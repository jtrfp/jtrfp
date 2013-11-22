/*******************************************************************************
 * This file is part of the JAVA FILE DESCRIPTION TOOLKIT (JFDT)
 * A library for parsing files and mapping their data to/from java Beans.
 * ...which is now part of the JAVA TERMINAL REALITY FILE PARSERS project.
 * Copyright (c) 2012,2013 Chuck Ritola and any contributors to these files.
 * 
 *     JFDT is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     JDFT is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with jTRFP.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/package org.jtrfp.common.util.jfdt1;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Simple testing utility for jfdt1. Reads the given file, <br>
 * attempts to parse it using the given class, attempts to write it to a temporary location, <br>
 * then compares the two byte-for-byte, printing the results to the console.<br>
 * Note that some formats may fail the comparison test but could remain valid because the parser may have discarded garbage data
 * which is left unread anyway.
 * @author Chuck Ritola
 *
 */
public class TestParser{
	/**
	 * Read the supplied test file, attempt to parse it using the supplied ThirdPartyPareable class name, attempt to write it to testTempPath, and then perform a 1:1 byte comparison between input and temp.
	 * @param testFilePath
	 * @param testTempPath
	 * @param beanClassName
	 */
	public TestParser(String testFilePath, String testTempPath, String beanClassName){
		Parser prs = new Parser();
		try {
			ThirdPartyParseable obj = prs.readToNewBean(new EndianAwareDataInputStream(new DataInputStream(new BufferedInputStream(new FileInputStream(testFilePath)))), (Class<? extends ThirdPartyParseable>)Class.forName(beanClassName));
			
			printBean(obj);
			
			prs.writeBean(obj,new EndianAwareDataOutputStream(new DataOutputStream(new FileOutputStream(testTempPath))));
			
			//new File(testTempPath).deleteOnExit();//cleanup //Disabled so that the in/out can be analyzed manually.
			int diffIndex;
			if((diffIndex=compareTwoFiles(new File(testFilePath), new File(testTempPath)))==-1)
				{System.out.println("Read/Write passed 1:1 byte-for-byte test!");}
			else{
				System.out.println("Read/Write failed 1:1 byte-for-byte test...");
				System.out.println("Difference starts at byte index "+diffIndex+". in hex this index is: "+Integer.toHexString(diffIndex));
				System.out.println("This might not necessarily be a problem as the parser will sometimes discard garbage data,\n" +
						"for example following terminated strings in statically-sized allocations.");
				}
			}
		catch(Exception e)
			{e.printStackTrace();}
		}//end TestParser()
	
	/**
	 * Recursively prints a bean's properties, pending it is a ThirdPartyParseable.
	 * @param obj
	 * @throws Exception
	 * @since Sep 17, 2012
	 */
	public static void printBean(ThirdPartyParseable obj) throws Exception{
		for(PropertyDescriptor prop:Introspector.getBeanInfo(obj.getClass(), Introspector.USE_ALL_BEANINFO).getPropertyDescriptors()){
			Object value = prop.getReadMethod().invoke(obj, null);
			System.out.println("Property: "+prop.getName()+" \t\tValue: "+value);
			if(value.getClass().isArray()){
				Class<?>componentClass=value.getClass().getComponentType();
				if(ThirdPartyParseable.class.isAssignableFrom(value.getClass().getComponentType())){
					for(ThirdPartyParseable elm:(ThirdPartyParseable [])value)
						{printBean(elm);}
					}//end if(componentIsThirdPartyParseable)
				}//end if(isArray)
			if(value instanceof ThirdPartyParseable)
				{printBean((ThirdPartyParseable)value);}//Recurse print
			}//end for(properties)
		}//end printBean(...)
	
	/**
	 * Compares two files, byte for byte.
	 * @param one
	 * @param two
	 * @return							The byte index where the two files diverge, else -1.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @since Sep 17, 2012
	 */
	public static int compareTwoFiles(File one, File two) throws FileNotFoundException, IOException{
		//int result=-1;
		byte [] f1 = readToBytes(one);
		byte [] f2 = readToBytes(two);
		int scanLen=f1.length>f2.length?f2.length:f1.length;
		for(int i=0; i<scanLen;i++)
			{if(f1[i]!=f2[i])return i;}

		if(f1.length!=f2.length)return scanLen;//The divergence is where one file ends early
		return -1;
		}
	
	public static byte [] readToBytes(File f) throws IOException{
		DataInputStream di1=new DataInputStream(new FileInputStream(f));
		byte [] result = new byte[(int)f.length()];
		di1.read(result);
		return result;
		}
	
	/**
	 * Do it already!
	 * @param args			[inputFilePath]	[outputFilePath where the parser writes the bean to do 1:1 testing]	[name of the ThirdPartyParseable class describing the format of the given file]
	 * @since Sep 17, 2012
	 */
	public static void main(String [] args){
		System.out.println("Test Parser  Copyright (C) 2012  Chuck Ritola\n"+
				"This program comes with ABSOLUTELY NO WARRANTY.\n"+
				"This is free software, and you are welcome to redistribute it\n"+
				"under certain conditions under the GPL version 3.\n");
		if(args.length!=3)
			{System.out.print("USAGE:	TestParser [testFilePath] [tempFilePath] [beanClassName]\n\n" );}
			//"\tEXAMPLE: TestParser /home/bob/fury3/LEVELS/TERRAN.LVL /tmp/TERRAN.LVL "+LVLFile.class.getName()+"\n\n*************\n");
		else new TestParser(args[0],args[1],args[2]);
		}
	}//end TestParser
