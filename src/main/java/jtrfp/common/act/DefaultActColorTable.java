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
package jtrfp.common.act;

import java.awt.image.BufferedImage;
import java.io.File;

import jtrfp.common.FileStoreException;


/**
 * Default color table in gray scale with 256 indexed colors.
 * @author Stefan Teitge
 */
public class DefaultActColorTable implements IActData {

	private final int colorCount;

	private final ActColor[] colors;

	/**
	 * 
	 * @param colorCount if colorCount > 256 colors are adapted to a maximum of 256 colors
	 */
	public DefaultActColorTable(int colorCount) {
		this.colorCount = colorCount;
		colors = new ActColor[colorCount];

		for (int i = 0; i < colorCount; i++) {
			int component = Math.round(i * (colorCount / 256.0f));
			colors[i] = new ActColor(component, component, component); // TODO: calc better colors
		}
	}

	@Override
	public ActColor getColor(int index) {
		// TODO: check index
		return colors[index];
	}

	@Override
	public int getColorCount() {
		return colorCount;
	}

	@Override
	public ActColor[] getColors() {
		int length = colors.length;
		ActColor[] colorsCopy = new ActColor[length];
		System.arraycopy(colors, 0, colorsCopy , 0, length);
		return colorsCopy;
	}

	@Override
	public BufferedImage toImage() {
		return null; // TODO maybe improve
	}

	@Override
	public void toBitmapFile(File file) throws FileStoreException {
		// TODO maybe improve
	}

}
