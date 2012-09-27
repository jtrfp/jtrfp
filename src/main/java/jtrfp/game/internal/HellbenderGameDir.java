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


public final class HellbenderGameDir extends AbstractTriGameDir implements ITriGameDir {

	private static final String EXE_FILE_NAME = "hellbend.exe";

	private static final String NAME = "Hellbender";

	private HellbenderGameDir(File baseDir) {
		super(baseDir, NAME);
	}

	public static HellbenderGameDir create(File dir) {

		if (dir == null || !dir.exists() || !dir.isDirectory()) {
			return null;
		}

		File exe = new File(dir, EXE_FILE_NAME);
		if (isHellbenderExe(exe)) {
			return new HellbenderGameDir(dir);
		}

		return null;
	}

	private static boolean isHellbenderExe(File exeFile) {
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
				new File(getDirectory(), "system/hellbend.ini")
		};
		return files;
	}

	@Override
	protected File[] getPodDirectories() {
		return new File[] { new File(getDirectory(), "system") };
	}

	@Override
	public File getExeFile() {
		return new File(getDirectory(), EXE_FILE_NAME);
	}
}
