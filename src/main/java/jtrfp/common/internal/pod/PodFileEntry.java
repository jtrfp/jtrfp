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
package jtrfp.common.internal.pod;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import jtrfp.common.FileStoreException;
import jtrfp.common.internal.SubFileInputStream;
import jtrfp.common.pod.IPodFileEntry;
import jtrfp.common.pod.PodFile;


public class PodFileEntry implements IPodFileEntry {

	private int index;

	private String path;

	private long offset;

	private int size;

	private final PodFile podFile;

	public PodFileEntry(PodFile podFile) {
		this.podFile = podFile;
	}

	@Override
	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	@Override
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return getPath();
	}

	@Override
	public PodFile getPodFile() {
		return podFile;
	}

	@Override
	public void toFile(File file) throws FileStoreException {
		FileOutputStream fos = null;
		try {
			boolean result = file.getParentFile().mkdirs();
			if (!result) {
				if (!file.getParentFile().exists()) {
					String msg = "Directory for POD file entry entry couln't be created.";
					throw new FileStoreException(msg);
				}
			}
			
			fos = new FileOutputStream(file);
			InputStream is = getInputStreamFromPod();
			int readCount = 0;
			
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			
			while (readCount < size) {
				int read = is.read(buffer, 0, bufferSize);
				fos.write(buffer, 0, read);
				readCount += read;
			}
			
			is.close();
			fos.close();
		} catch (FileNotFoundException e) {
			throw new FileStoreException(e);
		} catch (IOException e) {
			throw new FileStoreException(e);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}
	}

	public InputStream getInputStreamFromPod() throws IOException {
		return new SubFileInputStream(getPodFile().getFile(), getOffset(), getSize());
	}
}
