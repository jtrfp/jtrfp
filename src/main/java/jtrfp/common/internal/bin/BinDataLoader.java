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
package jtrfp.common.internal.bin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import jtrfp.common.FileLoadException;
import jtrfp.common.bin.BinFile;
import jtrfp.common.bin.IBinData;
import jtrfp.common.bin.IBinVertex;
import jtrfp.common.internal.Util;


public final class BinDataLoader {

	private static final int BLOCK_ID_STOP = 0x00;

	private static final int BLOCK_ID_ROTOR = 0x02;

	private static final int BLOCK_ID_NORMALS = 0x03;

	private static final int BLOCK_ID_FACE_UNKNOWN_05 = 0x05;

	private static final int BLOCK_ID_COLOR = 0x0A;

	private static final int BLOCK_ID_UNKNOWN_0C = 0x0C;

	private static final int BLOCK_ID_TEXTURE = 0x0D;

	private static final int BLOCK_ID_FACE_UNKNOWN_0E = 0x0E;

	private static final int BLOCK_ID_FACE_MTM1_TRANS = 0x11;

	private static final int BLOCK_ID_UNKNOWN_12 = 0x12;

	private static final int BLOCK_ID_FACE_NOT_TRANS = 0x18;

	private static final int BLOCK_ID_FACE_COLOR = 0x19;

	private static final int BLOCK_ID_TEXTURE_ANIMATED = 0x1D;

	private static final int BLOCK_ID_FACE_MTM2_SHINY = 0x29;

	private static final int BLOCK_ID_FACE_MTM2_TRANS = 0x33;

	private static final int BLOCK_ID_FACE_MTM2_ARENA = 0x34;

	private int charCount;

	private InputStream is;

	private final byte[] buffer4 = new byte[4];

	private String lastTextureName = null;

	public BinDataLoader() {
	}

	public IBinData load(BinFile binFile) throws FileLoadException {
		try {
			return load(new FileInputStream(binFile.getFile()));
		} catch (FileNotFoundException e) {
			throw new FileLoadException(e);
		}
	}

	private void readBlocks(BinData binData) throws IOException, FileLoadException {
		int id = -1;
		while (id != BLOCK_ID_STOP) {
			read4(1);
			id = makeInt(buffer4);
			switch (id) {
			case BLOCK_ID_TEXTURE:
				readTextureBlock(binData);
				break;
			case BLOCK_ID_TEXTURE_ANIMATED:
				readAnimatedTextureBlock();
				break;
			case BLOCK_ID_FACE_MTM1_TRANS:
				readFaceBlock(binData, id, true);
				break;
			case BLOCK_ID_FACE_MTM2_TRANS:
				readFaceBlock(binData, id, true);
				break;
			case BLOCK_ID_FACE_MTM2_SHINY:
				readFaceBlock(binData, id, true);
				break;
			case BLOCK_ID_FACE_MTM2_ARENA:
				readFaceBlock(binData, id, true);
				break;
			case BLOCK_ID_FACE_NOT_TRANS:
				readFaceBlock(binData, id, true);
				break;
			case BLOCK_ID_FACE_UNKNOWN_0E:
				readFaceBlock(binData, id, true);
				break;
			case BLOCK_ID_FACE_COLOR:
				readFaceBlock(binData, id, false);
				break;
			case BLOCK_ID_FACE_UNKNOWN_05:
				readFaceBlock(binData, id, false);
				break;
			case BLOCK_ID_ROTOR:
				readRotorBlock();
				break;
			case BLOCK_ID_UNKNOWN_12:
				readUnknown12Block();
				break;
			case BLOCK_ID_UNKNOWN_0C:
				readUnknown0CBlock();
				break;
			case BLOCK_ID_NORMALS:
				readNormalsBlock(binData);
				break;
			case BLOCK_ID_COLOR:
				readColorBlock(binData);
				break;
			case BLOCK_ID_STOP:
				return;
			default: {
				String msg = "Unknown block id (" + id + ") found at " + (charCount - 4) + ".";
				throw new FileLoadException(msg);
			}
			}
		}
	}

	private void readUnknown0CBlock() throws IOException {
		read4(6);
	}

	private void readUnknown12Block() throws IOException {
		read4(1);
	}

