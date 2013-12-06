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
package org.jtrfp.jtrfp.internal.act;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.jtrfp.jtrfp.FileLoadException;
import org.jtrfp.jtrfp.act.ActColor;
import org.jtrfp.jtrfp.act.ActFile;
import org.jtrfp.jtrfp.act.IActData;
import org.jtrfp.jtrfp.internal.Util;



public final class ActDataLoader {

	private static final int BYTES_PER_COLOR = 3;

	private ActDataLoader() {
	}

	public static IActData load(ActFile actFile) throws FileLoadException {
		try {
			return load(new FileInputStream(actFile.getFile()));
		} catch (FileNotFoundException e) {
			throw new FileLoadException(e);
		}
	}

	public static IActData load(InputStream is) throws FileLoadException {

		ActData colorTable = new ActData();

		try {
			long fileSize = is.available();

			if ((fileSize % BYTES_PER_COLOR) != 0) {
				String msg = "Color table file size must be a multiple of " + BYTES_PER_COLOR + ".";
				throw new FileLoadException(msg);
			}

			long colorCount = fileSize / BYTES_PER_COLOR;

			for (int i = 0; i < colorCount; i++) {
				byte[] colorBuffer = new byte[BYTES_PER_COLOR];
				is.read(colorBuffer);
				ActColor color = new ActColor(
						Util.unsignedByteToInt(colorBuffer[0]),
						Util.unsignedByteToInt(colorBuffer[1]),
						Util.unsignedByteToInt(colorBuffer[2]));
				colorTable.add(color);
			}
			is.close();
		} catch (FileNotFoundException e) {
			throw new FileLoadException("Color table file does not exist.", e);
		} catch (IOException e) {
			throw new FileLoadException("Error accessing color table file.", e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}

		return colorTable;
	}

}
