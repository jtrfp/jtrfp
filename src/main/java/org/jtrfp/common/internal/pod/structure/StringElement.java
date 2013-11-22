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
package org.jtrfp.common.internal.pod.structure;

public class StringElement implements IStructureElement {

	private final String name;
	
	private final IStructureElementAddress address;
	
	private final int size;
	
	public StringElement(String name, int size) {
		this(name, null, size);
	}

	public StringElement(String name, IStructureElementAddress address, int size) {
		this.name = name;
		// TODO Auto-generated constructor stub
		this.address = address;
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
