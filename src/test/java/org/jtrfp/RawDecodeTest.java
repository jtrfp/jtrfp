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
package org.jtrfp;

import java.io.File;




import org.jtrfp.jtrfp.FileLoadException;
import org.jtrfp.jtrfp.FileStoreException;
import org.jtrfp.jtrfp.act.IActPodFileEntry;
import org.jtrfp.jtrfp.game.GameDirFactory;
import org.jtrfp.jtrfp.game.ITriGameDir;
import org.jtrfp.jtrfp.pod.IPodData;
import org.jtrfp.jtrfp.pod.PodFile;
import org.jtrfp.jtrfp.raw.IRawPodFileEntry;
import org.junit.Assert;
import org.junit.Test;

public class RawDecodeTest {

	private void testSkeleton(ITriGameDir gameDir) throws FileLoadException, FileStoreException {
		PodFile[] podFiles = gameDir.getPodFiles();

		for (PodFile podFile : podFiles) {
			IPodData podData = podFile.getData();

			IRawPodFileEntry[] rawEntries = podData.findEntries(IRawPodFileEntry.class);

			for (IRawPodFileEntry rawEntry : rawEntries) {
				IActPodFileEntry actEntry = gameDir.getActSearchStrategy().find(rawEntry);

				String message = "No ACT entry found for " + rawEntry.getPath() + " in " + podFile.getFile().getName() + " in " + gameDir.getGameName();
				Assert.assertNotNull(message, actEntry);
			}
		}
	}

	@Test
	public void testMtm2() throws FileLoadException, FileStoreException {
		ITriGameDir gameDir = GameDirFactory.create(new File(ITestConfig.MTM2_DIR));

		testSkeleton(gameDir);
	}
}
