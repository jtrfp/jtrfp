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
package org.jtrfp.jtrfp.tdf.jfdt;

import java.io.IOException;
import java.io.InputStream;

import org.jtrfp.jfdt.ClassInclusion;
import org.jtrfp.jfdt.Parser;
import org.jtrfp.jfdt.SelfParsingFile;
import org.jtrfp.jfdt.ThirdPartyParseable;
import org.jtrfp.jfdt.UnrecognizedFormatException;
import org.jtrfp.jtrfp.jfdt.DirectionVector;
import org.jtrfp.jtrfp.jfdt.TRParsers;

/**
 * Read/Write parses a Terminal Reality TDF asset file.
 * TDFs define locations for tunnel entrances, exits, LVL definitions and ground textures
 * @author Chuck Ritola
 *
 */

public class TDFFile extends SelfParsingFile {
    int numTunnels;
    Tunnel[] tunnels;
    
    public TDFFile(InputStream is) throws IllegalAccessException, IOException {
	super(is);
    }
    
    public TDFFile() {}

    @Override
    public void describeFormat(Parser prs) throws UnrecognizedFormatException {
	prs.ignoreEOF(true);//numTunnels will default to zero and we'll finish safely.
	prs.stringEndingWith(TRParsers.LINE_DELIMITERS, prs.property("numTunnels", int.class),
		false);
	for (int i = 0; i < getNumTunnels(); i++) {
	    prs.subParseProposedClasses(
		    prs.indexedProperty("tunnels", Tunnel.class, i),
		    ClassInclusion.classOf(Tunnel.class));
	}
    }

    public static class Tunnel implements ThirdPartyParseable {
	String tunnelLVLFile;
	DirectionVector entrance;
	DirectionVector exit;
	TunnelLogic entranceLogic;
	String unused1;
	String entranceTerrainTextureFile;
	TunnelLogic exitLogic;
	String unused2;
	String exitTerrainTextureFile;
	ExitMode exitMode;

	@Override
	public void describeFormat(Parser prs)
		throws UnrecognizedFormatException {
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS,
		    prs.property("tunnelLVLFile", String.class), false);
	    prs.subParseProposedClasses(
		    prs.property("entrance", DirectionVector.class),
		    ClassInclusion.classOf(DirectionVector.class));
	    prs.subParseProposedClasses(
		    prs.property("exit", DirectionVector.class),
		    ClassInclusion.classOf(DirectionVector.class));
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS,
		    prs.property("entranceLogic", TunnelLogic.class), false);
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS, prs.property("unused1", String.class),
		    false);
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS,
		    prs.property("entranceTerrainTextureFile", String.class),
		    false);
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS,
		    prs.property("exitLogic", TunnelLogic.class), false);
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS, prs.property("unused2", String.class),
		    false);
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS,
		    prs.property("exitTerrainTextureFile", String.class), false);
	    prs.stringEndingWith(TRParsers.LINE_DELIMITERS,
		    prs.property("exitMode", ExitMode.class), false);
	}

	/**
	 * @return the tunnelLVLFile
	 */
	public String getTunnelLVLFile() {
	    return tunnelLVLFile;
	}

	/**
	 * @param tunnelLVLFile
	 *            the tunnelLVLFile to set
	 */
	public void setTunnelLVLFile(String tunnelLVLFile) {
	    this.tunnelLVLFile = tunnelLVLFile;
	}

	/**
	 * @return the entrance
	 */
	public DirectionVector getEntrance() {
	    return entrance;
	}

	/**
	 * @param entrance
	 *            the entrance to set
	 */
	public void setEntrance(DirectionVector entrance) {
	    this.entrance = entrance;
	}

	/**
	 * @return the exit
	 */
	public DirectionVector getExit() {
	    return exit;
	}

	/**
	 * @param exit
	 *            the exit to set
	 */
	public void setExit(DirectionVector exit) {
	    this.exit = exit;
	}

	/**
	 * @return the entranceLogic
	 */
	public TunnelLogic getEntranceLogic() {
	    return entranceLogic;
	}

	/**
	 * @param entranceLogic
	 *            the entranceLogic to set
	 */
	public void setEntranceLogic(TunnelLogic entranceLogic) {
	    this.entranceLogic = entranceLogic;
	}

	/**
	 * @return the unused1
	 */
	public String getUnused1() {
	    return unused1;
	}

	/**
	 * @param unused1
	 *            the unused1 to set
	 */
	public void setUnused1(String unused1) {
	    this.unused1 = unused1;
	}

	/**
	 * @return the entranceTerrainTextureFile
	 */
	public String getEntranceTerrainTextureFile() {
	    return entranceTerrainTextureFile;
	}

	/**
	 * @param entranceTerrainTextureFile
	 *            the entranceTerrainTextureFile to set
	 */
	public void setEntranceTerrainTextureFile(
		String entranceTerrainTextureFile) {
	    this.entranceTerrainTextureFile = entranceTerrainTextureFile;
	}

	/**
	 * @return the exitLogic
	 */
	public TunnelLogic getExitLogic() {
	    return exitLogic;
	}

	/**
	 * @param exitLogic
	 *            the exitLogic to set
	 */
	public void setExitLogic(TunnelLogic exitLogic) {
	    this.exitLogic = exitLogic;
	}

	/**
	 * @return the unused2
	 */
	public String getUnused2() {
	    return unused2;
	}

	/**
	 * @param unused2
	 *            the unused2 to set
	 */
	public void setUnused2(String unused2) {
	    this.unused2 = unused2;
	}

	/**
	 * @return the exitTerrainTextureFile
	 */
	public String getExitTerrainTextureFile() {
	    return exitTerrainTextureFile;
	}

	/**
	 * @param exitTerrainTextureFile
	 *            the exitTerrainTextureFile to set
	 */
	public void setExitTerrainTextureFile(String exitTerrainTextureFile) {
	    this.exitTerrainTextureFile = exitTerrainTextureFile;
	}

	/**
	 * @return the exitMode
	 */
	public ExitMode getExitMode() {
	    return exitMode;
	}

	/**
	 * @param exitMode
	 *            the exitMode to set
	 */
	public void setExitMode(ExitMode exitMode) {
	    this.exitMode = exitMode;
	}
    }// end Tunnel

    public static enum TunnelLogic {
	invisible, visible, visibleUnlessBoss, unknown // TODO: Figure this out.
    }

    public static enum ExitMode {
	exitToOverworld, exitToChamber;
    }

    /**
     * @return the numTunnels
     */
    public int getNumTunnels() {
	return numTunnels;
    }

    /**
     * @param numTunnels
     *            the numTunnels to set
     */
    public void setNumTunnels(int numTunnels) {
	this.numTunnels = numTunnels;
    }

    /**
     * @return the tunnels
     */
    public Tunnel[] getTunnels() {
	return tunnels;
    }

    /**
     * @param tunnels
     *            the tunnels to set
     */
    public void setTunnels(Tunnel[] tunnels) {
	this.tunnels = tunnels;
    }
}// end TDFFile
