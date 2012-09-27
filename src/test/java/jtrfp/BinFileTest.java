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
package jtrfp;

import java.io.File;
import java.io.IOException;

import jtrfp.common.FileLoadException;
import jtrfp.common.FileStoreException;
import jtrfp.common.bin.IBinPodFileEntry;
import jtrfp.common.pod.PodFile;
import jtrfp.game.GameDirFactory;
import jtrfp.game.ITriGameDir;

import org.junit.Assert;
import org.junit.Test;

public class BinFileTest {

	public void testSkeleton(String name, String path) throws FileLoadException {
		ITriGameDir gameDir = GameDirFactory.create(new File(path));
		Assert.assertNotNull("Invalid " + name + " dir", gameDir);
		PodFile[] podFiles = gameDir.getPodFiles();

		for (PodFile podFile : podFiles) {
			IBinPodFileEntry[] entries = podFile.getData().findEntries(IBinPodFileEntry.class);
			for (IBinPodFileEntry entry : entries) {
				try {
					entry.getData();
				} catch (FileLoadException e) {
					// as BinDataLoader loads from streams it does not know the file name
					// so we print helping message here
					String msg = "Failed '" + entry.getPath() + "' from '" + podFile.getFile().getName() + "'";
					System.err.println(msg);
					throw e;
				}
			}
		}
	}

	@Test
	public void testGetDataMtm1() throws IOException, FileStoreException, FileLoadException {
		testSkeleton("MTM1", ITestConfig.MTM1_DIR);
	}

	@Test
	public void testGetDataMtm2() throws IOException, FileStoreException, FileLoadException {
		testSkeleton("MTM2", ITestConfig.MTM2_DIR);
	}

	//	@Test
	//	public void testGetDataHellbender() throws IOException, FileStoreException, FileLoadException {
	//		testSkeleton("Hellbender", ITestConfig.HELLBENDER_DIR);
	//	}
}
