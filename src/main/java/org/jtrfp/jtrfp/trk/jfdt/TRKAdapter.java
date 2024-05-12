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

package org.jtrfp.jtrfp.trk.jfdt;

import org.jtrfp.jtrfp.Vertex3f;
import org.jtrfp.jtrfp.trk.ITrkData;

/**
 * Wraps a jFDT TRKFile (not those found in CART) to implement the mtmX ITrkData interface
 * @author Chuck Ritola
 *
 */

public class TRKAdapter implements ITrkData {
    private final TRKFile delegate;
    
    public TRKAdapter(TRKFile delegate) {
	this.delegate = delegate;
    }

    @Override
    public String getTruckName() {
	return delegate.getTruckName();
    }

    @Override
    public String getTruckModelBaseName() {
	return delegate.getTruckModelBaseName();
    }

    @Override
    public String getTireModelBaseName() {
	return delegate.getTireModelBaseName();
    }

    @Override
    public String getAxleModelName() {
	return delegate.getAxleModelName();
    }

    @Override
    public String getShockTextureName() {
	return delegate.getShockTextureName();
    }

    @Override
    public String getBarTextureName() {
	return delegate.getBarTextureName();
    }

    @Override
    public Vertex3f getAxlebarOffset() {
	return delegate.getAxlebarOffset().asVertex3f();
    }

    @Override
    public Vertex3f getDriveshaftPos() {
	return delegate.getDriveshaftPos().asVertex3f();
    }

    @Override
    public Vertex3f getLeftFrontTirePos() {
	return new Vertex3f((float)delegate.getLeftFrontTirePosX(), (float)delegate.getLeftFrontTirePosY(), (float)delegate.getLeftFrontTirePosZ());
    }

    @Override
    public Vertex3f getRightFrontTirePos() {
	return new Vertex3f((float)delegate.getRightFrontTirePosX(), (float)delegate.getRightFrontTirePosY(), (float)delegate.getRightFrontTirePosZ());
    }

    @Override
    public Vertex3f getLeftRearTirePos() {
	return new Vertex3f((float)delegate.getLeftRearTirePosX(), (float)delegate.getLeftRearTirePosY(), (float)delegate.getLeftRearTirePosZ());
    }

    @Override
    public Vertex3f getRightRearTirePos() {
	return new Vertex3f((float)delegate.getRightRearTirePosX(), (float)delegate.getRightRearTirePosY(), (float)delegate.getRightRearTirePosZ());
    }

}//end TRKAdapter
