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
package org.jtrfp.jtrfp;

/**
 * This Exception is thrown when loading a file fails.
 * @author Stefan Teitge
 */
public class FileLoadException extends Exception {

	/**
	 * The serial version UID of this class.
	 */
	private static final long serialVersionUID = -4588744698655637806L;

	/**
	 * Constructs a FileLoadException with the specified detail message.
	 * @param the detail message
	 */
	public FileLoadException(String message) {
		super(message);
	}

	/**
	 * Constructs a FileLoadException with the specified detail message and causing exception.
	 * @param the detail message
	 * @param the causing exception
	 */
	public FileLoadException(String message, Exception e) {
		super(message, e);
	}

	/**
	 * Constructs a FileLoadException with the specified causing exception.
	 * @param the causing exception
	 */
	public FileLoadException(Exception e) {
		super(e);
	}
}
