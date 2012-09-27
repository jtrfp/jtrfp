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
package jtrfp.common.lst;

import java.io.File;

import jtrfp.common.FileLoadException;
import jtrfp.common.internal.lst.LstDataLoader;


public class LstFile {

	private final File file;

	private ILstData data;

	public LstFile(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public ILstData getData() throws FileLoadException {
		if (data == null) {
			data = LstDataLoader.load(this);
		}

		return data;
	}

	@Override
	public String toString() {
		return getFile().getName();
	}
}
