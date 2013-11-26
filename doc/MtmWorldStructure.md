# World structured in MTM

In MTM tracks are stored in [POD files](PodFormatReference.md). Each of those POD file usually has internal sub folders like _art_, _data_, _fog_, _levels_, _models_ and _world_. See the [POD file list](PodFileList.md) for more information.

*This document will describe the world structure using the track _Crazy '98_ from _crazy98.pod_ of MTM2.*

## SIT File

[SIT files](SitFormatReference.md) are the entry point for the world structure. They are usually located in the _world_ sub folder. We will now use _world/crazy98.sit_.

The first line refers to a LVL file, which is the geometric description of the world, which in our case is _crazy98.lvl_ located in _levels_. The SIT file itself contains further information on the track displayed in the game UI, the placing of models and the circuits.

## LVL File

The LVL describes the basic geometry of the world like water height, size and coordinates. It also contains the files used to render the level.

For _crazy98.lvl_ those are:
 * _crazy98.raw_ in _data_: the height map
 * _crazy98.tex_ in _data_: the textures used
 * more to come...