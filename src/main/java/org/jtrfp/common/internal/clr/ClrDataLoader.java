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
package org.jtrfp.common.internal.clr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.jtrfp.common.FileLoadException;
import org.jtrfp.common.clr.ClrFile;



public class ClrDataLoader {

	private ClrDataLoader() {
	}

	public static ClrData load(ClrFile clrFile) throws FileLoadException {
		try {
			return load(new FileInputStream(clrFile.getFile()));
		} catch (FileNotFoundException e) {
			throw new FileLoadException(e);
		}
	}

	public static ClrData load(InputStream is) throws FileLoadException {
		try {
			long fileSize = is.available();
			
			// it has n^2 two byte size entries
			int entryCount = (int) (fileSize / 2);

			// which must be square, so file size is (n*2)^2
			int sideLength = (int) Math.sqrt(entryCount);
			if (Math.pow(sideLength, 2.0d) != entryCount) {
				throw new FileLoadException("CLR file must be square.");
			}

			int[] colors = new int[entryCount];
			int[] types = new int[entryCount];

			int maxColorIndex = 0;
			byte[] buffer = new byte[2];
			for (int i = 0; i < entryCount; i++) {
				is.read(buffer);
				int colorIndex = makeColor(buffer);
				if (maxColorIndex < colorIndex) {
					maxColorIndex = colorIndex;
				}
				colors[i] = colorIndex;
				types[i] = makeType(buffer);
			}
			is.close();
			return new ClrData(colors, types, maxColorIndex);
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
	}

	private static int makeType(byte[] buffer) {
		return ((buffer[1] & 0xF0) >> 4);
	}

	private static int makeColor(byte[] buffer) {
		return ((buffer[1] & 0x0F) << 8)
		+ (buffer[0] & 0xFF);
	}

}
