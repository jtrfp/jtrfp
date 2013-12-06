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
package org.jtrfp.jtrfp.tools;

import java.io.File;

import org.jtrfp.jtrfp.FileLoadException;
import org.jtrfp.jtrfp.FileStoreException;
import org.jtrfp.jtrfp.pod.IPodData;
import org.jtrfp.jtrfp.pod.IPodFileEntry;
import org.jtrfp.jtrfp.pod.PodFile;



public class Pod {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length == 0) {
			System.err.println("You must at least specify a pod file.");
			printHelp();
			System.exit(1);
		}

		boolean list = false;
		String podFilePath = "";
		String outDir = "";

		for (int i = 0; i < args.length; i++) {
			String s = args[i];

			if (isOption(s)) {
				if ("-l".equals(s)) {
					list = true;
				} else if ("-o".equals(s)) {
					if (i + 2 >= args.length) {
						System.err.println("No out directory given " + s);
						printHelp();
						System.exit(1);
					}

					i++;
					outDir = args[i];
				} else {
					System.err.println("Unknown option " + s);
					printHelp();
					System.exit(1);
				}

			} else {
				if (i != args.length - 1) {
					System.err.println("Invalid options " + s);
					printHelp();
					System.exit(1);
				}

				podFilePath = s;
			}
		}

		if (list && outDir.length() > 0) {
			System.err.println("Ignoring out dir in list mode");
		}

		// setup paths

		File file = new File(podFilePath);

		// TODO: check for pod file existence
		// TODO: check for out dir existence

		PodFile podFile = new PodFile(file);

		try {
			// retrieve all BIN file entries from POD file
			IPodData data = podFile.getData();
			IPodFileEntry[] entries = data.getEntries();

			if (!list) {
				System.out.println("Found " + data.getEntryCount() + " files.");
			}

			// store each entry
			for (IPodFileEntry entry : entries) {
				String path = entry.getPath().replaceAll("\\\\", "/");

				if (list) {
					System.out.println(path);
				} else {
					System.out.println("Extracting file " + entry.getPath());

					File outFile = new File(outDir, entry.getPath().replaceAll("\\\\", "/"));
					outFile.getParentFile().mkdir(); // insure directory exists
					entry.toFile(outFile);
				}
			}
		} catch (FileLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private static void printHelp() {
		System.out.println("Use pod [options] podFile");
		System.out.println("-l List");
		System.out.println("-o output directory");
	}

	private static boolean isOption(String s) {
		return s.charAt(0) == '-';
	}
}
