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
package jtrfp.common.internal.pod;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jtrfp.common.FileStoreException;
import jtrfp.common.pod.IPodData;
import jtrfp.common.pod.IPodFileEntry;
import jtrfp.common.pod.PodDataFormat;
import jtrfp.common.pod.PodFile;
import jtrfp.common.raw.IRawPodFileEntry;


public class PodData implements IPodData {

	private final List<PodFileEntry> entries;

	private String comment;

	private final List<IPodFileEntry> untypedEntries;

	private final HashMap<Class<?>, IPodFileEntry[]> entryGroups;

	private final PodFile podFile;

	private int checksum;

	private final PodDataFormat format;

	public PodData(PodFile podFile, PodDataFormat format) {
		this.podFile = podFile;
		this.format = format;
		entries = new ArrayList<PodFileEntry>();
		untypedEntries = new ArrayList<IPodFileEntry>();
		entryGroups = new HashMap<Class<?>, IPodFileEntry[]>();
	}

	@Override
	public IPodFileEntry[] getEntries() {
		return entries.toArray(new PodFileEntry[]{});
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public int getEntryCount() {
		return entries.size();
	}

	@Override
	public PodDataFormat getFormat() {
		return format;
	}
	
	public PodFile getPodFile() {
		return podFile;
	}

	@Override
	public IPodFileEntry findEntry(String path) {
		for (IPodFileEntry entry : entries) {
			boolean isPath = entry.getPath().equalsIgnoreCase(path);
			if (isPath) {
				return entry;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends IPodFileEntry> T[] findEntries(Class<T> clazz) {
		T[] filteredEntries = (T[])entryGroups.get(clazz);
		if (filteredEntries != null) {
			return filteredEntries;
		}

		List<IPodFileEntry> results = new ArrayList<IPodFileEntry>();
		for (IPodFileEntry entry : entries) {
			if (clazz.isAssignableFrom(entry.getClass())) {
				results.add(entry);
			}
		}

		T[] resultArray = results.toArray((T[]) Array.newInstance(clazz, 0));
		entryGroups.put(clazz, resultArray);
		return resultArray;
	}

	@Override
	public IPodFileEntry findColorTable(IRawPodFileEntry rawEntry) {
		String search = rawEntry.getPath().toLowerCase().replace(".raw", ".act");
		for (int i = 0; i < entries.size(); i++) {
			if (search.equalsIgnoreCase(entries.get(i).getPath())) {
				return entries.get(i);
			}
		}

		return null;
	}

	@Override
	public IPodFileEntry[] getUntypedEntries() {
		return untypedEntries.toArray(new IPodFileEntry[]{});
	}

	@Override
	public void extractAll(File extractDir) throws FileStoreException {
		if (extractDir == null || !extractDir.exists() || !extractDir.isDirectory()) {
			throw new IllegalArgumentException("Invalid directory specified");
		}

		for (IPodFileEntry entry : entries) {
			entry.toFile(new File(extractDir, entry.getPath()));
		}
	}

	protected void setComment(String comment) {
		this.comment = comment;
	}

	protected void addEntry(PodFileEntry entry) {
		entries.add(entry);
	}

	protected void addUntypedEntry(PodFileEntry entry) {
		untypedEntries.add(entry);
	}

	@Override
	public int getChecksum() {
		return checksum;
	}

	protected void setChecksum(int checksum) {
		this.checksum = checksum;
	}
}
