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
package jtrfp.common;

/**
 * This Exception is thrown when storing a file fails.
 * @author Stefan Teitge
 */
public class FileStoreException extends Exception {

	/**
	 * The serial version UID of this class.
	 */
	private static final long serialVersionUID = -4588744698655637806L;

	/**
	 * Constructs a FileStoreException with the specified detail message.
	 * @param the detail message
	 */
	public FileStoreException(String message) {
		super(message);
	}

	/**
	 * Constructs a FileStoreException with the specified detail message and causing exception.
	 * @param the detail message
	 * @param the causing exception
	 */
	public FileStoreException(String message, Exception e) {
		super(message, e);
	}

	/**
	 * Constructs a FileStoreException with the specified causing exception.
	 * @param the causing exception
	 */
	public FileStoreException(Exception e) {
		super(e);
	}
}
