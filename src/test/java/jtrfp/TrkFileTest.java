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

import static org.junit.Assert.assertTrue;

import java.io.File;

import jtrfp.common.FileLoadException;
import jtrfp.common.trk.ITrkData;
import jtrfp.common.trk.TrkFile;

import org.junit.Before;
import org.junit.Test;

public class TrkFileTest {

	private static final String TRUCK_PATH = "TRUCK\\DIGGER.TRK";

	private TrkFile trkFile;

	@Before
	public void setUp() {
		File file = new File(ITestConfig.EXTRACTED_MTM2_FILES_DIR, TRUCK_PATH);

		assertTrue("Test truck does not exist", file.exists() && file.isFile());
		trkFile = new TrkFile(file);
	}

	@Test
	public void testGetData() throws FileLoadException {
		ITrkData trkData = trkFile.getData();

		assertTrue("Truck name mismatch", "Grave Digger".equals(trkData.getTruckName()));
	}
}
