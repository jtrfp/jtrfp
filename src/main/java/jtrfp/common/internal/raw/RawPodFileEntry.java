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
package jtrfp.common.internal.raw;

import java.io.IOException;

import jtrfp.common.FileLoadException;
import jtrfp.common.internal.pod.PodFileEntry;
import jtrfp.common.pod.PodFile;
import jtrfp.common.raw.IRawData;
import jtrfp.common.raw.IRawPodFileEntry;


public class RawPodFileEntry extends PodFileEntry implements IRawPodFileEntry {

	public RawPodFileEntry(PodFile podFile) {
		super(podFile);
	}

	@Override
	public IRawData getRawData() throws FileLoadException {
		try {
			return RawDataLoader.load(getInputStreamFromPod());
		} catch (IOException e) {
			throw new FileLoadException(e);
		}
	}

}
