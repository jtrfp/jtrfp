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
package jtrfp.common.internal.act;

import java.io.IOException;

import jtrfp.common.FileLoadException;
import jtrfp.common.act.IActData;
import jtrfp.common.act.IActPodFileEntry;
import jtrfp.common.internal.pod.PodFileEntry;
import jtrfp.common.pod.PodFile;


public class ActPodFileEntry extends PodFileEntry implements IActPodFileEntry {

	public ActPodFileEntry(PodFile podFile) {
		super(podFile);
	}

	@Override
	public IActData getData() throws FileLoadException {
		try {
			return ActDataLoader.load(getInputStreamFromPod());
		} catch (IOException e) {
			throw new FileLoadException(e);
		}
	}
}
