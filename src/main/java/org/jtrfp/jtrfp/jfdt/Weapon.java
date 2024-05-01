/*******************************************************************************
 * This file is part of jTRFP
 * Copyright (c) 2012-2024. See commit history for copyright owners.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     chuck - initial API and implementation
 ******************************************************************************/
package org.jtrfp.jtrfp.jfdt;

import java.awt.Dimension;

import org.jtrfp.jtrfp.jfdt.ModelingType.BINModelingType;
import org.jtrfp.jtrfp.jfdt.ModelingType.BillboardModelingType;
import org.jtrfp.jtrfp.jfdt.ModelingType.FlatModelingType;
/**
 * LASER6.BIN - fireball == FIREBALL.BIN
 * TV-style colored Jenga pieces:
 * LASER.BIN - purple
 * LASER7.BIN - Green
 * LASER8.BIN - Orange-red
 * LASER9.BIN - Blue
 * @author Chuck Ritola
 *
 */
public enum Weapon {
    // / THESE ARE LISTED REPRESENTATIVE OF THE ORDINAL ORDER IN A DEF FILE. DO
    // NOT RE-ORDER!!
    purpleLaser(null, null,new BINModelingType("LASER.BIN"), 4096, 4456448, true,-1, 2048,
	    false, false,1000/6), 
    PAC("PAC", "SKL", new BINModelingType("LASER3.BIN"), 4096, //LASER3.BIN
	    0x200000, true,1, 2048, true, false,1000/6), 
    ION("ION",
	    "DC1", new BINModelingType("LASER4.BIN"), 8192,
	    0x180000, true,2, 2048, false, false,1000/6), 
    RTL(
	    "RTL", "RFL20", new BINModelingType("LASER5.BIN"), 4096, 0x400000,
	    true,3, 2048, true, false,1000/6), 
    fireBall(null, null, new BINModelingType(
	    "FIREBALL.BIN"), 8192, ModelingType.MAP_SQUARE_SIZE * 8, true,-1, 1024,
	    false, false,1000/2), 
    greenLaser(null, null, new BINModelingType("LASER7.BIN"), 8192,
	    1835008, true,-1, 2048, false, false,1000/6), 
    redLaser(
	    null, null, new BINModelingType("LASER8.BIN"), 4096, 2359296, true, -1, 2048,
	    false, false,1000/6), 
    blueLaser(null, null, new BINModelingType("LASER9.BIN"), 4096,
	    4456448, true,-1, 2048, false, false,1000/6), 
    bullet(
	    null, null, new BINModelingType("BULLET.BIN"), 6554,
	    4456448, true,-1, 2048, false, false,1000/2), 
    purpleBall(
	    null, null, new BillboardModelingType(new String[] { "PBALL1.RAW",
		    "PBALL3.RAW", "PBALL4.RAW" }, 70, new Dimension(320000,
		    320000)), 6554, 4456448, false,-1, 2048,
	    false, false,1000/2), 
    blueFireBall(null, null, new BillboardModelingType(
	    new String[] { "BFIRJ0.RAW", "BFIRJ1.RAW", "BFIRJ2.RAW",
		    "BFIRJ3.RAW" }, 100, new Dimension(320000, 320000)), 6554,
		    4456448, false,-1, 2048, false, false,1000/2), 
    goldBall(
	    null, null, new BINModelingType("FIREBALL.BIN"), 8000,
	    4456448, true, -1, 2048, false, false,1000/2), 
    atomWeapon(
	    null, null, new BillboardModelingType(new String[] { "ATM2.RAW",
		    "ATM3.RAW" }, 70, new Dimension(320000, 320000)), 10000,
		    4456448, false,-1, 2048, false, false,1000/2), 
    purpleRing(
	    null, null, new BillboardModelingType(new String[] {
		    "PURFRIN0.RAW", "PURFRIN1.RAW", "PURFRIN2.RAW",
		    "PURFRIN3.RAW" }, 45, new Dimension(320000, 320000)),
	    8192, 4456448, false,-1, 2048, false, false,1000/2), 
    bossW6(
	    null, null, new BINModelingType("WBOSS6.BIN"), 6554,
	    4456448, false,-1, 2048, false, false,1000/2), 
    bossW7(
	    null, null, new BINModelingType("WBOSS7.BIN"), 25,
	    4456448, false,-1, 2048, false, false,1000/2), 
    bossW8(
	    null, null, new BINModelingType("WBOSS8.BIN"), 8192,
	    4456448, false,-1, 2048, false, false,1000/2), 
    enemyMissile(
	    null, null, new BINModelingType("BRADMIS.BIN"), 8192,
	    4194304, false,-1, 2048, false, false,1000/2), 
    MAM(
	    "MAM", "DOM", new BINModelingType("BRADMIS.BIN"), 16384,
	    0x400000, false,4, 2048, false, false,1000/2),
    // ////// THESE ARE NOT PART OF THE ORDINAL ORDER OF A DEF FILE AND MAY BE
    // RE-ORDERED
    SAD("SAD", "VIP", new BINModelingType("BRADMIS.BIN"), 32768,
	    0x400000, false, 5, 2048, false, true,1000/2), 
    SWT("SWT",
	    "BFM", new BINModelingType("BRADMIS.BIN"), 65536,
	    0x400000, false, 6, 2048, false, true,1000/2), 
    DAM("DAM",
	    "FFF", new FlatModelingType("FIRBAL0.RAW","SQGLSR.RAW", new Dimension(80000,
		    560000)), Integer.MAX_VALUE, 0, false, 7,
		    4194304, false, false,1000/2),
    LegionMissile("LM",//Warning: Speculative!
	    "LM", new FlatModelingType("FIRBAL0.RAW","SQGLSR.RAW", new Dimension(80000,
		    560000)), Integer.MAX_VALUE, 0, false, 7,
		    4194304, false, false,1000/2),
    IndependenceMissile("IM",//Warning: Speculative!
	    "IM", new FlatModelingType("FIRBAL0.RAW","SQGLSR.RAW", new Dimension(80000,
		    560000)), Integer.MAX_VALUE, 0, false, 7,
		    4194304, false, false,1000/2),
    DoomsdayMine("DM",//Warning: Speculative!
	    "DM", new FlatModelingType("FIRBAL0.RAW","SQGLSR.RAW", new Dimension(80000,
		    560000)), Integer.MAX_VALUE, 0, false, 7,
		    4194304, false, false,1000/2),
    Unknown0("DM",//Warning: Speculative!
	    "DM", new FlatModelingType("FIRBAL0.RAW","SQGLSR.RAW", new Dimension(80000,
		    560000)), Integer.MAX_VALUE, 0, false, 7,
		    4194304, false, false,1000/2),
    Unknown1("DM",//Warning: Speculative!
	    "DM", new FlatModelingType("FIRBAL0.RAW","SQGLSR.RAW", new Dimension(80000,
		    560000)), Integer.MAX_VALUE, 0, false, 7,
		    4194304, false, false,1000/2),
    Unknown2("DM",//Warning: Speculative!
	    "DM", new FlatModelingType("FIRBAL0.RAW","SQGLSR.RAW", new Dimension(80000,
		    560000)), Integer.MAX_VALUE, 0, false, 7,
		    4194304, false, false,1000/2),
    Unknown3("DM",//Warning: Speculative!
	    "DM", new FlatModelingType("FIRBAL0.RAW","SQGLSR.RAW", new Dimension(80000,
		    560000)), Integer.MAX_VALUE, 0, false, 7,
		    4194304, false, false,1000/2),
    Unknown4("DM",//Warning: Speculative!
	    "DM", new FlatModelingType("FIRBAL0.RAW","SQGLSR.RAW", new Dimension(80000,
		    560000)), Integer.MAX_VALUE, 0, false, 7,
		    4194304, false, false,1000/2),
    Unknown5("DM",//Warning: Speculative!
	    "DM", new FlatModelingType("FIRBAL0.RAW","SQGLSR.RAW", new Dimension(80000,
		    560000)), Integer.MAX_VALUE, 0, false, 7,
		    4194304, false, false,1000/2),
    Unknown6("DM",//Warning: Speculative!
	    "DM", new FlatModelingType("FIRBAL0.RAW","SQGLSR.RAW", new Dimension(80000,
		    560000)), Integer.MAX_VALUE, 0, false, 7,
		    4194304, false, false,1000/2),
    Unknown7("DM",//Warning: Speculative!
	    "DM", new FlatModelingType("FIRBAL0.RAW","SQGLSR.RAW", new Dimension(80000,
		    560000)), Integer.MAX_VALUE, 0, false, 7,
		    4194304, false, false,1000/2),
    Unknown8("DM",//Warning: Speculative!
	    "DM", new FlatModelingType("FIRBAL0.RAW","SQGLSR.RAW", new Dimension(80000,
		    560000)), Integer.MAX_VALUE, 0, false, 7,
		    4194304, false, false,1000/2),
    Unknown9("DM",//Warning: Speculative!
	    "DM", new FlatModelingType("FIRBAL0.RAW","SQGLSR.RAW", new Dimension(80000,
		    560000)), Integer.MAX_VALUE, 0, false, 7,
		    4194304, false, false,1000/2),
    Unknown10("DM",//Warning: Speculative!
	    "DM", new FlatModelingType("FIRBAL0.RAW","SQGLSR.RAW", new Dimension(80000,
		    560000)), Integer.MAX_VALUE, 0, false, 7,
		    4194304, false, false,1000/2);
    
    
    private final String       tvDisplayName, f3DisplayName;
    private final int 	       damage, speed, buttonToSelect, firingIntervalMS;
    private final ModelingType modelingType;
    private final boolean      laser, honing, sumWithProjectorVel;

