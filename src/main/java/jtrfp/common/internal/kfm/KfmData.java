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
package jtrfp.common.internal.kfm;

import jtrfp.common.kfm.IKfmData;
import jtrfp.common.kfm.IKfmPart;
import jtrfp.common.kfm.IKfmPoly;
import jtrfp.common.kfm.IKfmVertex;

public class KfmData implements IKfmData {

	private int version;
	private int polyCount;
	private int partCount;
	private int frameCount;
	private int textureCount;
	private int vertexCount;

	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public int getVertexCount() {
		return vertexCount;
	}

	@Override
	public int getPolyCount() {
		return polyCount;
	}

	@Override
	public int getTextureCount() {
		return textureCount;
	}

	@Override
	public int getPartCount() {
		return partCount;
	}

	@Override
	public int getFrameCount() {
		return frameCount;
	}

	@Override
	public int getUseCollisionListFlag() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTransparentPixelFlag() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDisableBackfaceCulling() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEnviromentMapListFlag() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IKfmVertex[] getVertexes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IKfmPoly[] getPolys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getTextures() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IKfmPart[] getParts() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}

	public void setTextureCount(int textureCount) {
		this.textureCount = textureCount;
	}

	public void setPolyCount(int polyCount) {
		this.polyCount = polyCount;
	}

	public void setPartCount(int partCount) {
		this.partCount = partCount;
	}

	public void setFrameCount(int frameCount) {
		this.frameCount = frameCount;
	}
}
