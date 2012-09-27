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
package jtrfp.common.internal.trk;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import jtrfp.common.FileLoadException;
import jtrfp.common.Vertex3f;
import jtrfp.common.trk.ITrkData;
import jtrfp.common.trk.TrkFile;


public class TrkDataLoader {

	private TrkDataLoader() {
	}

	public static ITrkData load(TrkFile trkFile) throws FileLoadException {
		try {
			return load(new FileInputStream(trkFile.getFile()));
		} catch (FileNotFoundException e) {
			throw new FileLoadException(e);
		}
	}

	private static Vertex3f makeVertex(String line) throws FileLoadException {
		String[] dimensions = line.split(",");
		if (dimensions.length != 3) {
			throw new FileLoadException("Cannot convert '" + line + "' to vertex.");
		}

		Vertex3f vertex = new Vertex3f(
				Float.parseFloat(dimensions[0]),
				Float.parseFloat(dimensions[1]),
				Float.parseFloat(dimensions[2])
		);

		return vertex;
	}

	private static void nextLineIs(BufferedReader reader, String string) throws IOException, FileLoadException {
		boolean equal = string.equals(reader.readLine());
		if (!equal) {
			throw new FileLoadException("Syntax error: line '" + string + "' not found.");
		}
	}

	public static ITrkData load(InputStream is) throws FileLoadException {
		TrkData trkData = new TrkData();

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			// 1
			nextLineIs(reader, "MTM2 truckName");
			trkData.setTruckName(reader.readLine());

			// 2
			nextLineIs(reader, "truckModelBaseName");
			trkData.setTruckModelBaseName(reader.readLine());

			// 3
			nextLineIs(reader, "tireModelBaseName");
			trkData.setTireModelBaseName(reader.readLine());

			// 4
			nextLineIs(reader, "axleModelName");
			trkData.setAxleModelName(reader.readLine());

			// 5
			nextLineIs(reader, "shockTextureName");
			trkData.setShockTextureName(reader.readLine());

			// 6
			nextLineIs(reader, "barTextureName");
			trkData.setBarTextureName(reader.readLine());

			// 7
			nextLineIs(reader, "axlebarOffset");
			trkData.setAxlebarOffset(makeVertex(reader.readLine()));

			// 8
			nextLineIs(reader, "driveshaftPos");
			trkData.setDriveshaftPos(makeVertex(reader.readLine()));

			// 9
			nextLineIs(reader, "faxle.rtire.static_bpos.x");
			float frx = Float.parseFloat(reader.readLine());

			// 10
			nextLineIs(reader, "faxle.ltire.static_bpos.x");
			float flx = Float.parseFloat(reader.readLine());

			// 11
			nextLineIs(reader, "raxle.rtire.static_bpos.x");
			float rrx = Float.parseFloat(reader.readLine());

			// 12
			nextLineIs(reader, "raxle.ltire.static_bpos.x");
			float rlx = Float.parseFloat(reader.readLine());

			// 13
			nextLineIs(reader, "faxle.rtire.static_bpos.y");
			float fry = Float.parseFloat(reader.readLine());

			// 14
			nextLineIs(reader, "faxle.ltire.static_bpos.y");
			float fly = Float.parseFloat(reader.readLine());

			// 15
			nextLineIs(reader, "raxle.rtire.static_bpos.y");
			float rry = Float.parseFloat(reader.readLine());

			// 16
			nextLineIs(reader, "raxle.ltire.static_bpos.y");
			float rly = Float.parseFloat(reader.readLine());

			// 17
			nextLineIs(reader, "faxle.rtire.static_bpos.z");
			float frz = Float.parseFloat(reader.readLine());

			// 18
			nextLineIs(reader, "faxle.ltire.static_bpos.z");
			float flz = Float.parseFloat(reader.readLine());

			// 19
			nextLineIs(reader, "raxle.rtire.static_bpos.z");
			float rrz = Float.parseFloat(reader.readLine());

			// 20
			nextLineIs(reader, "raxle.ltire.static_bpos.z");
			float rlz = Float.parseFloat(reader.readLine());

			trkData.setLeftFrontTirePos(new Vertex3f(flx, fly, flz));
			trkData.setLeftRearTirePos(new Vertex3f(rlx, rly, rlz));
			trkData.setRightFrontTirePos(new Vertex3f(frx, fry, frz));
			trkData.setRightRearTirePos(new Vertex3f(rrx, rry, rrz));

		} catch (FileNotFoundException e) {
			throw new FileLoadException(e);
		} catch (IOException e) {
			throw new FileLoadException(e);
		}

		return trkData;
	}
}
