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



import org.jtrfp.jtrfp.FileLoadException;
import org.jtrfp.jtrfp.clr.IClrData;
import org.jtrfp.jtrfp.clr.IClrPodFileEntry;
import org.jtrfp.jtrfp.pod.IPodFileEntry;
import org.jtrfp.jtrfp.pod.PodFile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClrDataTest {

	private static final String POD_FILE_NAME = "crazy98.pod";

	private static final String CLR_ENTRY_PATH = "data\\crazy98.clr";

	private PodFile podFile;

	@Before
	public void setUp() {
		File file = new File(ITestConfig.MTM2_DIR, POD_FILE_NAME);
		Assert.assertTrue("Test POD file does not exist.", file.exists() && file.isFile());
		podFile = new PodFile(file);
	}

	@Test
	public void testFromPodFile() throws FileLoadException {
		IPodFileEntry entry = podFile.getData().findEntry(CLR_ENTRY_PATH);

		Assert.assertTrue("No CLR entry found.", entry instanceof IClrPodFileEntry);

		IClrData data = ((IClrPodFileEntry) entry).getData();

		Assert.assertEquals("Color count is wrong.", 241, data.getColorCount());
	}
}
