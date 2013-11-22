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
package org.jtrfp.common.raw;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jtrfp.common.FileLoadException;
import org.jtrfp.common.FileStoreException;
import org.jtrfp.common.act.ActColor;
import org.jtrfp.common.act.IActData;




public class RawImage {

	private final IActData colorTable;

	private final IRawData rawData;

	public RawImage(IRawData rawFile, IActData actFile) {
		colorTable = actFile;
		rawData = rawFile;
	}

	public IActData getActFile() {
		return colorTable;
	}

	public IRawData getRawFile() {
		return rawData;
	}

	public BufferedImage toImage() throws FileStoreException {
		BufferedImage outImg;
		try {
			outImg = new BufferedImage(
					rawData.getWidth(),
					rawData.getHeight(),
					BufferedImage.TYPE_INT_RGB);

			for (int i = 0; i < rawData.getWidth(); i++) {
				for (int j = 0; j < rawData.getHeight(); j++) {
					outImg.setRGB(j, i, getRgb(rawData.getValueAt(i, j)));
				}
			}
		} catch (FileLoadException e) {
			throw new FileStoreException(e);
		}

		return outImg;
	}

	public void storeAsBitmap(File file) throws FileStoreException {
		// TODO: check file
		try {
			ImageIO.write(toImage(), "bmp", file);
		} catch (IOException e) {
			throw new FileStoreException(e);
		}
	}

	private int getRgb(int value) throws FileLoadException {
		ActColor color = colorTable.getColor(value);
		return color.toRgb();
	}
}
