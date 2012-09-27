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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ActDataTest.class,
	ActFileTest.class,
	BinFileTest.class,
	ClrDataTest.class,
	CockpitPodInfoTest.class,
	KfmFileTest.class,
	LstDataTest.class,
	PodFileTest.class,
	PodIniFileTest.class,
	PodLstFileTest.class,
	RawDecodeTest.class,
	RawFileTest.class,
	RawImageTest.class,
	SitDataTest.class,
	TrkFileTest.class
})

public class AllTests {

}
