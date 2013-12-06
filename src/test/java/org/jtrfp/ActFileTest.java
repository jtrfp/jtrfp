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
import org.jtrfp.jtrfp.act.ActColor;
import org.jtrfp.jtrfp.act.ActFile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;


public class ActFileTest {

	@Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
	
	private static final int COLOR_COUNT = 256;

	private static final ActColor COLOR_37 = new ActColor(0x3A, 0x2B, 0x21);

	private ActFile actFile;

	@Before
	public void setUp() {
		File file = new File(ITestConfig.EXTRACTED_MTM2_FILES_DIR, "art/crazy98.act");

		Assert.assertTrue("ACT file does not exist.", file.exists());

		actFile = new ActFile(file);
	}

	@Test
	public void testColorCount() throws FileLoadException {
		int count = actFile.getData().getColorCount();
		Assert.assertEquals("Color count is not " + COLOR_COUNT, COLOR_COUNT, count);
	}

	@Test
	public void testColors() throws FileLoadException {
		ActColor color = actFile.getData().getColor(37);
		Assert.assertEquals("tested color count not corrrect.", COLOR_37, color);
	}

	@Test
	public void testStoreAsBitmap() throws IOException, FileStoreException, FileLoadException  {
		File bitmapFile = temporaryFolder.newFile();
		actFile.getData().toBitmapFile(bitmapFile);

		Assert.assertTrue("Bitmap has not been created.", bitmapFile.exists());

		Assert.assertTrue("Bitmap has no size.", bitmapFile.length() > 0);
	}
}
