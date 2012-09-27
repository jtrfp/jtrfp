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
package jtrfp.common.pod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jtrfp.common.FileLoadException;
import jtrfp.common.FileStoreException;
import jtrfp.common.internal.pod.Pod1Writer;
import jtrfp.common.internal.pod.PodDataLoader;


/**
 * Abstract representation for POD file.
 * @author Stefan Teitge
 */
public class PodFile {

	private final File file;

	private IPodData data;

	/**
	 * Constructs a POD file from a file in the file system.
	 * @param file the file
	 */
	public PodFile(File file) {
		this.file = file;
	}

	public static PodFile createFromLst(File directory, PodLstFile lstFile, File outFile, PodDataFormat format) throws FileStoreException {
		if (format != PodDataFormat.POD1) {
			throw new FileStoreException("Ony POD1 files supported yet.");
		}
		
		List<File> files = new ArrayList<File>();
		
		for (String path : lstFile.getEntries()) {
			files.add(new File(directory, path));
		}
		
		Pod1Writer.write(files.toArray(new File[0]), outFile);
		
		return new PodFile(outFile);
	}
	
	/**
	 * Returns the file this POD file loads data from.
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Return an abstract representation of the POD file's contents.
	 * @return the abstract representation
	 * @throws FileLoadException if file is malformed or reading fails.
	 */
	public IPodData getData() throws FileLoadException {
		if (data == null) {
			PodDataLoader pdl = new PodDataLoader(this);
			data = pdl.load();
		}

		return data;
	}

	@Override
	public String toString() {
		return file.getName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PodFile other = (PodFile) obj;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		return true;
	}
}
