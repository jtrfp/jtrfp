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
package org.jtrfp.jtrfp.game.internal;

import java.io.File;

import org.jtrfp.jtrfp.game.ITriGameDir;



public final class NocturneGameDir extends AbstractTriGameDir implements ITriGameDir {

	private static final String EXE_FILE_NAME = "nocturne.exe";

	private static final String NAME = "Nocturne";

	private final File directory;

	private NocturneGameDir(File baseDir) {
		super(baseDir, NAME);
		directory = baseDir;
	}

	public static NocturneGameDir create(File dir) {

		if (dir == null || !dir.exists() || !dir.isDirectory()) {
			return null;
		}

		File mtm2Exe = new File(dir, EXE_FILE_NAME);
		if (isNocturneExe(mtm2Exe) && hasGtownPod(dir)) {
			return new NocturneGameDir(dir);
		}

		return null;
	}

	private static boolean isNocturneExe(File exeFile) {
		if (!exeFile.exists() || !exeFile.isFile()) {
			return false;
		}
		return true;
	}

	private static boolean hasGtownPod(File dir) {
		File gamePod = new File(dir, "GTOWN.POD");

		if (!gamePod.exists() || !gamePod.isFile()) {
			return false;
		}
		return true;
	}

	@Override
	public File[] getIniFiles() {
		File[] files = new File[] {
				new File(directory, "system/nocturne.ini"),
				new File(directory, "system/RENDER.INI")
		};
		return files;
	}

	@Override
	protected File[] getPodDirectories() {
		return new File[] { getDirectory() };
	}

	@Override
	public File getExeFile() {
		return new File(getDirectory(), EXE_FILE_NAME);
	}
}
