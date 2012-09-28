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

import jtrfp.game.ITriGameDir;


public final class Mtm2GameDir extends AbstractTriGameDir implements ITriGameDir {

	private static final String EXE_FILE_NAME = "Monster.EXE";

	private static final String NAME = "Monster Truck Madness 2";

	private final File directory;

	private Mtm2GameDir(File baseDir) {
		super(baseDir, NAME);
		setActSearchStrategy(new Mtm2ActSearchStrategy(this));
		directory = baseDir;
	}

	public static Mtm2GameDir create(File dir) {

		if (dir == null || !dir.exists() || !dir.isDirectory()) {
			return null;
		}

		File mtm2Exe = new File(dir, EXE_FILE_NAME);
		if (isMtm2Exe(mtm2Exe) && hasTruck2Pod(dir)) {
			return new Mtm2GameDir(dir);
		}

		return null;
	}

	private static boolean isMtm2Exe(File exeFile) {
		if (!exeFile.exists() || !exeFile.isFile()) {
			return false;
		}
		return true;
	}

	private static boolean hasTruck2Pod(File dir) {
		File gamePod = new File(dir, "TRUCK2.POD");

		if (!gamePod.exists() || !gamePod.isFile()) {
			return false;
		}
		return true;
	}

	@Override
	public File[] getIniFiles() {
		File[] files = new File[] {
				new File(directory, "pod.ini"),
				new File(directory, "system/monster.ini"),
				new File(directory, "system/extras.ini")
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
