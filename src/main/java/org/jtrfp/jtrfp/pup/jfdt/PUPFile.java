/*******************************************************************************
 * This file is part of jTRFP
 * Copyright (c) 2024. See commit history for copyright owners.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     chuck - initial API and implementation
 ******************************************************************************/
package org.jtrfp.jtrfp.pup.jfdt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.jtrfp.jfdt.Parser;
import org.jtrfp.jfdt.SelfParsingFile;
import org.jtrfp.jfdt.ThirdPartyParseable;
import org.jtrfp.jfdt.UnrecognizedFormatException;
import org.jtrfp.jtrfp.jfdt.Powerup;
import org.jtrfp.jtrfp.jfdt.TRParsers;

public class PUPFile extends SelfParsingFile {
    int numPowerups;
    PowerupLocation[] powerupLocations;
    
    public PUPFile(InputStream is) throws IllegalAccessException, IOException {
	super(new BufferedInputStream(is,1024));
    }

    public PUPFile() {}

    public static class PowerupLocation implements ThirdPartyParseable {
	int x, y, z;
	Powerup type;

	@Override
	public void describeFormat(Parser p) throws UnrecognizedFormatException {
	    p.stringEndingWith(",", p.property("x", int.class), false);
	    p.stringEndingWith(",", p.property("y", int.class), false);
	    p.stringEndingWith(",", p.property("z", int.class), false);
	    p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("type", Powerup.class), false);
	}// end describeFormat()

	/**
	 * @return X position of powerup in world units
	 */
	public int getX() {
	    return x;
	}

	/**
	 * @param x
	 *           X position of powerup in world units
	 */
	public void setX(int x) {
	    this.x = x;
	}

	/**
	 * @return Y position of powerup in world units
	 */
	public int getY() {
	    return y;
	}

	/**
	 * @param y
	 *            Y position of powerup in world units
	 */
	public void setY(int y) {
	    this.y = y;
	}

	/**
	 * @return Z position of powerup in world units
	 */
	public int getZ() {
	    return z;
	}

	/**
	 * @param z
	 *            Z position of powerup in world units
	 */
	public void setZ(int z) {
	    this.z = z;
	}

	/**
	 * @return Type of powerup
	 */
	public Powerup getType() {
	    return type;
	}

	/**
	 * @param type
	 *            Type of powerup
	 */
	public void setType(Powerup type) {
	    this.type = type;
	}

    }// end PowerupLocation

    /**
     * @return the powerupLocations
     */
    public PowerupLocation[] getPowerupLocations() {
	return powerupLocations;
    }

    /**
     * @param powerupLocations
     *            the powerupLocations to set
     */
    public void setPowerupLocations(PowerupLocation[] powerupLocations) {
	this.powerupLocations = powerupLocations;
    }

    @Override
    public void describeFormat(Parser p) throws UnrecognizedFormatException {
	p.stringEndingWith(TRParsers.LINE_DELIMITERS, p.property("numPowerups", int.class), false);
	p.arrayOf(getNumPowerups(), "powerupLocations", PowerupLocation.class);
    }

    /**
     * @return Total number of powerups
     */
    public int getNumPowerups() {
	return numPowerups;
    }

    /**
     * @param numPowerups
     *            Total number of powerups
     */
    public void setNumPowerups(int numPowerups) {
	this.numPowerups = numPowerups;
    }
}// end PUPFile
