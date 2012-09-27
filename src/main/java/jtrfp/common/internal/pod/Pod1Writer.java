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
package jtrfp.common.internal.pod;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import jtrfp.common.FileStoreException;
import jtrfp.common.internal.Util;
import jtrfp.common.internal.pod.structure.IStructureElement;
import jtrfp.common.internal.pod.structure.Int16Element;
import jtrfp.common.internal.pod.structure.Int32Element;
import jtrfp.common.internal.pod.structure.Pod1Structure;
import jtrfp.common.internal.pod.structure.StringElement;
import jtrfp.common.internal.pod.structure.StructureTraversal;

public class Pod1Writer {

	private static final int BUFFER_SIZE = 1024;

	public static void write(File[] inFiles, File outFile) throws FileStoreException {
		
		if (inFiles.length >= 65536) {
			throw new FileStoreException("Only 65536 entries allowed.");
		}
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(outFile);
			int fileCount = inFiles.length;
			
			StructureTraversal st = new StructureTraversal(new Pod1Structure());
			while (st.hasMore()) {
				IStructureElement element = st.next();
				
				String name = element.getName();
				
				if (Pod1Structure.FILE_COUNT.equals(name)) {
					write(fos, element, fileCount);
				} else if (Pod1Structure.COMMENT.equals(name)) {
					write(fos, element, ""); // TODO use original comment
				} else if (Pod1Structure.FILES.equals(name)) {
					// do nothing
				} else if (Pod1Structure.FILE.equals(name)) {
					
					
					
				} else if (Pod1Structure.CONTENT.equals(name)) {
					
//					byte[] buffer = new byte[BUFFER_SIZE];
//					
//					File inFile = inFiles[element.getRepetition()];
//					FileInputStream fis = new FileInputStream(inFile);
//					
//					int read = 0;
//					while ((read = fis.read(buffer)) > 0)
//					{
//						fos.write(buffer, 0, read);
//					}
//					
//					fos.close();
					
				}
			}
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void write(FileOutputStream fos, IStructureElement element, String string) throws IOException {
		if (element instanceof StringElement) {
			// TODO check address
			fos.write(Util.stringToByteArray("", element.getSize()));
		}
	}

	private static void write(FileOutputStream fos, IStructureElement element, int integer) throws IOException {
		if (element instanceof Int16Element) {
			// TODO check address
			fos.write(Util.int16ToByteArray(integer));
		} else if (element instanceof Int32Element) {
			// TODO check address
			fos.write(Util.int32ToByteArray(integer));
		}
		
	}
}
