/*
 * This file is part of mtmX.
 *
 * mtmX is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mtmX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mtmX.  If not, see <http://www.gnu.org/licenses/>.
 */
package jtrfp.common.internal.pod;

import java.io.IOException;
import java.io.InputStream;

import jtrfp.common.internal.Util;
import jtrfp.common.pod.PodDataFormat;
import jtrfp.common.pod.PodFile;


public class Pod3Loader extends AbstractPodLoader {

	private static final int COMMENT_LENGTH = 80;

	private static final int AUTHOR_LENGTH = 80;

	private static final int COPYRIGHT_LENGTH = 80;

	private final PodData podData;

	public Pod3Loader(InputStream is, PodFile podFile) {
		super(is, podFile);
		podData = new PodData(podFile, PodDataFormat.POD3);
	}

	@Override
	public PodData load() throws IOException {

		// 00 - header "POD3" was read by PodDataLoader

		// 01 - checksum
		read4(1);
		podData.setChecksum(Util.makeSignedInt(buffer4));

		// 02 - comment
		byte[] commentBuffer = new byte[COMMENT_LENGTH];
		read(commentBuffer);
		podData.setComment(Util.makeString(commentBuffer));

		// 03 - entry count
		read4(1);
		final int entryCount = Util.makeSignedInt(buffer4);

		// 04 - audit count
		read4(1);
		Util.makeSignedInt(buffer4);

		// 05 - revision
		read4(1);

		// 06 - priority
		read4(1);

		// 07 - author
		byte[] authorBuffer = new byte[AUTHOR_LENGTH];
		read(authorBuffer);
		Util.makeString(authorBuffer);

		// 08 - copyright
		byte[] copyrightBuffer = new byte[COPYRIGHT_LENGTH];
		read(copyrightBuffer);
		Util.makeString(copyrightBuffer);

		// 09 - directory offset
		read4(1);
		int directoryOffset = Util.makeSignedInt(buffer4);

		// TODO here some possibly important values are left out

		seekForwardTo(directoryOffset);

		// read entries
		int lastOffset = -1;

		// read the directory
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

		// read string table and build entries
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
