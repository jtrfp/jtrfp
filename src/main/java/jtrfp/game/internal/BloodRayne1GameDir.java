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
package jtrfp.game.internal;

import java.io.File;

import jtrfp.game.ITriGameDir;


public final class BloodRayne1GameDir extends AbstractTriGameDir implements ITriGameDir {

	private static final String EXE_FILE_NAME = "rayne.exe";

	private static final String NAME = "BloodRayne";

	private final File directory;

	private BloodRayne1GameDir(File baseDir) {
		super(baseDir, NAME);
		directory = baseDir;
	}

	public static BloodRayne1GameDir create(File dir) {

		if (dir == null || !dir.exists() || !dir.isDirectory()) {
			return null;
		}

		File mtm2Exe = new File(dir, EXE_FILE_NAME);
		if (isBloodRayneExe(mtm2Exe) && hasPcengvoxPod(dir)) {
			return new BloodRayne1GameDir(dir);
		}

		return null;
	}

	private static boolean isBloodRayneExe(File exeFile) {
		if (!exeFile.exists() || !exeFile.isFile()) {
			return false;
		}
		return true;
	}

	private static boolean hasPcengvoxPod(File dir) {
		File gamePod = new File(dir, "PCENGVOX.POD");

		if (!gamePod.exists() || !gamePod.isFile()) {
			return false;
		}
		return true;
	}

	@Override
	public File[] getIniFiles() {
		File[] files = new File[] {
				new File(directory, "system/rayne.ini"),
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
