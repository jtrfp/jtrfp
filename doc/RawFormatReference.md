# Reference for the MTM RAW file format

*All information here is to my best knowledge, is is not proven by any official reference manual by Microsoft or [Terminal Reality](TerminalReality.md).*

RAW files simply contain a (square) matrix of values or indexes.

## Description

### Textures

Textures in MTM are square with width and height equals `sideLength`. So the RAW file size is `sideLength^2`. As a texture each pixel is assigned a color from an [ACT file](ActFormatReference.md), where `value[i][j]` is an index in the color table.

### Height maps

RAW files are also used for the height maps of the individual tracks.

_More information to come._

### Other

There are also non square RAW files. Don't know why at the moment.

_More information to come._

## Structure

The RAW file structure is as follows (for square RAW files):

```c
value[0][0]
value[0][1]
...
value[sideLength - 1][sideLength - 2]
value[sideLength - 1][sideLength - 1]
```

With variables:

```c
value[i][j]: index or numeric value in range [0...255]
```