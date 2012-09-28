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

import java.io.File;
import java.io.IOException;

import jtrfp.common.FileLoadException;
import jtrfp.common.FileStoreException;
import jtrfp.common.act.ActFile;
import jtrfp.common.raw.RawFile;
import jtrfp.common.raw.RawImage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class RawImageTest {

	@Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
	
	private RawImage rawImage;

	@Before
	public void setUp() throws FileLoadException {
		File file = new File(ITestConfig.EXTRACTED_MTM2_FILES_DIR, "art/C8OFF31.RAW");
		Assert.assertTrue("Raw file does not exist.", file.exists());
		RawFile rawFile = new RawFile(file);

		File file2 = new File(ITestConfig.EXTRACTED_MTM2_FILES_DIR, "art/C8OFF31.ACT");
		Assert.assertTrue("ACT file does not exist.", file2.exists());
		ActFile actFile = new ActFile(file2);

		rawImage = new RawImage(rawFile.getRawData(), actFile.getData());
	}

	@Test
	public void testStoreAsBitmap() throws IOException, FileStoreException  {
		File bitmapFile = temporaryFolder.newFile();
		rawImage.storeAsBitmap(bitmapFile);

		Assert.assertTrue("Bitmap has not been created.", bitmapFile.exists());

		Assert.assertTrue("Bitmap has no size.", bitmapFile.length() > 0);
	}
}