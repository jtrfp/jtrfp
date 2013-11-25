# Unofficial POD File Format v1.1 by Oliver Pieper

*Version*: v1.1

*Author*: Oliver Pieper

*Email*: [pieper@viaregio.de](mailto:pieper@viaregio.de)

*WWW*: [www.oliverpieper.de](http://www.oliverpieper.de)

*Revision history*:

 * 1999-12-18 v1.1 - Changed the description of file_lnth (were I was obviously wrong) and file_palette name in chapter 3.3, thanks to input from Guitar Bill.
 * 1999-12-05 v1.0 - Put all the info from my old and unstructured "POD File Format.txt" and my program PODMate2 together.


## 0. About this file

This file contains all the info I've gathered about the POD file format used by [TerminalReality Terminal Reality] Inc. (TRI) for the games [Monster Truck Madness 1](MTM1.md) & [2](MTM2.md) (MTM1 & MTM2), [CART Precision Racing (CPR)](CartPrecisionRacing.md), [Hellbender](Hellbender.md), and possibly [Fly!](Fly1.md). A POD file is a kind of uncompressed archive that is used to store several normally separate files in one big file (similar to a ZIP file with compression turned off).

The information in this file has been gathered by reverse engineering the POD files of Monster Truck Madness and has been used for my POD file merger PODMate2. The focus of this work has always been MTM, so you might be in for a surprise or two when you are working with Hellbender, CPR, or Fly! POD files.

If you discover anything new about POD files, please send me the info and I will try to keep this document up to date.

## 1. Data format

Numbers - either signed or unsigned integer values - are stored in the "little endian" format used by all Intel machines. Little endian means that the least significant byte is stored first. The four bytes 0x11 0x22 0x33 0x44 (or short: 11 22 33 44) in a file represent the number 0x44332211, not 0x11223344 as one might expect. I usually read the values by reading 4 bytes directly into a four byte integer variable. That way, no conversion is necessary.

## 2. General info

The term #variable_name in this file should be read as "the value stored in the variable called variable_name".

## 3. File format

### 3.1 Number of Files

The POD archive starts with a four byte value that represents the number of files stored in the POD.

```c
int num_files;
```

### 3.2 Comment Space

The first value (3.1) is followed by 80 bytes that are often set to zero, but may contain any information. PODMate2, for example, uses the space to store the comment "Merged by PODMate2.02, (c)1997 Oliver Pieper" as a zero terminated string, as does TRI sometimes (COCKPIT.POD: "Cockpits are cool").

```c
char pod_comment[80];
```

### 3.3 Directory

After the Comment Space comes the POD's Directory, starting at offset 0x54 (decimal: 84). It is a sequence of #num_files structures, each 40 bytes long and taking this form:

```c
struct pod_directory {
	char file_palette_name[32];
	int file_lnth;
	int file_offset;
};
```

File_palette_name usually is a zero terminated string with the name of a file. In MTM1, however, there can be a special case: If the file is a .RAW file (.RAW files are the MTM texture files), it is followed by a zero byte, followed again by the name of the .ACT palette file that belongs to the .RAW file. According to Guitar Bill, it is not necessary to either read or write the .ACT name from/to PODs. MTM1 seems to default to METALCR2.ACT in both cases.

File_lnth is the length (i.e. the number of bytes) of the stored file. This is always the case, and there's no exception for .RAW files, as I thought before. Thanks, Guitar Bill!

File_offset is the starting position within the POD archive where the actual data of the stored file is located. In other words: if file_offset is 0x12345, the the file data starts 0x12345 bytes from the beginning of the POD archive. See the previous paragraph on obtaining the length of the stored file.

### 3.4 File data

After the last Directory entry follows the unmodified data of the files stored in the POD archive. The structure of that part of the POD file can only be determined by evaluating all the information extracted from the POD Directory structure (3.3).

_©1999 Oliver Pieper. Please send all comments and questions to [pieper@viaregio.de](mailto:pieper@viaregio.de). Permission to copy and modify this document is granted, as long as I am given proper credit for my work._