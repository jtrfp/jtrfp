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
package jtrfp.common.internal.pod.structure;

public class Pod1Structure implements IPodFileStructure {

	public static final String CONTENT = "content";

	public static final String FILE = "file";

	public static final String FILES = "files";

	public static final String COMMENT = "comment";

	public static final String FILE_COUNT = "fileCount";

	public static final int COMMENT_LENGTH = 80;
	
	public static final int FILE_PATH_LENGTH = 32;
	
	public static final int FILE_INFO_LENGTH = FILE_PATH_LENGTH + 4 + 4;
	
	public IStructureElement[] getStructure() {
		
		Struct file = new Struct(FILE, new IStructureElement[] {
			new StringElement("filePath", FILE_PATH_LENGTH),
			new Int32Element("fileSize"),
			new BinaryElement("unknown", 4)
		});
			
		BinaryElement content = new BinaryElement(CONTENT);
		
		IStructureElement[] elements = new IStructureElement[] {
			new Int32Element(FILE_COUNT, new AbsoluteAddress(0)),
			new StringElement(COMMENT, new AbsoluteAddress(2), COMMENT_LENGTH),
			new ElementArray(FILES, new AbsoluteAddress(COMMENT_LENGTH + 2), file),
			new ElementArray("contents", new RelativeAddressAfter(FILES), content)
		};
		
		return elements;
	}
}
