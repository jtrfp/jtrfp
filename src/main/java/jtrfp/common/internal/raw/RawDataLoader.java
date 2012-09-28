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
package jtrfp.common.internal.raw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import jtrfp.common.FileLoadException;
import jtrfp.common.internal.Util;
import jtrfp.common.raw.IRawData;
import jtrfp.common.raw.RawFile;


public final class RawDataLoader {

	private RawDataLoader() {
	}

	public static IRawData load(RawFile rawFile) throws FileLoadException {
		try {
			return load(new FileInputStream(rawFile.getFile()));
		} catch (FileNotFoundException e) {
			throw new FileLoadException(e);
		}
	}

	private static int[] makeInt(byte[] buffer) {
		final int length = buffer.length;
		int[] intBuffer = new int[length];
		for (int i = 0; i < length; i++) {
			intBuffer[i] = Util.unsignedByteToInt(buffer[i]);
		}
		return intBuffer;
	}

	public static IRawData load(InputStream is) throws FileLoadException {
		try {
			long fileSize = is.available();

			int sideLength = (int) Math.sqrt(fileSize);
			if (Math.pow(sideLength, 2.0d) != fileSize) {
				throw new FileLoadException("Raw file must be square.");
			}

			int[][] pixels = new int[sideLength][sideLength];

			byte[] buffer = new byte[sideLength];
			for (int i = 0; i < sideLength; i++) {
				is.read(buffer);
				pixels[i] = makeInt(buffer);
			}
			is.close();
			return new RawData(sideLength, pixels);
		} catch (FileNotFoundException e) {
			throw new FileLoadException("Raw file does not exist.", e);
		} catch (IOException e) {
			throw new FileLoadException("Error accessing raw file.", e);
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
}
