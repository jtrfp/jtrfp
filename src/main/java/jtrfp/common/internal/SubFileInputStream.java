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
package jtrfp.common.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SubFileInputStream extends InputStream {

	private int available;

	private final int length;

	private final FileInputStream fis;

	public SubFileInputStream(File file, long offset, int length) throws IOException {
		super();

		this.length = length;

		fis = new FileInputStream(file);

		fis.skip(offset);
		available = length;
	}

	@Override
	public long skip(long n) throws IOException {
		return 0;
	}
	@Override
	public synchronized void reset() throws IOException {
		available = length;
	}

	@Override
	public int available() throws IOException {
		return available;
	}

	@Override
	public int read() throws IOException {
		if (available <= 0) {
			return -1;
		}
		int read = fis.read();
		available -= 1;
		return read;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		if (available <= 0) {
			return -1;
		}
		if (len > available) {
			len = available;
		}
		int read = fis.read(b, off, len);
		available -= read;
		return read;
	}

	@Override
	public int read(byte[] b) throws IOException {
		if (available <= 0) {
			return -1;
		}
		int read = fis.read(b);
		available -= read;
		return read;
	}
}
