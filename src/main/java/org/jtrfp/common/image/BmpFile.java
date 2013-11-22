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
package org.jtrfp.common.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jtrfp.common.FileLoadException;



/**
 * Abstract representation for BMP (Bitmap) file.
 * @author Stefan Teitge
 */
public class BmpFile {

	private final File file;

	/**
	 * Constructs a BMP file from a file in the file system.
	 * @param file the file
	 */
	public BmpFile(File file) {
		this.file = file;
	}

	/**
	 * Returns the file this BMP file loads data from.
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Converts file into AWT Image object.
	 * @return the image
	 * @throws FileLoadException if reading fails
	 */
	public BufferedImage toImage() throws FileLoadException {
		BufferedImage image;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			throw new FileLoadException(e);
		}

		return image;
	}
}
