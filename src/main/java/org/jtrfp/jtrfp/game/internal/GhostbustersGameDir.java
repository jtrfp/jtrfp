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



public final class GhostbustersGameDir extends AbstractTriGameDir implements ITriGameDir {

	private static final String EXE_FILE_NAME = "ghost_w32.exe";

	private static final String NAME = "Ghostbusters";

	private GhostbustersGameDir(File baseDir) {
		super(baseDir, NAME);
	}

	public static GhostbustersGameDir create(File dir) {

		if (dir == null || !dir.exists() || !dir.isDirectory()) {
			return null;
		}

		File exe = new File(dir, EXE_FILE_NAME);
		if (isGhostbustersExe(exe)) {
			return new GhostbustersGameDir(dir);
		}

		return null;
	}

	private static boolean isGhostbustersExe(File exeFile) {
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
		File[] files = new File[] {};
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
