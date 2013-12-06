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
package org.jtrfp.jtrfp.internal.lst;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.jtrfp.jtrfp.FileLoadException;
import org.jtrfp.jtrfp.lst.LstData;
import org.jtrfp.jtrfp.lst.LstFile;
import org.jtrfp.jtrfp.pod.IPodFileEntry;
import org.jtrfp.jtrfp.pod.PodFile;



public final class LstDataLoader {

	private LstDataLoader() {
	}

	public static LstData load(PodFile podFile) throws FileLoadException {
		LstData lstData = new LstData();
		IPodFileEntry[] entries = podFile.getData().getEntries();
		for (IPodFileEntry entry : entries) {
			lstData.addEntry(entry.getPath());
		}

		return lstData;
	}

	public static LstData load(LstFile lstFile) throws FileLoadException {
		LstData lstData = new LstData();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(lstFile.getFile()));

			String line = null;
			while ((line = reader.readLine()) != null) {
				lstData.addEntry(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			throw new FileLoadException(e);
		} catch (IOException e) {
			throw new FileLoadException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}

		return lstData;
	}

}
