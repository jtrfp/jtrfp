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
package org.jtrfp.jtrfp.sit;

import org.jtrfp.jtrfp.DataKey;

public class SitDataKeys {

	public static final DataKey LEVEL_FILE_NAME = new DataKey("levelFileName", "Level file name");

	public static final DataKey RACE_TRACK_NAME = new DataKey("raceTrackName", "Race track name");

	public static DataKey[] getAll() {
		return new DataKey[] {
				LEVEL_FILE_NAME,
				RACE_TRACK_NAME
		};
	}
}
