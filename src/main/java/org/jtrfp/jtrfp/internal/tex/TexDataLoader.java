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
package org.jtrfp.jtrfp.internal.tex;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.jtrfp.jtrfp.FileLoadException;
import org.jtrfp.jtrfp.tex.ITexData;
import org.jtrfp.jtrfp.tex.TexFile;



public class TexDataLoader {

	public static ITexData load(TexFile texFile) throws FileLoadException {
		try {
			return load(new FileInputStream(texFile.getFile()));
		} catch (FileNotFoundException e) {
			throw new FileLoadException(e);
		}
	}

	public static ITexData load(InputStream sfis) throws FileLoadException {
		TexData texData = new TexData();

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(sfis));

			// first line is the number of textures
			String line = reader.readLine();
			int textureCount = Integer.parseInt(line);
			while ((line = reader.readLine()) != null) {
				texData.addTextureName(line);
			}
			reader.close();

			if (textureCount != texData.getTextureCount()) {
				throw new FileLoadException("Texture count in header is incorrect.");
			}
		} catch (FileNotFoundException e) {
			throw new FileLoadException(e);
		} catch (IOException e) {
			throw new FileLoadException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}

		return texData;
	}

}
