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


public final class Fly2GameDir extends AbstractTriGameDir implements ITriGameDir {

	private static final String EXE_FILE_NAME = "fly2.exe";

	private static final String NAME = "Fly! II";

	private Fly2GameDir(File baseDir) {
		super(baseDir, NAME);
	}

	public static Fly2GameDir create(File dir) {

		if (dir == null || !dir.exists() || !dir.isDirectory()) {
			return null;
		}

		File exe = new File(dir, EXE_FILE_NAME);
		if (isFlyExe(exe)) {
			return new Fly2GameDir(dir);
		}

		return null;
	}

	private static boolean isFlyExe(File exeFile) {
		if (exeFile == null) {
			return false;
		}

		if (!exeFile.exists() || !exeFile.isFile()) {
			return false;
		}

		return true;
	}

	@Override
	public File[] getIniFiles() {
		File[] files = new File[] {
				new File(getDirectory(), "system/fly.ini"),
				new File(getDirectory(), "system/render.ini")
		};
		return files;
	}

	@Override
	protected File[] getPodDirectories() {
		// TODO add all directories
		return new File[] { new File(getDirectory(), "system") };
	}

	@Override
	public File getExeFile() {
		return new File(getDirectory(), EXE_FILE_NAME);
	}
}
