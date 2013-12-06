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
package org.jtrfp.jtrfp.act;

/**
 * Represents an color entry in an IActColorTable
 * The components may be of certain color space, e.g. RGB.
 * @author Stefan Teitge
 */
public class ActColor {

	private final int component1;

	private final int component2;

	private final int component3;

	public ActColor(int component1, int component2, int component3) {
		this.component1 = component1;
		this.component2 = component2;
		this.component3 = component3;
	}

	public int getComponent1() {
		return component1;
	}

	public int getComponent2() {
		return component2;
	}

	public int getComponent3() {
		return component3;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + component1;
		result = prime * result + component2;
		result = prime * result + component3;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ActColor other = (ActColor) obj;
		if (component1 != other.component1) {
			return false;
		}
		if (component2 != other.component2) {
			return false;
		}
		if (component3 != other.component3) {
			return false;
		}
		return true;
	}

	public int toRgb() {
		int red = (getComponent1() & 0xFF) * 65536;
		int green = (getComponent2() & 0xFF) * 256;
		int blue = (getComponent3() & 0xFF);

		return (red + green + blue);
	}
}
