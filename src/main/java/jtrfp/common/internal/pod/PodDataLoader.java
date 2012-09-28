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
package jtrfp.common.internal.pod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import jtrfp.common.FileLoadException;
import jtrfp.common.internal.Util;
import jtrfp.common.pod.PodDataFormat;
import jtrfp.common.pod.PodFile;


public class PodDataLoader {

	private static final String EPD_HEADER = "dtxe";

	private static final String POD5_HEADER = "POD5";
	
	private static final String POD4_HEADER = "POD4";

	private static final String POD3_HEADER = "POD3";

	private static final String POD2_HEADER = "POD2";

	static final int HEADER_LENGTH = 4;

	private final PodFile podFile;

	public PodDataLoader(PodFile podFile) {
		this.podFile = podFile;
	}

	public PodData load() throws FileLoadException {
		InputStream is = null;
		PodData podData = null;
		try {
			is = new FileInputStream(podFile.getFile());

			// header
			byte[] headerBuffer = new byte[HEADER_LENGTH];
			is.read(headerBuffer);
			String header = new String(headerBuffer);
			if (POD2_HEADER.equals(header)) {
				Pod2Loader pod2Loader = new Pod2Loader(is, podFile);
				podData = pod2Loader.load();
			} else if (POD3_HEADER.equals(header)) {
				Pod3Loader pod3Loader = new Pod3Loader(is, podFile);
				podData = pod3Loader.load();
			} else if (POD4_HEADER.equals(header)) {
				Pod4Loader pod4Loader = new Pod4Loader(is, podFile);
				podData = pod4Loader.load();
			} else if (POD5_HEADER.equals(header)) {
				Pod5Loader pod5Loader = new Pod5Loader(is, podFile);
				podData = pod5Loader.load();
			} else if (EPD_HEADER.equals(header)) {
				EpdLoader epdLoader = new EpdLoader(is, podFile);
				podData = epdLoader.load();
			} else {
				podData = new PodData(podFile, PodDataFormat.POD1);
				int fileCount = Util.unsignedByteToInt(headerBuffer[0]);
				fileCount += (Util.unsignedByteToInt(headerBuffer[1]) * 256);
				Pod1Loader pod1Loader = new Pod1Loader(is, podFile, fileCount);
				podData = pod1Loader.load();
			}

			is.close();
		} catch (FileNotFoundException e) {
			throw new FileLoadException(e);
		} catch (IOException e) {
			throw new FileLoadException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}

		return podData;
	}




}
