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
package jtrfp.game;

import java.io.File;

import jtrfp.game.internal.Blair1GameDir;
import jtrfp.game.internal.BloodRayne1GameDir;
import jtrfp.game.internal.BloodRayne2GameDir;
import jtrfp.game.internal.BlowoutGameDir;
import jtrfp.game.internal.CartGameDir;
import jtrfp.game.internal.Evo1GameDir;
import jtrfp.game.internal.Evo2GameDir;
import jtrfp.game.internal.Fly2GameDir;
import jtrfp.game.internal.Fury3GameDir;
import jtrfp.game.internal.GhostbustersGameDir;
import jtrfp.game.internal.HellbenderGameDir;
import jtrfp.game.internal.Mtm1GameDir;
import jtrfp.game.internal.Mtm2GameDir;
import jtrfp.game.internal.NocturneGameDir;
import jtrfp.game.internal.TvGameDir;


public class GameDirFactory {

	public static ITriGameDir create(File dir) {
		ITriGameDir gameDir;

		// TODO: make dynamic list

		// Blair Witch 1
		gameDir = Blair1GameDir.create(dir);
		if (gameDir != null) { return gameDir; }
		
		// BloodRayne
		gameDir = BloodRayne1GameDir.create(dir);
		if (gameDir != null) { return gameDir; }
		
		// BloodRayne 2
		gameDir = BloodRayne2GameDir.create(dir);
		if (gameDir != null) { return gameDir; }
		
		// Blowout
		gameDir = BlowoutGameDir.create(dir);
		if (gameDir != null) { return gameDir; }

		// CART Precision Racing
		gameDir = CartGameDir.create(dir);
		if (gameDir != null) { return gameDir; }

		// 4x4 Evo 1
		gameDir = Evo1GameDir.create(dir);
		if (gameDir != null) { return gameDir; }

		// 4x4 Evo2
		gameDir = Evo2GameDir.create(dir);
		if (gameDir != null) { return gameDir; }

		// Fury3
		gameDir = Fury3GameDir.create(dir);
		if (gameDir != null) { return gameDir; }

		// Hellbender
		gameDir = HellbenderGameDir.create(dir);
		if (gameDir != null) { return gameDir; }

		// Monster Truck Madness 1
		gameDir = Mtm1GameDir.create(dir);
		if (gameDir != null) { return gameDir; }

		// Monster Truck Madness 2
		gameDir = Mtm2GameDir.create(dir);
		if (gameDir != null) { return gameDir; }

		// Nocturne
		gameDir = NocturneGameDir.create(dir);
		if (gameDir != null) { return gameDir; }

		// Terminal Velocity
		gameDir = TvGameDir.create(dir);
		if (gameDir != null) { return gameDir; }

		// Ghostbusters
		gameDir = GhostbustersGameDir.create(dir);
		if (gameDir != null) { return gameDir; }

		// Fly! II
		gameDir = Fly2GameDir.create(dir);

		return gameDir;
	}
}
