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
package org.jtrfp.jtrfp.internal.bin;

import org.jtrfp.jtrfp.bin.IBinTexCoord;

public class BinTexCoord implements IBinTexCoord {

	private int vertexIndex;

	private float u;

	private float v;

	@Override
	public float getU() {
		return u;
	}

	@Override
	public float getV() {
		return v;
	}

	public void setU(float u) {
		this.u = u;
	}

	public void setV(float v) {
		this.v = v;
	}

	@Override
	public int getVertexIndex() {
		return vertexIndex;
	}

	public void setVertexIndex(int vertexIndex) {
		this.vertexIndex = vertexIndex;
	}
}
