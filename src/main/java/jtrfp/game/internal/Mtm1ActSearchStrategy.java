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
package jtrfp.game.internal;

import jtrfp.common.FileLoadException;
import jtrfp.common.act.IActPodFileEntry;
import jtrfp.common.pod.IPodFileEntry;
import jtrfp.common.pod.PodFile;
import jtrfp.common.raw.IRawPodFileEntry;
import jtrfp.game.ITriGameDir;

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
