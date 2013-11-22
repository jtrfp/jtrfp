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
import org.jtrfp.common.raw.IRawPodFileEntry;
import org.jtrfp.game.IActSearchStrategy;
import org.jtrfp.game.ITriGameDir;

public class DefaultActSearchStrategy implements IActSearchStrategy {

	private final ITriGameDir gameDir;

	public DefaultActSearchStrategy(ITriGameDir gameDir) {
		this.gameDir = gameDir;
	}

	protected ITriGameDir getGameDir() {
		return gameDir;
	}

	public IActPodFileEntry find(IRawPodFileEntry rawEntry) {
		try {
			String search = rawEntry.getPath().toLowerCase().replace(".raw", ".act");
			IPodFileEntry entry = rawEntry.getPodFile().getData().findEntry(search);
			if (entry != null && entry instanceof IActPodFileEntry) {
				return (IActPodFileEntry) entry;
			}
		} catch (FileLoadException e) {
			return null;
		}

		return null;
	}

}