	private void readRotorBlock() throws IOException {
		read4(34);
	}

	private void readColorBlock(BinData binData) throws IOException {
		// color block is a four byte color
		read4(1);
	}

	private void readNormalsBlock(BinData binData) throws IOException {
		// XXX read them as hey are there but why?
		read4(2);

		for (int i = 0; i < binData.getVertexCount(); i++) {
			IBinVertex vertex = readVertex();
			binData.addNormal(vertex);
		}
	}

	private void readFaceBlock(BinData binData, int faceId, boolean readUV) throws IOException {
		BinFace binFace = new BinFace();
		binFace.setFaceId(faceId);

		read4(1);
		int vertexCount = makeInt(buffer4);

		BinVertex normalVertex = readVertex();
		binFace.setNormal(normalVertex);

		read4(1);

		for (int i = 0; i < vertexCount; i++) {
			BinTexCoord binTexCoord = new BinTexCoord();
			read4(1);
			int index = makeInt(buffer4);
			binTexCoord.setVertexIndex(index);

			if (readUV) {
				// coords U and V of the texture
				read4(1);
				binTexCoord.setU(makeInt(buffer4) / (float) 0xFF0000);
				read4(1);
				binTexCoord.setV(makeInt(buffer4) / (float) 0xFF0000);
			}

			binFace.addTexCoord(binTexCoord);
		}

		if (readUV) {
			binFace.setTextureName(lastTextureName);
		}

		binData.addFace(binFace);
	}

	private void read4(int amount) throws IOException {
		for (int i = 0; i < amount; i++) {
			charCount += is.read(buffer4);
		}
	}

	private void readTextureBlock(BinData binData) throws IOException {
		// 4 bytes of unknown purpose
		read4(1);

		// 16 bytes of texture name
		byte[] nameBuffer = new byte[16];
		is.read(nameBuffer);
		String name = Util.makeString(nameBuffer);
		lastTextureName = name;
		binData.addTextureName(name);
	}

	private void readAnimatedTextureBlock() throws IOException {
		// XXX just to be there
		read4(1);

		read4(1);
		int texturesCount = makeInt(buffer4);

		read4(4);

		for (int i = 0; i < texturesCount; i++) {
			read4(8);
		}
	}

	private void readVertexes(BinData binData) throws IOException {

		for (int i = 0; i < binData.getVertexCount(); i++) {
			BinVertex vertex = readVertex();
			binData.addVertex(vertex);
		}
	}

	private BinVertex readVertex() throws IOException {
		read4(1);
		int x = makeSignedInt(buffer4);

		read4(1);
		int y = makeSignedInt(buffer4);

		read4(1);
		int z = makeSignedInt(buffer4);

		BinVertex vertex = new BinVertex(x, y, z);
		return vertex;
	}

	private int makeInt(byte[] buffer) {
		int ret = 0;

		for (int i = 0; i < buffer.length; i++) {
			ret += Util.unsignedByteToInt(buffer[i]) * Math.pow(2, i * 8);
		}
		return ret;
	}

	private int makeSignedInt(byte[] buffer) {
		return (buffer[3] << 24)
		+ ((buffer[2] & 0xFF) << 16)
		+ ((buffer[1] & 0xFF) << 8)
		+ (buffer[0] & 0xFF);
	}

	public IBinData load(InputStream is) throws FileLoadException {
		this.is = is;
		BinData binData = new BinData();

		charCount = 0;

		try {
			// we start with 4 bytes of bin file id
			read4(1);
			int id = makeInt(buffer4);
			binData.setId(id);
			if (id != IBinData.ID_MODEL) {
				System.err.println("BinDataLoader: This a not a static model, not supported yet.");
				return binData;
			}

			// header starts with 12 bytes we ignore by now
			read4(3);

			// continues with 4 bytes for the number of contained vertexes
			read4(1);
			int vertexCount = makeInt(buffer4);
			binData.setVertexCount(vertexCount);

			// then there come vertexes where each's size is 12 bytes
			readVertexes(binData);

			// finally the
			readBlocks(binData);

		} catch (FileNotFoundException e) {
			throw new FileLoadException(e);
		} catch (IOException e) {
			throw new FileLoadException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}

		return binData;
	}
}
