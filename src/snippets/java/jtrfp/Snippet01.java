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
package jtrfp;

import java.io.File;

import jtrfp.common.FileLoadException;
import jtrfp.common.FileStoreException;
import jtrfp.common.bin.IBinPodFileEntry;
import jtrfp.common.pod.IPodData;
import jtrfp.common.pod.IPodFileEntry;
import jtrfp.common.pod.PodFile;
import jtrfp.common.raw.IRawPodFileEntry;


/**
 * This snippet shows how to store all BIN files from a specified POD file
 * to disk. You can do the same for other file types using the interfaces derived from
 * {@link IPodFileEntry}, e.g. {@link IRawPodFileEntry}.
 * @author Stefan Teitge
 */
public class Snippet01 {

	/**
	 * Runs the snippet.
	 * @param args path to POD file and directory the files are saved to
	 */
	public static void main(String[] args) {

		// setup paths
		String podFilePath = args[0];
		String outDir = args[1];

		// setup pod file
		File file = new File(podFilePath);
		PodFile podFile = new PodFile(file);

		try {
			// retrieve all BIN file entries from POD file
			IPodData data = podFile.getData();
			IBinPodFileEntry[] entries = data.findEntries(IBinPodFileEntry.class);

			// store each entry
			for (IBinPodFileEntry entry : entries) {
				File binFile = new File(outDir, entry.getPath());
				binFile.getParentFile().mkdir(); // insure directory exists
				entry.toFile(binFile);
			}
		} catch (FileLoadException e) {
			e.printStackTrace();
		} catch (FileStoreException e) {
			e.printStackTrace();
		}
	}

}
