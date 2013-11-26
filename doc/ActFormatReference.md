# Reference for the MTM ACT file format


*All information here is to my best knowledge, is is not proven by any official reference manual by Microsoft or [Terminal Reality](TerminalReality.md).*

ACT files are files containing an indexed list of colors. The are used to color [RAW files](RawFormatReference.md), which only contain color indexes instead of real colors. ACT stands for _Adobe Color Table_.

## Description

Each color contained has the length of 3 byte. Each byte represents one color component in the RGB color model. The position of the color in the file is its index (starting from 0).

The amount of colors `numColors` is the file size divided by 3.

Example: The MTM2 crazy98.pod contains the file `ART\BACKHOE.ACT`. The color at index 1 is `0x03 0x01 0x01` which in RGB is a slightly lighter black.

## Structure

The ACT file structure is as follows:

```c
color[0]
...
color[numColors - 1]
```

With decomposition:

```c
color[i] = component[i][0], component[i][1], component[i][2]
```

With variables:

```c
component[i][j]: color component in range [0...255]
```