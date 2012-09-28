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
import java.io.IOException;
import java.io.InputStream;

import jtrfp.common.FileStoreException;


/**
 * Abstract representation for a file contained in a POD file.
 * @author Stefan Teitge
 */
public interface IPodFileEntry {

	/**
	 * Returns the offset the entry begins within the POD file.
	 * @return the offset.
	 */
	long getOffset();

	/**
	 * Return the index of the entry.
	 * @return the index (starting from 0)
	 */
	int getIndex();

	/**
	 * Returns the relative path of the entry within the POD file.
	 * @return the path
	 */
	String getPath();

	/**
	 * Return the size of the entry in bytes.
	 * @return the size
	 */
	int getSize();

	/**
	 * Return the POD file this entry is contained in.
	 * @return the POD file
	 */
	PodFile getPodFile();

	/**
	 * Writes the contents of the entry to a file.
	 * @param file the file
	 * @throws FileStoreException if writing fails.
	 */
	void toFile(File file) throws FileStoreException;

	InputStream getInputStreamFromPod() throws IOException;
}
