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
package org.jtrfp.jtrfp.lst;

import java.io.File;
import java.util.ArrayList;

import org.jtrfp.jtrfp.FileLoadException;
import org.jtrfp.jtrfp.FileStoreException;
import org.jtrfp.jtrfp.internal.lst.LstDataLoader;
import org.jtrfp.jtrfp.internal.lst.LstDataStorer;
import org.jtrfp.jtrfp.pod.PodFile;



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
