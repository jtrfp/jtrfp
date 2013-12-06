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
package org.jtrfp.jtrfp.lvl;

import org.jtrfp.jtrfp.DataKey;

public class LvlDataKeys {

	public static final DataKey COLOR_MAP_NAME = new DataKey("colorMapName", "Color map");

	public static final DataKey HEIGHT_MAP_NAME = new DataKey("heightMapName", "Height map");

	public static final DataKey TEXTURE_LIST_NAME = new DataKey("textureListName", "Texture list");

	public static final DataKey COLOR_TABLE_NAME = new DataKey("colorTableName", "Color table");

	public static DataKey[] getAll() {
		return new DataKey[] {
				COLOR_MAP_NAME,
				COLOR_TABLE_NAME,
				HEIGHT_MAP_NAME,
				TEXTURE_LIST_NAME
		};
	}
}
