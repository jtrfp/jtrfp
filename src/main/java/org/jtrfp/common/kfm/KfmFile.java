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
package org.jtrfp.common.kfm;

import java.io.File;

import org.jtrfp.common.FileLoadException;
import org.jtrfp.common.internal.kfm.KfmDataLoader;



public class KfmFile {

	private final File file;

	private IKfmData data;

	public KfmFile(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public IKfmData getData() throws FileLoadException {
		if (data == null) {
			KfmDataLoader loader = new KfmDataLoader();
			data = loader.load(this);
		}

		return data;
	}

	@Override
	public String toString() {
		return getFile().getName();
	}


}
