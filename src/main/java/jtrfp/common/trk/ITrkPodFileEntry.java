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
package jtrfp.common.trk;

import jtrfp.common.FileLoadException;
import jtrfp.common.pod.IPodFileEntry;

/**
 * Abstract representation for a TRK (truck) file contained in a POD file.
 * @author Stefan Teitge
 */
public interface ITrkPodFileEntry extends IPodFileEntry {

	ITrkData getData() throws FileLoadException;

}
