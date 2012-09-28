/*
 * This file is part of jtrfp.
 *
 * jtrfp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jtrfp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jtrfp.  If not, see <http://www.gnu.org/licenses/>.
 */
package jtrfp.common.pod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jtrfp.common.FileLoadException;
import jtrfp.common.FileStoreException;

public class PodIniFile {
	
	private final List<String> entries;

	private PodIniFile(List<String> entries) {
		this.entries = entries;
	}

	public static PodIniFile createNew() {
		return new PodIniFile(new ArrayList<String>());
	}
	
	public static PodIniFile fromFile(File file) throws FileLoadException {
		List<String> entryList = new ArrayList<String>();
		
		int lineCount = 0;
		Scanner scanner = null;
	    try {
	    	scanner = new Scanner(new FileInputStream(file));
	    	
	    	boolean first = true;
			while (scanner.hasNextLine()) {
				if (first && scanner.hasNextInt()) {
					lineCount = scanner.nextInt();
					scanner.nextLine();
					first = false;
				} else {
					String line = scanner.nextLine();
					
					// XXX some pod.ini have an extra 0x1B (MTM2) or 0x1A (BloodRayne, 4x4 Evo)
					// or maybe other trailing characters
					if (line.length() > 1) {
						entryList.add(line);	
					}
				}
			}
	    } catch (FileNotFoundException ex) {
	    	throw new FileLoadException("File not found.", ex);
	    } finally{
	    	if (scanner != null) {
	    		scanner.close();
	    	}
	    }
	    
	    if (entryList.size() != lineCount) {
	    	throw new FileLoadException("Entry count mismatches actual entries.");
	    }
		
		return new PodIniFile(entryList);
	}
	
	public String[] getEntries() {
		return entries.toArray(new String[0]);
	}

	public int getEntryCount() {
		return entries.size();
	}

	public void addEntry(String entry) {
		entries.add(entry);
	}

	public void toFile(File file) throws FileStoreException {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			printWriter.println(getEntryCount());
			
			for (String entry : entries) {
				printWriter.println(entry);	
			}
			
			printWriter.close();
		} catch (IOException e) {
			throw new FileStoreException("File could not be opened.", e);
		}
	}
}
