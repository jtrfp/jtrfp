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
package jtrfp.common.pod;

import java.io.File;

import jtrfp.common.FileStoreException;
import jtrfp.common.raw.IRawPodFileEntry;


/**
 * Abstract representation of POD file's contents.
 * @author Stefan Teitge
 */
public interface IPodData {

	/**
	 * Get all entries of the POD file.
	 * @return an array of containing all entries.
	 */
	IPodFileEntry[] getEntries();

	/**
	 * Return the comment of the POD file.
	 * @return the comment
	 */
	String getComment();

	/**
	 * Return the number of entries.
	 * @return the number of entries
	 */
	int getEntryCount();

	PodDataFormat getFormat();
	
	/**
	 * Searches an entry using its path.
	 * @param path the path
	 * @return the entry, otherwise null
	 */
	IPodFileEntry findEntry(String path);

	/**
	 * Searches entries by type.
	 * @param clazz the class representing this type
	 * @return all entries found
	 * @see IActPodFileEntry, IBinPodFileEntry, ...
	 */
	<T extends IPodFileEntry> T[] findEntries(Class<T> clazz);

	/**
	 * Return all entries having no specialized type assigned.
	 * Means: All entries only implementing IPodFileEntry.
	 * @return the untyped entries
	 */
	IPodFileEntry[] getUntypedEntries();

	/**
	 * Searches a color table for a given RAW file.
	 * @param podFileEntry
	 * @return the color table, if not found null
	 * @deprecated
	 */
	@Deprecated
	IPodFileEntry findColorTable(IRawPodFileEntry podFileEntry);

	/**
	 * Extracts all contained files to specified directory.
	 * @param extractDir the target directory
	 * @throws FileStoreException if writing contained files fails.
	 */
	void extractAll(File extractDir) throws FileStoreException;

	int getChecksum();
}
