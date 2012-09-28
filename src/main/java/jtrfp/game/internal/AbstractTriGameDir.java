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
package jtrfp.game.internal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jtrfp.common.pod.PodFile;
import jtrfp.game.IActSearchStrategy;
import jtrfp.game.ITriGameDir;

public abstract class AbstractTriGameDir implements ITriGameDir {

	private final File directory;

	private final String gameName;

	private IActSearchStrategy actSearchStrategy;

	public AbstractTriGameDir(File directory, String gameName) {
		this.directory = directory;
		this.gameName = gameName;
		actSearchStrategy = new DefaultActSearchStrategy(this);
	}

	@Override
	public IActSearchStrategy getActSearchStrategy() {
		return actSearchStrategy;
	}

	protected void setActSearchStrategy(IActSearchStrategy actSearchStrategy) {
		this.actSearchStrategy = actSearchStrategy;
	}

	@Override
	public final File getDirectory() {
		return directory;
	}

	@Override
	public final String getGameName() {
		return gameName;
	}

	@Override
	public final PodFile[] getPodFiles() {
		// TODO: cache?
		List<PodFile> podFiles = new ArrayList<PodFile>();
		for (File podDir : getPodDirectories()) {

			File[] files = podDir.listFiles(new PodFileFilter(getPodFileExtension()));

			if (files == null) {
				continue;
			}

			final int numFiles = files.length;
			for (int i = 0; i < numFiles; i++) {
				podFiles.add(new PodFile(files[i]));
			}
		}

		return podFiles.toArray(new PodFile[0]);
	}

	protected String getPodFileExtension() {
		return "pod";
	}

	@Override
	public final PodFile findPodFile(String name) {
		for (PodFile currPodFile : getPodFiles()) {
			if (currPodFile.getFile().getName().equalsIgnoreCase(name)) {
				return currPodFile;
			}
		}
		return null;
	}

	@Override
	public boolean hasPodFile(PodFile podFile) {
		PodFile[] files = getPodFiles();
		for (PodFile podFile2 : files) {
			if (podFile2.equals(podFile)) {
				return true;
			}
		}
		return false;
	}

	protected abstract File[] getPodDirectories();
}
