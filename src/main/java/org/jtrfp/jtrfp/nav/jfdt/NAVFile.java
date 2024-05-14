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

package org.jtrfp.jtrfp.nav.jfdt;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jtrfp.jfdt.ThirdPartyParseable;

/**
 * Top-level read/write parser for Terminal Reality .NAV file assets. Parses to an internal delegate
 * either as a TVF3NAVFile or HellbenderNAVFile.<br><br>
 * <table>
 * <tr><th>SUPPORT</th><th>UNIT TESTED</th><th>INTEGRATION TESTED</th><th>FIELD PROVEN</th></tr>
 * <tr><td>Terminal Velocity</td><td>Yes</td><td>PODDoc</td><td>Yes</td></tr>
 * <tr><td>Fury3</td><td>Yes</td><td>PODDoc</td><td>Yes</td></tr>
 * <tr><td>Hellbender</td><td>No</td><td>PODDoc</td><td>No</td></tr>
 * </table>
 * 
 * @author Chuck Ritola
 *
 */

public class NAVFile implements NAVData {
    private NAVData delegate;
    
    public NAVFile(InputStream is) throws IOException, IllegalAccessException {
	final byte [] bytes = is.readAllBytes();
	//Try TVF3 version first
	try {
	    delegate = new TVF3NAVFile(new ByteArrayInputStream(bytes));
	} catch(Exception tvf3Exception) {
	    //Hellbender version
	    delegate = new HellbenderNAVFile(new ByteArrayInputStream(bytes));
	}
    }

    public ThirdPartyParseable getDelegate() {
        return (ThirdPartyParseable)delegate;
    }

    public void setDelegate(ThirdPartyParseable delegate) {
	if(!(delegate instanceof NAVData))
	    throw new IllegalArgumentException("Delegate must implement NAVData");
        this.delegate = (NAVData)delegate;
    }

    public int getNumNavigationPoints() {
	return delegate.getNumNavigationPoints();
    }

    public void setNumNavigationPoints(int numNavigationPoints) {
	delegate.setNumNavigationPoints(numNavigationPoints);
    }

    public List<? extends NAVSubObjectData> getNavObjects() {
	return delegate.getNavObjects();
    }

    public void setNavObjects(List<? extends NAVSubObjectData> newObjects) {
	delegate.setNavObjects(newObjects);
    }

}//end NAVFile
