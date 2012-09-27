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
package jtrfp.game;

import java.io.File;

import jtrfp.common.pod.PodFile;


public interface ITriGameDir {

	/**
	 * Returns the game's directory in the local file system.
	 * @return the directory
	 */
	File getDirectory();

	/**
	 * Returns all mounted POD files.
	 * @return the POD files
	 */
	PodFile[] getPodFiles();

	/**
	 * Returns the game's name.
	 * @return
	 */
	String getGameName();

	/**
	 * Return all meaningful INI files.
	 * @return the INI files
	 */
	File[] getIniFiles();

	/**
	 * Returns the EXE file for launching this game.
	 * @return the EXE file
	 */
	File getExeFile();

	/**
	 * Returns whether this game has the given POD file mounted.
	 * @param podFile the POD file to look for
	 * @return true if POD file is mounted
	 */
	boolean hasPodFile(PodFile podFile);

	/**
	 * Returns a mounted POD file with given name.
	 * @param name the name
	 * @return the POD file or null is it does not exist
	 */
	PodFile findPodFile(String name);

	/**
	 * The strategy to find an ACT color table for a given RAW file.
	 * @return the search strategy
	 */
	IActSearchStrategy getActSearchStrategy();
}
