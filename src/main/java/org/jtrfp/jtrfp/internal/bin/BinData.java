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

import java.util.ArrayList;
import java.util.List;

import org.jtrfp.jtrfp.bin.IBinData;
import org.jtrfp.jtrfp.bin.IBinFace;
import org.jtrfp.jtrfp.bin.IBinVertex;



public class BinData implements IBinData {

	private int vertexCount;

	private int id;

	private final List<IBinVertex> vertexes;

	private final List<IBinVertex> normals;

	private final List<IBinFace> faces;

	private final List<String> textureNames;

	public BinData() {
		vertexes = new ArrayList<IBinVertex>();
		normals = new ArrayList<IBinVertex>();
		faces = new ArrayList<IBinFace>();
		textureNames = new ArrayList<String>();
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public int getVertexCount() {
		return vertexCount;
	}

	public void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public IBinVertex[] getVertexes() {
		return vertexes.toArray(new IBinVertex[]{});
	}

	public void addVertex(IBinVertex vertex) {
		vertexes.add(vertex);
	}

	public void addFace(IBinFace binFace) {
		faces.add(binFace);
	}

	@Override
	public IBinFace[] getFaces() {
		return faces.toArray(new IBinFace[]{});
	}

	public void addNormal(IBinVertex normal) {
		normals.add(normal);
	}

	@Override
	public IBinVertex[] getNormals() {
		return normals.toArray(new IBinVertex[]{});
	}

	public void addTextureName(String name) {
		textureNames.add(name);
	}

	@Override
	public String[] getTextureNames() {
		return textureNames.toArray(new String[]{});
	}
}
