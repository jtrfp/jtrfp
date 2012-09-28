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
package jtrfp;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import jtrfp.common.FileLoadException;
import jtrfp.common.FileStoreException;
import jtrfp.common.lst.ILstData;
import jtrfp.common.lst.LstData;
import jtrfp.common.lst.LstFile;
import jtrfp.common.pod.PodFile;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Tests for {@link LstData} and {@link LstFile}.
 * @author Stefan Teitge
 */
public class LstDataTest {

	@Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
	
	private static final String ENTRY1 = "foo.bar";
	
	private static final String ENTRY2 = "bar.foo";

	/**
	 * Tests MTM2's alaska.pod for entry count of generated LST data.
	 * @throws FileLoadException if reading POD file fails
	 */
	@Test
	public void testFromPodFile() throws FileLoadException {
		File file = new File(ITestConfig.MTM2_DIR, "alaska.pod");
		
		Assert.assertTrue("Test POD file does not exist.", file.exists() && file.isFile());
		
		PodFile podFile = new PodFile(file);
		LstData lstData = LstData.fromPodFile(podFile); // 684
		assertEquals("Entry count mismatch.", 684, lstData.getEntryCount());
	}

	/**
	 * Creates a custom {@link LstData}, stores it and reads it again.
	 * @throws FileStoreException if storing LST data fails
	 * @throws IOException if creating temp file fails
	 * @throws FileLoadException if reading LST data fails
	 */
	@Test
	public void testToFile() throws FileStoreException, IOException, FileLoadException {
		LstData lstData = new LstData();
		lstData.addEntry(ENTRY1);
		lstData.addEntry(ENTRY2);
		File file = temporaryFolder.newFile();
		lstData.toFile(file);

		LstFile lstFile = new LstFile(file);
		ILstData readData = lstFile.getData();

		assertEquals("Entry count mismatch.", 2, readData.getEntryCount());

		assertEquals("Entry 2 is different", ENTRY2, readData.getEntries()[1]);
	}
}
