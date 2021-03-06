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
package org.jtrfp.jtrfp.internal;

import java.util.HashMap;
import java.util.Map;

import org.jtrfp.jtrfp.DataKey;
import org.jtrfp.jtrfp.IKeyBasedData;


public class KeyBasedData implements IKeyBasedData {

	private Map<DataKey, String> data;

	public KeyBasedData() {
		data = new HashMap<DataKey, String>();
	}

	@Override
	public DataKey[] getUsedKeys() {
		return data.keySet().toArray(new DataKey[0]);
	}

	@Override
	public String getValue(DataKey key) {
		return data.get(key);
	}

	public void setValue(DataKey key, String value) {
		data.put(key, value);
	}
}
