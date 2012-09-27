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
package jtrfp.common.lst;

import java.io.File;
import java.util.ArrayList;

import jtrfp.common.FileLoadException;
import jtrfp.common.FileStoreException;
import jtrfp.common.internal.lst.LstDataLoader;
import jtrfp.common.internal.lst.LstDataStorer;
import jtrfp.common.pod.PodFile;


public class LstData implements ILstData {

	private final ArrayList<String> entries;

	public LstData() {
		entries = new ArrayList<String>();
	}

	@Override
	public String[] getEntries() {
		return entries.toArray(new String[]{});
	}

	@Override
	public int getEntryCount() {
		return entries.size();
	}

	public void addEntry(String entry) {
		entries.add(entry);
	}

	public void toFile(File file) throws FileStoreException {
		LstDataStorer.store(file, this);
	}

	public static LstData fromPodFile(PodFile podFile) throws FileLoadException {
		return LstDataLoader.load(podFile);
	}
}
