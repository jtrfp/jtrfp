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
package org.jtrfp.jtrfp.game.internal;

import org.jtrfp.jtrfp.FileLoadException;
import org.jtrfp.jtrfp.act.IActPodFileEntry;
import org.jtrfp.jtrfp.game.ITriGameDir;
import org.jtrfp.jtrfp.pod.IPodFileEntry;
import org.jtrfp.jtrfp.pod.PodFile;
import org.jtrfp.jtrfp.raw.IRawPodFileEntry;

public class Mtm1ActSearchStrategy extends DefaultActSearchStrategy {

	public Mtm1ActSearchStrategy(ITriGameDir gameDir) {
		super(gameDir);
	}

	@Override
	public IActPodFileEntry find(IRawPodFileEntry rawEntry) {
		try {
			IPodFileEntry entry = super.find(rawEntry);
			if (entry == null) {
				PodFile gamePod = getGameDir().findPodFile("game.pod");
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
