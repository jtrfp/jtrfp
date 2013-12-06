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
import java.io.IOException;



import org.jtrfp.jtrfp.FileLoadException;
import org.jtrfp.jtrfp.FileStoreException;
import org.jtrfp.jtrfp.pod.PodFile;
import org.jtrfp.jtrfp.pod.PodIniFile;
import org.jtrfp.jtrfp.pod.PodLstFile;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class PodLstFileTest {
	
	@Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private static final int CHECK_INDEX = 17;

	private static final String TEST_ENTRY = "test.pod";
	
	private static final String POD_FILE_NAME = "cockpit.pod";

	private static final int CREATE_COUNT = 0;

	private PodFile getMtm2PodFile() {
		File file = new File(ITestConfig.MTM2_DIR, POD_FILE_NAME);
		return new PodFile(file);
	}
	
	@Test
	public void testWithMtm2PodFile() throws FileLoadException {
		PodFile podIni = getMtm2PodFile();

		PodLstFile plf = PodLstFile.fromPodData(podIni.getData());

		Assert.assertEquals(
				"Mismatching entry count.",
				podIni.getData().getEntryCount(),
				plf.getEntryCount());

		Assert.assertEquals(
				"Mismatching entries.",
				podIni.getData().getEntries()[CHECK_INDEX].getPath(),
				plf.getEntries()[CHECK_INDEX]);
	}
	
	@Test
	public void testCreateNew() throws FileLoadException, FileStoreException, IOException {
		PodLstFile plf = PodLstFile.createNew();

		for (int i = 0; i < CREATE_COUNT; i++) {
			plf.addEntry(TEST_ENTRY);
		}

		File createdPodIni = temporaryFolder.newFile();

		plf.toFile(createdPodIni);

		PodIniFile pif2 = PodIniFile.fromFile(createdPodIni);

		Assert.assertEquals(
				"Entry count mismatch.", 
				CREATE_COUNT,
				pif2.getEntryCount());
	}
	
	@Test
	public void testFromAndToFile() throws FileLoadException, FileStoreException, IOException {
		PodLstFile plf = PodLstFile.createNew();
		plf.addEntry(TEST_ENTRY);
		
		File createdFile = temporaryFolder.newFile();
		
		plf.toFile(createdFile);
		
		PodLstFile plf2 = PodLstFile.fromFile(createdFile);
		
		Assert.assertEquals(
				"Entry count mismatch.", 
				plf.getEntryCount(), 
				plf2.getEntryCount());
	}
	

}
