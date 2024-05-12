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
package org.jtrfp.jtrfp;

import java.io.File;
import java.io.IOException;

import org.jtrfp.jtrfp.game.GameDirFactory;
import org.jtrfp.jtrfp.game.ITriGameDir;
import org.jtrfp.jtrfp.pod.PodIniFile;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class PodIniFileTest {

	@Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
	
	private static final String COCKPIT_POD = "cockpit.pod";

	private static final String TEST_POD = "test.pod";
	
	private static final int CREATE_COUNT = 7;

	private File getMtm2PodIni() {
		ITriGameDir gameDir = GameDirFactory.create(new File(ITestConfig.MTM2_DIR));

		Assert.assertNotNull("Game directory does not exist.", gameDir);
		
		for (File podIni : gameDir.getIniFiles()) {
			if ("pod.ini".equals(podIni.getName())) {
				return podIni;
			}
		}
		
		return null;
	}
	
	@Test
	public void testReadMtm2PodIni() throws FileLoadException {
		File podIni = getMtm2PodIni();
		
		Assert.assertNotNull("Test INI file does not exist.", podIni);
		
		PodIniFile pif = PodIniFile.fromFile(podIni);

		Assert.assertEquals(
				"Invalid pod.ini entry count.",
				19,
				pif.getEntryCount());

		Assert.assertEquals(
				"Invalid pod.ini entry count.",
				COCKPIT_POD,
				pif.getEntries()[4]);
	}
	
	@Test
	public void testModifyMtm2PodIni() throws FileLoadException, FileStoreException, IOException {
		File podIni = getMtm2PodIni();
		
		PodIniFile pif = PodIniFile.fromFile(podIni);

		pif.addEntry(TEST_POD);

		File modifiedPodIni = temporaryFolder.newFile();

		pif.toFile(modifiedPodIni);

		PodIniFile pif2 = PodIniFile.fromFile(modifiedPodIni);

		Assert.assertEquals("Entry count has not grown.", pif.getEntryCount(),
				pif2.getEntryCount());

		Assert.assertEquals("Entry was not added at valid position.", TEST_POD,
				pif.getEntries()[pif2.getEntryCount() - 1]);
	}
	
	@Test
	public void testCreatePodIni() throws FileLoadException, FileStoreException, IOException {
		PodIniFile pif = PodIniFile.createNew();

		for (int i = 0; i < CREATE_COUNT; i++) {
			pif.addEntry(TEST_POD);
		}

		File createdPodIni = temporaryFolder.newFile();

		pif.toFile(createdPodIni);

		PodIniFile pif2 = PodIniFile.fromFile(createdPodIni);

		Assert.assertEquals(
				"Entry count mismatch.", 
				CREATE_COUNT,
				pif2.getEntryCount());
	}
}
