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
package org.jtrfp.common.pod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jtrfp.common.FileLoadException;
import org.jtrfp.common.FileStoreException;


public class PodLstFile {
	
	private final List<String> entries;

	private PodLstFile(List<String> entries) {
		this.entries = entries;
	}

	public static PodLstFile createNew() {
		return new PodLstFile(new ArrayList<String>());
	}
	
	public static PodLstFile fromFile(File file) throws FileLoadException {
		List<String> entryList = new ArrayList<String>();
		
		Scanner scanner = null;
	    try {
	    	scanner = new Scanner(new FileInputStream(file));
	    	
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				entryList.add(line);
			}
	    } catch (FileNotFoundException ex) {
	    	throw new FileLoadException("File not found.", ex);
	    } finally{
	    	if (scanner != null) {
	    		scanner.close();
	    	}
	    }
	    
		return new PodLstFile(entryList);
	}
	
	public static PodLstFile fromPodData(IPodData data) throws FileLoadException {
		List<String> entryList = new ArrayList<String>();
		for (IPodFileEntry entry : data.getEntries()) {
			entryList.add(entry.getPath());
		}
		
		return new PodLstFile(entryList);
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
			
			for (String entry : entries) {
				printWriter.println(entry);	
			}
			
			printWriter.close();
		} catch (IOException e) {
			throw new FileStoreException("File could not be opened.", e);
		}
	}
}
