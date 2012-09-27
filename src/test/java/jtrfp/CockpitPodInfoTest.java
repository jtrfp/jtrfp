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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import jtrfp.common.FileLoadException;
import jtrfp.common.FileStoreException;
import jtrfp.common.internal.pod.Pod1Loader;
import jtrfp.common.pod.IPodData;
import jtrfp.common.pod.IPodFileEntry;
import jtrfp.common.pod.PodFile;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class CockpitPodInfoTest {

	@Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
	
	private static final int FILE_COUNT = 235;

	private static final String COMMENT = "Cockpits are cool...";

	private static final String FILE_NAME = "cockpit.pod";

	private PodFile podFile;

	@Before
	public void setUp() {
		File file = new File(ITestConfig.MTM2_DIR, FILE_NAME);

		assertTrue(FILE_NAME + " does not exist.", file.exists());

		podFile = new PodFile(file);
	}

	@Test
	public void testGetFileCount() throws FileLoadException {
		IPodData podInfo = podFile.getData();

		int fileCount = podInfo.getEntryCount();

		assertEquals("File count mismatch.", FILE_COUNT, fileCount);
	}

	@Test
	public void testGetComment() throws FileLoadException {
		IPodData podInfo = podFile.getData();

		String comment = podInfo.getComment();

		assertEquals("Wrong comment extracted.", COMMENT, comment);
	}

	@Test
	public void testFileEntryCount() throws FileLoadException {
		IPodData podInfo = podFile.getData();

		int fileCount = podInfo.getEntries().length;

		assertEquals("File list count mismatch.", FILE_COUNT, fileCount);
	}

	@Test
	public void testEntrySize() throws FileLoadException {
		IPodData podInfo = podFile.getData();

		IPodFileEntry[] entries = podInfo.getEntries();
		for (IPodFileEntry entry : entries) {
			File filesDir = new File(ITestConfig.EXTRACTED_MTM2_FILES_DIR);
			File realFile = new File(filesDir, entry.getPath());

			long size = realFile.length();
			assertEquals(entry.getPath() + " has file size mismatch.", size, entry.getSize());
		}
	}

	@Test
	public void testFileSize() throws FileLoadException {
		IPodData podInfo = podFile.getData();

		long size = Pod1Loader.PREFIX_LENGTH;
		size += podInfo.getEntryCount() * 40; // TODO use Pod1Structure.FILE_INFO_LENGTH here
		IPodFileEntry[] entries = podInfo.getEntries();
		for (IPodFileEntry entry : entries) {
			size += entry.getSize();
		}

		assertEquals("File size mismatch.", podFile.getFile().length(), size);
	}

	@Test
	public void testGetOutputStream() throws FileLoadException, FileStoreException, IOException {
		IPodFileEntry[] entries = podFile.getData().getEntries();
		IPodFileEntry entry1 = entries[1];

		File file = temporaryFolder.newFile();
		entry1.toFile(file);
	}

}
