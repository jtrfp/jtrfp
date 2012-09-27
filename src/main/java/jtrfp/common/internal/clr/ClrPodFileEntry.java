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
package jtrfp.common.internal.clr;

import java.io.IOException;

import jtrfp.common.FileLoadException;
import jtrfp.common.clr.IClrData;
import jtrfp.common.clr.IClrPodFileEntry;
import jtrfp.common.internal.pod.PodFileEntry;
import jtrfp.common.pod.PodFile;


public class ClrPodFileEntry extends PodFileEntry implements IClrPodFileEntry {

	public ClrPodFileEntry(PodFile podFile) {
		super(podFile);
	}

	@Override
	public IClrData getData() throws FileLoadException {
		try {
			return ClrDataLoader.load(getInputStreamFromPod());
		} catch (IOException e) {
			throw new FileLoadException(e);
		}
	}
}
