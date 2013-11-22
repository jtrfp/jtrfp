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

import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class ClassInclusion{
	public abstract Class [] propose();
	
	public static ClassInclusion nestedClassesOf(final Class <?> containingClass){
		return new ClassInclusion(){
				@Override
				public Class[] propose(){
					Class<?> []rawClasses = containingClass.getDeclaredClasses();
					ArrayList<Class<?>> resultList = new ArrayList<Class<?>>();
					for(Class<?> clazz:rawClasses){
						if(Modifier.isStatic(clazz.getModifiers()) 
								&& Modifier.isPublic(clazz.getModifiers())
								&& ThirdPartyParseable.class.isAssignableFrom(clazz)
								&& !Modifier.isAbstract(clazz.getModifiers())){
							resultList.add(clazz);
							}//end if(matches)
						}//end for(classes)
					return resultList.toArray(new Class[]{});
					}//end propose()
			};
		}//end nestedClassesOf(...)
	
	public static ClassInclusion classOf(final Class <?> proposedClass){
		return new ClassInclusion(){
				@Override
				public Class[] propose(){
					if(proposedClass.isAssignableFrom(ThirdPartyParseable.class)&&Modifier.isAbstract(proposedClass.getModifiers()))return new Class[]{proposedClass};
					return new Class[]{proposedClass};
					}//end propose()
			
			};
		}//end nestedClassesOf(...)
	
	public static ClassInclusion classInIncludes(final String registryKey,final String classSimpleName){
		return new ClassInclusion(){
			@Override
			public Class<?>[] propose(){
				LinkedList<Class<?>>resultList = new LinkedList<Class<?>>();
				for(Class<?>clazz:ParserRegistry.getClassesIn(registryKey)){
					if(clazz.isAssignableFrom(ThirdPartyParseable.class)
							&& clazz.getSimpleName().contentEquals(classSimpleName)
							&&Modifier.isAbstract(clazz.getModifiers()))resultList.add(clazz);
					}//end for(...)
				return resultList.toArray(new Class[]{});
				}//end propose()
			};
		}//end nestedClassesOf(...)
	}//end ClassInclusion
