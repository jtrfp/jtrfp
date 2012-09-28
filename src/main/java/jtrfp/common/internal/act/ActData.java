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
package jtrfp.common.internal.act;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import jtrfp.common.FileStoreException;
import jtrfp.common.act.ActColor;
import jtrfp.common.act.IActData;



public class ActData implements IActData {

	private final List<ActColor> colors;

	public ActData() {
		colors = new ArrayList<ActColor>();
	}

	public int getColorCount() {
		return colors.size();
	}

	public ActColor[] getColors() {
		return colors.toArray(new ActColor[]{});
	}

	public void add(ActColor color) {
		colors.add(color);
	}

	public ActColor getColor(int index) {
		if (index < 0 || index >= getColorCount()) {
			throw new IndexOutOfBoundsException("Color " + index + " does not exist.");
		}

		return colors.get(index);
	}

	public void toBitmapFile(File file) throws FileStoreException {
		// TODO: check file
		try {
			BufferedImage image = toImage();
			ImageIO.write(image, "bmp", file);
		} catch (IOException e) {
			throw new FileStoreException(e);
		}
	}

	public BufferedImage toImage() {
		// TODO: check square
		int sideLength = (int) Math.sqrt(getColorCount());

		BufferedImage image = new BufferedImage(sideLength, sideLength, BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < sideLength; i++) {
			for (int j = 0; j < sideLength; j++) {
				ActColor color = getColor(i * sideLength + j);
				image.setRGB(i, j, color.toRgb());
			}
		}

		return image;
	}
}
