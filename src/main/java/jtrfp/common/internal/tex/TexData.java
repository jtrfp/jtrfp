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
package jtrfp.common.internal.tex;

import java.util.ArrayList;
import java.util.List;

import jtrfp.common.tex.ITexData;


public class TexData implements ITexData {

	private final List<String> names;

	public TexData() {
		names = new ArrayList<String>();
	}

	@Override
	public int getTextureCount() {
		return names.size();
	}

	@Override
	public String[] getTextureNames() {
		return names.toArray(new String[]{});
	}

	public void addTextureName(String name) {
		names.add(name);
	}
}
