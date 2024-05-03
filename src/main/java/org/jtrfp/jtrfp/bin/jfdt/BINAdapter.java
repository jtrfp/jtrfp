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

package org.jtrfp.jtrfp.bin.jfdt;

import java.util.ArrayList;

import org.jtrfp.jfdt.ThirdPartyParseable;
import org.jtrfp.jtrfp.bin.IBinData;
import org.jtrfp.jtrfp.bin.IBinFace;
import org.jtrfp.jtrfp.bin.IBinTexCoord;
import org.jtrfp.jtrfp.bin.IBinVertex;
import org.jtrfp.jtrfp.bin.jfdt.BINFile.Model.DataBlock.FaceBlock;
import org.jtrfp.jtrfp.bin.jfdt.BINFile.Model.DataBlock.FaceBlock.FaceBlockVertex;
import org.jtrfp.jtrfp.bin.jfdt.BINFile.Model.DataBlock.FaceBlock.FaceBlockVertexWithUV;
import org.jtrfp.jtrfp.bin.jfdt.BINFile.Model.DataBlock.TextureBlock;
import org.jtrfp.jtrfp.bin.jfdt.BINFile.Model.DataBlock.VertexNormalsBlock;
import org.jtrfp.jtrfp.bin.jfdt.BINFile.Model.Vertex;

/**
 * Wraps a jFDT BINFile to implement the mtmX IBinData interface
 * @author Chuck Ritola
 *
 */
public class BINAdapter implements IBinData {
    private final BINFile.Model delegate;
    
    public BINAdapter(BINFile.Model delegate) {
	this.delegate = delegate;
    }

    @Override
    public int getId() {
	return IBinData.ID_MODEL;
    }

    @Override
    public int getVertexCount() {
	return delegate.getNumVertices();
    }

    @Override
    public IBinVertex[] getVertexes() {
	return delegate.getVertices().stream().map(x->new VertexWrapper(x)).toArray(IBinVertex[]::new);
    }
    
    static final class VertexWrapper implements IBinVertex {
	private final Vertex delegate;
	
	public VertexWrapper(Vertex delegate) {
	    this.delegate = delegate;
	}

	@Override
	public int getX() {
	    return delegate.getX();
	}

	@Override
	public int getY() {
	    return delegate.getY();
	}

	@Override
	public int getZ() {
	    return delegate.getZ();
	}
    }//end VertexWrapper

    @Override
    public IBinVertex[] getNormals() {
	return delegate.
		getDataBlocks().
		stream().
		filter(x->x instanceof VertexNormalsBlock).
		map(x->(VertexNormalsBlock)x).
		flatMap(x->x.getNormals().stream()).
		map(x->new VertexWrapper(x)).
		toArray(IBinVertex[]::new);
    }//end getNormals()

    @Override
    public IBinFace[] getFaces() {
	final ArrayList<IBinFace> result = new ArrayList<>();
	String boundTexture = null;
	for(ThirdPartyParseable db : delegate.getDataBlocks()) {
	    if(db instanceof TextureBlock) {
		boundTexture = ((TextureBlock)db).getTextureFileName();
	    } else if(db instanceof FaceBlock)
		result.add(new FaceBlockWrapper((FaceBlock)db,boundTexture));
	}//end for(dataBlocks)
	return result.toArray(new IBinFace[result.size()]);
    }//end getFaces()
    
    static final class FaceBlockWrapper implements IBinFace {
	private final FaceBlock delegate;
	private final String textureName;
	private final IBinVertex normal;
	
	public FaceBlockWrapper(FaceBlock delegate, String textureName) {
	    this.delegate = delegate;
	    this.textureName = textureName;
	    this.normal = new IBinVertex() {

		@Override
		public int getX() {
		    return delegate.getNormalX();
		}

		@Override
		public int getY() {
		    return delegate.getNormalY();
		}

		@Override
		public int getZ() {
		    return delegate.getNormalZ();
		}};
	}//end constructor

	@Override
	public int getTexCoordCount() {
	    return getTexCoords().length;
	}

	@Override
	public IBinTexCoord[] getTexCoords() {
	    return delegate.getVertices().stream().map(x->new TexCoordWrapper(x)).toArray(IBinTexCoord[]::new);
	}
	
	final class TexCoordWrapper implements IBinTexCoord {
	    private final float u,v;
	    private final int vertexIndex;
	    
	    public TexCoordWrapper(FaceBlockVertex fbv) {
		super();
		if(fbv instanceof FaceBlockVertexWithUV) {
		    final FaceBlockVertexWithUV uv = (FaceBlockVertexWithUV)fbv;
		    this.u = uv.getTextureCoordinateU() / (float)0xFF0000;
		    this.v = uv.getTextureCoordinateV() / (float)0xFF0000;
		} else {
		    this.u = 0; this.v = 0;
		}
		
		this.vertexIndex = fbv.getVertexIndex();
	    }//end constructor(...)
	    
	    @Override
	    public float getU() {
	        return u;
	    }
	    @Override
	    public float getV() {
	        return v;
	    }
	    @Override
	    public int getVertexIndex() {
	        return vertexIndex;
	    }
	}//end TexCoordWrapper

	@Override
	public IBinVertex getNormal() {
	    return normal;
	}

	@Override
	public int getFaceId() {
	    return delegate.getBlockID();
	}

	@Override
	public String getTextureName() {
	    return textureName;
	}
    }//end FaceBlockWrapper

    @Override
    public String[] getTextureNames() {
	return delegate.
		getDataBlocks().
		stream().
		filter(x->x instanceof TextureBlock).
		map(x->(TextureBlock)x).
		map(x->x.getTextureFileName()).
		toArray(String[]::new);
    }

    public BINFile.Model getDelegate() {
        return delegate;
    }

}//end BINAdapter
