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
package org.jtrfp.jtrfp.internal.pod;

import java.io.IOException;
import java.io.InputStream;

import org.jtrfp.jtrfp.internal.Util;
import org.jtrfp.jtrfp.pod.PodDataFormat;
import org.jtrfp.jtrfp.pod.PodFile;



public class EpdLoader extends AbstractPodLoader {

	private final PodData podData;

	public EpdLoader(InputStream is, PodFile podFile) {
		super(is, podFile);
		podData = new PodData(podFile, PodDataFormat.EPD);
	}

	private static final int ENTRY_PATH_LENGTH = 64;

	private static final int COMMENT_LENGTH = 256;

	public static final int FILE_INFO_LENGTH = 40;

	public static final long PREFIX_LENGTH = PodDataLoader.HEADER_LENGTH + COMMENT_LENGTH;

	/**
	 * Read all contents from an EPD file.
	 * @throws IOException if reading fails.
	 */
	@Override
	public PodData load() throws IOException {

		// 00 - header "dtxe" was read by PodDataLoader

		// 01 - comment
		byte[] commentBuffer = new byte[COMMENT_LENGTH];
		read(commentBuffer);
		podData.setComment(Util.makeString(commentBuffer));

		// 02 - number of entries
		read4(1);
		final int entryCount = Util.makeSignedInt(buffer4);

		// 03 - version
		read4(1);

		// 04 - checksum
		read4(1);
		podData.setChecksum(Util.makeSignedInt(buffer4));

		// 05 - entries
		readEntries(entryCount);

		return podData;
	}

	/**
	 * Reads the entries from the EPD file.
	 * @param entryCount
	 * @throws IOException if reading fails.
	 */
	private void readEntries(int entryCount) throws IOException {
		for (int i = 0; i < entryCount; i++) {

			// 01 - path
			byte[] pathBuffer = new byte[ENTRY_PATH_LENGTH];
			read(pathBuffer);
			String path = Util.makeString(pathBuffer);
			PodFileEntry pfei = createPodFileEntry(podData, path);
			pfei.setPath(path);

			// 02 - size
			read4(1);
			pfei.setSize(Util.makeSignedInt(buffer4));

			// 03 - offset
			read4(1);
			pfei.setOffset(Util.makeSignedInt(buffer4));

			// 04 - time stamp
			read4(1);

			// 05 - checksum
			read4(1);

			// finally add it
			pfei.setIndex(i);
			podData.addEntry(pfei);
		}
	}
}
