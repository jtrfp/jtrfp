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
package org.jtrfp.common.kfm;

public interface IKfmData {

	int getVersion();

	int getVertexCount();

	int getPolyCount();

	int getTextureCount();

	int getPartCount();

	int getFrameCount();

	int getUseCollisionListFlag();

	int getTransparentPixelFlag();

	int getDisableBackfaceCulling();

	int getEnviromentMapListFlag();

	IKfmVertex[] getVertexes();

	IKfmPoly[] getPolys();

	String[] getTextures();

	IKfmPart[] getParts();
}
