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
package org.jtrfp.common.internal.pod;

import java.io.IOException;
import java.io.InputStream;

import org.jtrfp.common.internal.act.ActPodFileEntry;
import org.jtrfp.common.internal.audio.ModPodFileEntry;
import org.jtrfp.common.internal.audio.WavPodFileEntry;
import org.jtrfp.common.internal.bin.BinPodFileEntry;
import org.jtrfp.common.internal.clr.ClrPodFileEntry;
import org.jtrfp.common.internal.image.BmpPodFileEntry;
import org.jtrfp.common.internal.image.TifPodFileEntry;
import org.jtrfp.common.internal.kfm.KfmPodFileEntry;
import org.jtrfp.common.internal.lvl.LvlPodFileEntry;
import org.jtrfp.common.internal.raw.RawPodFileEntry;
import org.jtrfp.common.internal.sit.SitPodFileEntry;
import org.jtrfp.common.internal.tex.TexPodFileEntry;
import org.jtrfp.common.internal.trk.TrkPodFileEntry;
import org.jtrfp.common.pod.PodFile;



public abstract class AbstractPodLoader {

	private final InputStream is;

	private final PodFile podFile;

	protected final byte[] buffer4 = new byte[4];

	private long count;

	public AbstractPodLoader(InputStream is, PodFile podFile) {
		this.is = is;
		this.podFile = podFile;
		count = 4;
	}

	//	protected InputStream getIs() {
	//		return is;
	//	}

	protected PodFile getPodFile() {
		return podFile;
	}

	protected void read(byte[] buffer) throws IOException {
		count += is.read(buffer);
	}

	protected int read() throws IOException {
		int read = is.read();
		if (read > -1) {
			count++;
		}
		return read;
	}

	protected void read4(int amount) throws IOException {
		for (int i = 0; i < amount; i++) {
			count += is.read(buffer4);
		}
	}

	abstract public PodData load() throws IOException;

	protected void seekForwardTo(int offset) throws IOException {
		if (offset <= count) {
			return;
		}
		count += is.skip(offset - count);
	}

	protected PodFileEntry createPodFileEntry(PodData podData, String path) {
		String localPath = path.toLowerCase();
		if (localPath.endsWith(".act")) {
			return new ActPodFileEntry(podFile);
		} else if (localPath.endsWith(".raw")) {
			return new RawPodFileEntry(podFile);
		} else if (localPath.endsWith(".sit")) {
			return new SitPodFileEntry(podFile);
		} else if (localPath.endsWith(".trk")) {
			return new TrkPodFileEntry(podFile);
		} else if (localPath.endsWith(".bmp")) {
			return new BmpPodFileEntry(podFile);
		} else if (localPath.endsWith(".wav")) {
			return new WavPodFileEntry(podFile);
		} else if (localPath.endsWith(".bin")) {
			return new BinPodFileEntry(podFile);
		} else if (localPath.endsWith(".lvl")) {
			return new LvlPodFileEntry(podFile);
		} else if (localPath.endsWith(".mod")) {
			return new ModPodFileEntry(podFile);
		} else if (localPath.endsWith(".tex")) {
			return new TexPodFileEntry(podFile);
		} else if (localPath.endsWith(".tif")) {
			return new TifPodFileEntry(podFile);
		} else if (localPath.endsWith(".clr")) {
			return new ClrPodFileEntry(podFile);
		} else if (localPath.endsWith(".kfm")) {
			return new KfmPodFileEntry(podFile);
		}

		PodFileEntry podFileEntry = new PodFileEntry(podFile);
		podData.addUntypedEntry(podFileEntry);
		return podFileEntry;
	}
}
