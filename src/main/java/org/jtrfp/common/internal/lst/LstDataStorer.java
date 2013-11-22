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
package org.jtrfp.common.internal.lst;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jtrfp.common.FileStoreException;
import org.jtrfp.common.lst.ILstData;



public class LstDataStorer {

	public static void store(File file, ILstData data) throws FileStoreException {
		String lineSeparator = System.getProperty("line.separator");
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			String[] entries = data.getEntries();
			for (String entry : entries) {
				writer.write(entry);
				writer.write(lineSeparator);
			}
			writer.close();
		} catch (IOException e) {
			throw new FileStoreException(e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}
	}
}
