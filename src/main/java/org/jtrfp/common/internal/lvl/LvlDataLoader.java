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
package org.jtrfp.common.internal.lvl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.jtrfp.common.FileLoadException;
import org.jtrfp.common.lvl.ILvlData;
import org.jtrfp.common.lvl.LvlDataKeys;
import org.jtrfp.common.lvl.LvlFile;



public class LvlDataLoader {

	public static ILvlData load(LvlFile lvlFile) throws FileLoadException {
		try {
			return load(new FileInputStream(lvlFile.getFile()));
		} catch (FileNotFoundException e) {
			throw new FileLoadException(e);
		}
	}

	public static ILvlData load(InputStream is) throws FileLoadException {
		LvlData lvlData = new LvlData();

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is));

			// 1 - integer - use?
			// XXX this value is an integer in MTM2 and TV
			reader.readLine();

			// 2 - TXT - description?
			// XXX the referenced TXT file here is empty in MTM2 and contains level description in TV
			reader.readLine();

			// 3 - RAW - the height map name
			lvlData.setValue(LvlDataKeys.HEIGHT_MAP_NAME, reader.readLine());

			// 4 - CLR - the color map name
			lvlData.setValue(LvlDataKeys.COLOR_MAP_NAME, reader.readLine());

			// 5 - ACT - the color table name
			lvlData.setValue(LvlDataKeys.COLOR_TABLE_NAME, reader.readLine());

			// 6 - TEX - the texture list name
			lvlData.setValue(LvlDataKeys.TEXTURE_LIST_NAME, reader.readLine());

			// 7 - QKE or RAW - use?
			// XXX QKE in TV, RAW in MTM2
			reader.readLine();

			// 8 - PUP - use?
			reader.readLine();

			// 9 - ANI - use?
			reader.readLine();

			// 10 - TDF - use?
			reader.readLine();

			// 11 - RAW - skybox texture?
			reader.readLine();

			// 12 - ACT - skybox color table?
			reader.readLine();

			// 13 - DEF - use?
			reader.readLine();

			// 14 - NAV - use?
			reader.readLine();

			// 15 - MOV/WAV - use?
			// XXX MOD in TV, WAV in MTM2
			reader.readLine();

			// 16 - FOG - use?
			reader.readLine();

			// 17 - LTE - use?
			reader.readLine();

			// 18 - coords 3D - use?
			reader.readLine();

			// 19 - integer - use?
			reader.readLine();

			// 20 - coords 3D - use?
			reader.readLine();

			// 21 - integer - use?
			reader.readLine();

			// 22 - integer - use?
			reader.readLine();

			// XXX until here TV and MTM2 have the same format

			// TODO read others, but game specific

			reader.close();
		} catch (FileNotFoundException e) {
			throw new FileLoadException(e);
		} catch (IOException e) {
			throw new FileLoadException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}

		return lvlData;
	}

}
