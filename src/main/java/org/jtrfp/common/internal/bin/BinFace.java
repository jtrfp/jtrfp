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
package org.jtrfp.common.internal.bin;

import java.util.ArrayList;
import java.util.List;

import org.jtrfp.common.bin.IBinFace;
import org.jtrfp.common.bin.IBinTexCoord;
import org.jtrfp.common.bin.IBinVertex;



public class BinFace implements IBinFace {

	private int texCoordCount;

	private IBinVertex normal;

	private int faceId;

	private final List<IBinTexCoord> texCoords;

	private String textureName;

	public BinFace() {
		texCoords = new ArrayList<IBinTexCoord>();
	}

	@Override
	public int getFaceId() {
		return faceId;
	}

	@Override
	public IBinVertex getNormal() {
		return normal;
	}

	@Override
	public int getTexCoordCount() {
		return texCoordCount;
	}

	@Override
	public IBinTexCoord[] getTexCoords() {
		return texCoords.toArray(new IBinTexCoord[]{});
	}

	public void setNormal(IBinVertex normal) {
		this.normal = normal;
	}

	public void setFaceId(int faceId) {
		this.faceId = faceId;
	}

	public void addTexCoord(BinTexCoord texCoord) {
		texCoords.add(texCoord);
	}

	@Override
	public String getTextureName() {
		return textureName;
	}

	public void setTextureName(String textureName) {
		this.textureName = textureName;
	}

}
