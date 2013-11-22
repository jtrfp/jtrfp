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
package org.jtrfp.game.internal;

import org.jtrfp.common.FileLoadException;
import org.jtrfp.common.act.IActPodFileEntry;
import org.jtrfp.common.pod.IPodFileEntry;
import org.jtrfp.common.pod.PodFile;
import org.jtrfp.common.raw.IRawPodFileEntry;
import org.jtrfp.game.ITriGameDir;

public class Mtm2ActSearchStrategy extends DefaultActSearchStrategy {

	public Mtm2ActSearchStrategy(ITriGameDir gameDir) {
		super(gameDir);
	}

	@Override
	public IActPodFileEntry find(IRawPodFileEntry rawEntry) {
		try {
			IPodFileEntry entry = super.find(rawEntry);
			if (entry == null) {
				PodFile gamePod = getGameDir().findPodFile("STARTUP.POD");
				if (gamePod == null) {
					return null;
				}

				String search = "art\\metalcr2.act";;
				entry = gamePod.getData().findEntry(search);
			}

			if (entry != null && entry instanceof IActPodFileEntry) {
				return (IActPodFileEntry) entry;
			}

			return null;
		} catch (FileLoadException e) {
			return null;
		}
	}


}
