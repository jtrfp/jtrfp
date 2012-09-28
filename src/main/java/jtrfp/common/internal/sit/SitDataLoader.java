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
package jtrfp.common.internal.sit;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import jtrfp.common.FileLoadException;
import jtrfp.common.Vertex3f;
import jtrfp.common.sit.ISitData;
import jtrfp.common.sit.SitBox;
import jtrfp.common.sit.SitDataKeys;
import jtrfp.common.sit.SitFile;


public final class SitDataLoader {

	private SitDataLoader() {
	}

	public static ISitData load(SitFile sitFile) throws FileLoadException {
		try {
			return load(new FileInputStream(sitFile.getFile()));
		} catch (FileNotFoundException e) {
			throw new FileLoadException(e);
		}
	}

	private static void nextLineIs(BufferedReader reader, String string) throws  FileLoadException {
		try {
			boolean equal = nextLineOk(reader, string);
			if (!equal) {
				throw new FileLoadException("Syntax error: line '" + string + "' not found.");
			}
		} catch (IOException e) {
			throw new FileLoadException(e);
		}
	}

	private static boolean nextLineOk(BufferedReader reader, String string)
	throws IOException {
		String line = reader.readLine();
		boolean equal = string.equals(line);
		return equal;
	}

	public static ISitData load(InputStream sfis) throws FileLoadException {
		SitData sitData = new SitData();

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(sfis));

			// 1
			sitData.setValue(SitDataKeys.LEVEL_FILE_NAME, reader.readLine());
			// 2
			nextLineIs(reader, "!Race Track Name");
			sitData.setValue(SitDataKeys.RACE_TRACK_NAME, reader.readLine());
			// 3
			nextLineIs(reader, "Race Track Locale");

			seekToLine(reader, "*** Ramps ***");

			seekToLine(reader, "*** Boxes ***");
			int numBoxes = Integer.parseInt(reader.readLine());

			for (int i = 0; i < numBoxes; i++) {
				nextLineIs(reader, "*********************************************");
				SitBox box = new SitBox();

				Vertex3f pos = readBoxPosition(reader, i);
				box.setPosition(pos);

				readRotation(reader, i, box);

				if ("model" == reader.readLine()) {
					box.setModelName(reader.readLine());
				} else {
					reader.readLine();
				}

				sitData.addBox(box);

				// the rest
				for (int j = 0; j < 14; j++) {
					reader.readLine();
				}
			}

			seekToLine(reader, "*** Cylinders ***");

			seekToLine(reader, "*** Top Crush ***");

			seekToLine(reader, "*** Course ***");

			seekToLine(reader, "*** Stadium ***");

			seekToLine(reader, "*** Backdrop ***");

			reader.close();
		} catch (IOException e) {
			throw new FileLoadException(e);
		} finally {
			// TODO close stream
		}

		return sitData;
	}

	private static void readRotation(BufferedReader reader, int i, SitBox box) throws FileLoadException, IOException {
		nextLineIs(reader, "theta,phi,psi");
		String line = reader.readLine();
		String[] split = line.split(",");
		if (split.length != 3) {
			throw new FileLoadException("Box " + i + " has invalid rotation angles.");
		}

		box.setTheta(Float.parseFloat(split[0]));
		box.setPhi(Float.parseFloat(split[1]));
		box.setPsi(Float.parseFloat(split[2]));
	}

	private static Vertex3f readBoxPosition(BufferedReader reader, int i) throws IOException, FileLoadException {
		nextLineIs(reader, "ipos");
		String line = reader.readLine();
		String[] split = line.split(",");
		if (split.length != 3) {
			throw new FileLoadException("Box " + i + " has invalid position coordinates.");
		}

		return new Vertex3f(
				Float.parseFloat(split[0]),
				Float.parseFloat(split[1]),
				Float.parseFloat(split[2]));
	}

	private static void seekToLine(BufferedReader reader, String line) throws IOException {
		while (!nextLineOk(reader, line));
	}
}
