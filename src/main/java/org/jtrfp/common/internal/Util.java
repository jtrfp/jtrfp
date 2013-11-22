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
package org.jtrfp.common.internal;


public final class Util {

	private Util() {
	}

	public static int unsignedByteToInt(byte b) {
		return b & 0xFF;
	}

	public static String makeString(byte[] buffer) {
		int index = 0;
		for (; index < buffer.length; index++) {
			if (buffer[index] == 0) {
				break;
			}
		}

		return new String(buffer, 0, index);
	}

	public static int makeSignedInt(byte[] buffer) {
		return (buffer[3] << 24)
		+ ((buffer[2] & 0xFF) << 16)
		+ ((buffer[1] & 0xFF) << 8)
		+ (buffer[0] & 0xFF);
	}
	
	public static byte[] int16ToByteArray(int integer) {
		return new byte[] {(byte)integer, (byte)(integer >> 8)};
	}

	public static byte[] int32ToByteArray(int integer) {
		return new byte[] {(byte)integer, (byte)(integer >> 8), (byte)(integer >> 16), (byte)(integer >> 24)};
	}

	public static byte[] stringToByteArray(String string, int size) {
		if (string.length() == size) {
			return string.getBytes();
		} else if (string.length() < size) {
			while (string.length() < size) {
				string += "\0";
			}
			
			return string.getBytes();
		}
		
		return string.substring(0, size).getBytes();
	}
}

