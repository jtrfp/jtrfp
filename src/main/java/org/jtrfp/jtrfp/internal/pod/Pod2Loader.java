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



public class Pod2Loader extends AbstractPodLoader {

	private final PodData podData;

	public Pod2Loader(InputStream is, PodFile podFile) {
		super(is, podFile);
		podData = new PodData(podFile, PodDataFormat.POD2);
	}

	@Override
	public PodData load() throws IOException {

		// 00 - header "POD3" was read by PodDataLoader

		// 01 - checksum
		read4(1);
		podData.setChecksum(Util.makeSignedInt(buffer4));

		// 02 - comment
		byte[] commentBuffer = new byte[80];
		read(commentBuffer);
		podData.setComment(Util.makeString(commentBuffer));

		// 03 - entry count
		read4(1);
		final int entryCount = Util.makeSignedInt(buffer4);

		// 04 - audit count
		read4(1);
		Util.makeSignedInt(buffer4);

		// 05 - entries
		int lastOffset = -1;

		int[] pathOffsets = new int[entryCount];
		int[] sizes = new int[entryCount];
		int[] offsets = new int[entryCount];

		for (int i = 0; i < entryCount; i++) {
			// pathOffset
			read4(1);
			int pathOffset = Util.makeSignedInt(buffer4);
			pathOffsets[i] = pathOffset;
			assert lastOffset < pathOffset;
			lastOffset = pathOffset;

			// size
			read4(1);
			sizes[i] = Util.makeSignedInt(buffer4);

			// offset
			read4(1);
			offsets[i] = Util.makeSignedInt(buffer4);

			// timestamp
			read4(1);

			// checksum
			read4(1);
		}

		// 05 - read string table
		byte[] pathBuffer = new byte[256];
		for (int i = 0; i < entryCount; i++) {

			int j = 0;
			for (j = 0; j < 256; j++) {
				int x = read();
				pathBuffer[j] = (byte) x;
				if (x == 0) {
					break;
				}
			}
			// XXX check pathOffset for i + 1 == j here
			//is.read(pathBuffer);
			String path = Util.makeString(pathBuffer);

			PodFileEntry entry = createPodFileEntry(podData, path);
			entry.setOffset(offsets[i]);
			entry.setSize(sizes[i]);
			entry.setPath(path);
			podData.addEntry(entry);
		}

		return podData;
	}
}
