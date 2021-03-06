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
package org.jtrfp.jtrfp.internal.pod.structure;

public class BinaryElement implements IStructureElement {

	private final int size;
	
	private final String name;

	private IStructureElementAddress address;

	public BinaryElement(String name) {
		this(name, -1);
	}
	
	public BinaryElement(String name, int size) {
		this.name = name;
		this.size = size;
	}

	@Override
	public IStructureElementAddress getAddress() {
		return address;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getSize() {
		return size;
	}

}
