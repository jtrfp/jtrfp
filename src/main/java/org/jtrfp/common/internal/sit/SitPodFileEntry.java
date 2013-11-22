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
package org.jtrfp.common.internal.sit;

import java.io.IOException;

import org.jtrfp.common.FileLoadException;
import org.jtrfp.common.internal.pod.PodFileEntry;
import org.jtrfp.common.pod.PodFile;
import org.jtrfp.common.sit.ISitData;
import org.jtrfp.common.sit.ISitPodFileEntry;



public class SitPodFileEntry extends PodFileEntry implements ISitPodFileEntry {

	public SitPodFileEntry(PodFile podFile) {
		super(podFile);
	}

	@Override
	public ISitData getData() throws FileLoadException {
		try {
			return SitDataLoader.load(getInputStreamFromPod());
		} catch (IOException e) {
			throw new FileLoadException(e);
		}
	}
}
