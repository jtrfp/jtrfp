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



public class Pod5Loader extends AbstractPodLoader {

	private static final int COMMENT_LENGTH = 80; // XXX seems ok

	private static final int AUTHOR_LENGTH = 80; // XXX seems ok

	private static final int COPYRIGHT_LENGTH = 80; // XXX seems ok

	private final PodData podData;

	public Pod5Loader(InputStream is, PodFile podFile) {
		super(is, podFile);
		podData = new PodData(podFile, PodDataFormat.POD5);
	}

	@Override
	public PodData load() throws IOException {

		// 00 - header "POD5" was read by PodDataLoader

		// 01 - checksum
		// XXX seems ok
		read4(1);
		podData.setChecksum(Util.makeSignedInt(buffer4));

		// 02 - comment
		// XXX	 seems ok
		byte[] commentBuffer = new byte[COMMENT_LENGTH];
		read(commentBuffer);
		podData.setComment(Util.makeString(commentBuffer));

		// 03 - entry count
		// XXX seems ok
		read4(1);
		final int entryCount = Util.makeSignedInt(buffer4);
		// XXX in language pod this is 514

		// 04 - XXX seems like another count
		read4(1);
		Util.makeSignedInt(buffer4);

		// 05 - XXX seems like another number
		read4(1);

		// 06 - XXX seems like another number
		read4(1);

		// XXX this now is followed by 160 bytes

		// 07 - author
		// XXX in POD3 this was the author
		byte[] authorBuffer = new byte[AUTHOR_LENGTH];
		read(authorBuffer);
		Util.makeString(authorBuffer);

		// 08 - copyright
		// XXX in POD3 this was the copyright message
		byte[] copyrightBuffer = new byte[COPYRIGHT_LENGTH];
		read(copyrightBuffer);
		Util.makeString(copyrightBuffer);

		// 09 - directory offset
		read4(1);
		int directoryOffset = Util.makeSignedInt(buffer4);
		// XXX in language pod this is 8A 27 3B

		// XXX here some possibly important values are left out
		// XXX there are now 116 bytes until the file content starts

		seekForwardTo(directoryOffset);

		// read entries
		int lastOffset = -1;

		// read the directory
		int[] pathOffsets = new int[entryCount];
		int[] sizes = new int[entryCount];
		int[] offsets = new int[entryCount];

		for (int i = 0; i < entryCount; i++) {
			// XXX every entry ist 28 bytes long

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

			// XXX read the rest
			read4(4);
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

		// XXX there is more after this

		return podData;
	}
}
