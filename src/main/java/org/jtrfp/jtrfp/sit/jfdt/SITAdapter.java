/*******************************************************************************
 * This file is part of jTRFP
 * Copyright (c) 2012-2024. See commit history for copyright owners.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     chuck - initial implementation
 ******************************************************************************/

package org.jtrfp.jtrfp.sit.jfdt;

import java.util.Objects;

import org.jtrfp.jtrfp.DataKey;
import org.jtrfp.jtrfp.Vertex3f;
import org.jtrfp.jtrfp.sit.ISitData;
import org.jtrfp.jtrfp.sit.SitBox;
import org.jtrfp.jtrfp.sit.SitDataKeys;
import org.jtrfp.jtrfp.sit.jfdt.SITFile.Box;

/**
 * Wraps a jFDT SITFile to implement the mtmX ISitData interface
 * @author Chuck Ritola
 *
 */

public class SITAdapter implements ISitData {
    private final SITFile delegate;
    private static DataKey [] USED_KEYS = {SitDataKeys.LEVEL_FILE_NAME, SitDataKeys.RACE_TRACK_NAME};
    
    public SITAdapter(SITFile delegate) {
	this.delegate = delegate;
    }//end constructor

    @Override
    public String getValue(DataKey key) {
	Objects.requireNonNull(key, "Key must not be null.");
	
	if(key == SitDataKeys.RACE_TRACK_NAME) {
	    return delegate.getRaceTrackName();
	} else if(key == SitDataKeys.LEVEL_FILE_NAME)
	    return delegate.getLvlFile();
	else
	    throw new UnsupportedOperationException("Did not recognize key");
    }//end getValue(...)

    @Override
    public DataKey[] getUsedKeys() {
	return USED_KEYS;
    }

    @Override
    public SitBox[] getBoxes() {
	return delegate.getBoxes().stream().map(x->buildSitBox(x)).toArray(SitBox[]::new);
    }
    
    private SitBox buildSitBox(Box in) {
	final SitBox result = new SitBox();
	result.setModelName(in.getModelBINFile());
	result.setPhi((float)in.getPhi());
	result.setPsi((float)in.getPsi());
	result.setTheta((float)in.getTheta());
	result.setPosition(new Vertex3f((float)in.getPosX(), (float)in.getPosY(), (float)in.getPosZ()));
	
	return result;
    }

}//end SITAdapter
