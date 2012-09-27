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
package jtrfp.common.internal.clr;

import jtrfp.common.clr.IClrData;

public class ClrData implements IClrData {

	private final int[] colors;

	private final int[] types;

	private final int height;

	private final int width;

	private final int maxColorIndex;

	public ClrData(int[] colors, int[] types, int maxColorIndex) {
		this.colors = colors;
		this.types = types;
		this.maxColorIndex = maxColorIndex;

		width = (int) Math.sqrt(colors.length);
		height = width;
	}

	@Override
	public int getColorAt(int x, int y) {
		return colors[x * width + y];
	}

	@Override
	public int getColorCount() {
		return maxColorIndex + 1;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getTypeAt(int x, int y) {
		return types[x * width + y];
	}

	@Override
	public int getWidth() {
		return width;
	}
}