    Weapon(String tvDisplayName, String f3DisplayName,
	    ModelingType modelingType, int damage, int speed, boolean sumWithProjectorVel,
	    int buttonToSelect, int hitRadius, boolean laser, boolean honing, int firingIntervalMS) {
	this.modelingType 	= modelingType;
	this.damage 		= damage;
	this.speed 		= speed;
	this.buttonToSelect 	= buttonToSelect;
	this.tvDisplayName 	= tvDisplayName;
	this.f3DisplayName 	= f3DisplayName;
	this.laser 		= laser;
	this.honing 		= honing;
	this.sumWithProjectorVel= sumWithProjectorVel;
	this.firingIntervalMS   = firingIntervalMS;
    }// end constructor

    /**
     * @return the damage
     */
    public int getDamage() {
	return damage;
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
	return speed;
    }

    /**
     * @return the java.awt.event.KeyEvent.KV** constant representing desired
     *         button, or Integer.MIN_VALUE if unavailable
     */
    public int getButtonToSelect() {
	return buttonToSelect;
    }

    /**
     * @return the tvDisplayName
     */
    public String getTvDisplayName() {
	return tvDisplayName;
    }

    /**
     * @return the f3DisplayName
     */
    public String getF3DisplayName() {
	return f3DisplayName;
    }

    @Override
    public String toString() {
	return "enum Weapon " + super.toString() + " tvName=" + tvDisplayName
		+ " f3Name=" + f3DisplayName;
    }

    /**
     * @return the modelingType
     */
    public ModelingType getModelingType() {
	return modelingType;
    }

    /**
     * @return the laser
     */
    public boolean isLaser() {
	return laser;

    }

    /**
     * @return the honing
     */
    public boolean isHoning() {
	return honing;
    }

    /**
     * @return the sumWithProjector
     */
    public boolean isSumWithProjectorVel() {
        return sumWithProjectorVel;
    }

    /**
     * @return the firingIntervalMS
     */
    public int getFiringIntervalMS() {
        return firingIntervalMS;
    }
}// end Weapon
