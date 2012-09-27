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
package jtrfp.common.internal.kfm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import jtrfp.common.FileLoadException;
import jtrfp.common.kfm.IKfmData;
import jtrfp.common.kfm.KfmFile;

public class KfmDataLoader {

	private Scanner scanner;

	private String line;

	public KfmDataLoader() {
	}

	public IKfmData load(KfmFile kfmFile) throws FileLoadException {
		try {
			return load(new FileInputStream(kfmFile.getFile()));
		} catch (FileNotFoundException e) {
			throw new FileLoadException(e);
		}
	}

	public IKfmData load(InputStream is) throws FileLoadException {
		KfmData kfmData = new KfmData();

		scanner = new Scanner(is);

		try {
			advanceLine();
			kfmData.setVersion(Integer.parseInt(line));

			advanceLine();
			String[] parts = line.split(",");

			if (parts.length != 5) {
				throw new FileLoadException("Count line has wrong length");
			}

			kfmData.setVertexCount(Integer.parseInt(parts[0]));
			kfmData.setPolyCount(Integer.parseInt(parts[1]));
			kfmData.setTextureCount(Integer.parseInt(parts[2]));
			kfmData.setPartCount(Integer.parseInt(parts[3]));
			kfmData.setFrameCount(Integer.parseInt(parts[4]));

		} catch (NumberFormatException e) {
			throw new FileLoadException("Parsing numbers failed", e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}

		return kfmData;
	}

	private void advanceLine() {
		if (scanner.hasNextLine()) {
	        String newLine = scanner.nextLine();
	        if (newLine.trim().startsWith("//")) {
	        	advanceLine();
	        }
	        else
	        {
	        	line = newLine;
	        }
	    }
	}
}
