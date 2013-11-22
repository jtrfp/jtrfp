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
package org.jtrfp.common.trk;

import java.io.File;

import org.jtrfp.common.FileLoadException;
import org.jtrfp.common.internal.trk.TrkDataLoader;



public class TrkFile {

	private final File file;

	private ITrkData trkData;

	public TrkFile(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public ITrkData getData() throws FileLoadException {
		if (trkData == null) {
			trkData = TrkDataLoader.load(this);
		}

		return trkData;
	}
}
