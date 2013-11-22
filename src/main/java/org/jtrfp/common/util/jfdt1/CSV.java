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
 ******************************************************************************/
package org.jtrfp.common.util.jfdt1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class CSV implements ArrayParser{
	StringParser subParser;
	public CSV(StringParser subParser)
		{this.subParser=subParser;}
	
	@Override
	public Object parseRead(String s){
		ArrayList<Object> result = new ArrayList<Object>();
		Scanner scanner = new Scanner(s).useDelimiter(",");
		while(scanner.hasNext())
			{result.add(subParser.parseRead(scanner.next()));}
		Class elementClass = result.get(0).getClass();
		Object rA = Array.newInstance(elementClass, result.size());
		final int size=result.size();
		for(int i=0; i<size;i++)
			{Array.set(rA, i, result.get(i));}
		return rA;
		}//end parseRead(...)

	@Override
	public String parseWrite(Object o){
		Object [] objects = (Object [])o;
		StringBuilder result = new StringBuilder();
		for(Object obj:objects)
			{result.append(subParser.parseWrite(obj));result.append(",");}
		//Remove trailing comma
		result.deleteCharAt(result.length()-1);
		return result.toString();
		}//end parseWrite(...)
	}//end CSV
