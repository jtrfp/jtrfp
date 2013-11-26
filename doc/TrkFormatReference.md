# Reference for the MTM TRK file format

*All information here is to my best knowledge, is is not proven by any official reference manual by Microsoft or [Terminal Reality](TerminalReality.md).*

TRK files contain a information for a truck. Please not that [Monster Truck Madness 1](MTM1.md) uses a slightly different format than described here.

# Description

All file contents are organized in a key/value-pair where line `n` is the key and line `n + 1` is the value.

### Meta Section

 * *MTM2 truckName*: the truck name
 * *truckModelBaseName*: the name used to load the model, e.g. _models\truckModelBaseName.bin_
 * *tireModelBaseName*: the name used to load the tires, e.g. _models\tireModelBaseName16R.bin_
 * *axleModelName*: the model used for the axle, e.g. _models\axleModelName_
 * *shockTextureName*: the texture used for the shocks, e.g. _art\shockTextureName_
 * *barTextureName*: the texture used for the axle bars, e.g. _art\barTextureName_

### Position section

All the values below give positions relative to the center of the truck located at (0, 0, 0). The truck is scaled via the wheels. It is assumed, that a wheel is 6 feet tall. So the center of the wheel is always 3 feet above the ground. So tires smaller than 6 feet will float in the air and those larger will sink into the ground.

Tire models in BIN format are always 1536 world units tall. *So one feet of truck scale is 256 world units.*

The *axlebarOffset* is a triplet (x, y, z) for the position of the axle bars. Each of the four axle bars are located between tire position and _axlebarOffset_.

The *driveshaftPos* is a triplet (x, y, z) for the position of the drive shaft center. Each of the two drive shafts are located between the center of an axle and _driveshaftPos_.

Both key/value-pairs are followed by twelve coords, e.g. _faxle.rtire.static_bpos.x_. _faxle_ is for front axle, _raxle_ for rear axle, _ltire_ and _rtire_ for left and right side, and _x_, _y_ and _z_ give the coords.

### Scrape section

_Coming soon._

### Audio section

_Coming soon._

### Light section

_Coming soon._