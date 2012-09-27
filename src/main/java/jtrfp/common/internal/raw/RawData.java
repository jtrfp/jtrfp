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
package jtrfp.common.internal.raw;

import jtrfp.common.raw.IRawData;

public class RawData implements IRawData {

	private int sideLength;
	
	private int[][] pixels;
	
	public RawData(int sideLength, int[][] pixels) {
		this.sideLength = sideLength;
		this.pixels = pixels;
		
		// TODO check args
	}
	
	public int getHeight() {
		return sideLength;
	}

	public int getValueAt(int x, int y) {
		// TODO: check index NPE
		return pixels[x][y];
	}

	public int getWidth() {
		return sideLength;
	}

}
