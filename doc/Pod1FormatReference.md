# Reference for the POD file format version 1

*All information here is to my best knowledge, is is not proven by any official reference manual by Microsoft or [Terminal Reality](TerminalReality.md).*

POD files are simple container files housing other files like textures or models. File extension is _pod_.

## Used in

 * [Terminal Velocity](TerminalVelocity.md)
 * [Fury 3](Fury3.md)
 * [Hellbender](Hellbender.md)
 * [Monster Truck Madness 1](MTM1.md)
 * [CART Precision Racing](CartPrecisionRacing.md)
 * [Monster Truck Madness 2](MTM2.md)

## Description

The file starts with a *header block* of 4 bytes size. It gives the amount of files contained in this pod file. All the values are unsigned.
The file amount is calculated as follows:

```c
numFiles = header[0] + header[1] * 2^8 + header[2] * 2^16 + header[3] * 2^24
```

Example:
The MTM2 sound.pod header is `0x57 0x02 0x00 0x00`. So the number of files contained is `0x57 + 0x02 * 2^8 + 0x00 * 2^16 + 0x00 * 2^24`. Which in decimals means `87 + 2 * 256 = 599`.

After that there is a *comment block* of size 80 bytes. I'm not quite sure if it is smaller, but it is at most 80 bytes. It contains a comment the file file contents. An ASCII charset is used.

Example:
The MTM2 cockpit.pod comment is `Cockpits are cool...`.

After the follows the *files block* of size `numFiles * 40` byte. Every contained file is described here by an individual *file block* of size 40 byte. The first 32 bytes ate file's name and folder is ASCII characters. The next 4 bytes are the files size.

The size for the i-th file is calculated as follows:

```c
fileSize[i] = file[i][32] + file[i][33] * 2^8 + file[i][34] * 2^16 + file[i][35] * 2^24
```

Example: the first file (`i = 0`) in MTM2 crazy98.pod is `ART\BACKHOE.ACT` with size 768 bytes.

After those there is the *contents block*. It contains `numFiles` *content blocks* of size `fileSize[i]`.

## Structure

The POD file structure is as follows:

```c
header
comment
files
contents
```

With decomposition:

```c
files = file[0] ... file[numFiles - 1]
file[i] = filePath[i], fileSize[i], unknows
filePath[i] = file[i][0] ... file[i][31]
fileSize[i] = file[i][32] ... file[i][35]
contents = content[0] ... content[numFiles - 1]
```

With variables:

```c
header: size (calculation see above) of length 4
comment: ASCII string of length 80
filePath[i]: ASCII string of length 32
fileSize[i]: size (calculation see above) of length 4
content[i]: binary data of length fileSize[i]
```