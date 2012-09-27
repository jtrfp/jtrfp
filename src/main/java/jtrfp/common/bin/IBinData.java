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
package jtrfp.common.bin;

public interface IBinData {

	int ID_MODEL = 0x14;

	int ID_ANIMATION_CONTROL = 0x20;

	int getId();

	int getVertexCount();

	IBinVertex[] getVertexes();

	IBinVertex[] getNormals();

	IBinFace[] getFaces();

	String[] getTextureNames();
}
