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
package org.jtrfp.game.internal;

import java.io.File;

import org.jtrfp.game.ITriGameDir;



public final class Mtm1GameDir extends AbstractTriGameDir implements ITriGameDir {

	private static final String EXE_FILE_NAME = "monster.exe";
	private static final String NAME = "Monster Truck Madness 1";

	private Mtm1GameDir(File baseDir) {
		super(baseDir, NAME);
		setActSearchStrategy(new Mtm1ActSearchStrategy(this));
	}

	public static Mtm1GameDir create(File dir) {

		if (dir == null || !dir.exists() || !dir.isDirectory()) {
			return null;
		}

		File mtm1Exe = new File(dir, EXE_FILE_NAME);
		if (isMtm1Exe(mtm1Exe) && hasGamePod(dir)) {
			return new Mtm1GameDir(dir);
		}

		return null;
	}

	private static boolean hasGamePod(File dir) {
		File gamePod = new File(dir, "system/game.pod");

		if (!gamePod.exists() || !gamePod.isFile()) {
			return false;
		}
		return true;
	}

	private static boolean isMtm1Exe(File exeFile) {
		if (!exeFile.exists() || !exeFile.isFile()) {
			return false;
		}
		return true;
	}

	@Override
	public File[] getIniFiles() {
		File[] files = new File[] {
				new File(getDirectory(), "system/monster.ini")
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
