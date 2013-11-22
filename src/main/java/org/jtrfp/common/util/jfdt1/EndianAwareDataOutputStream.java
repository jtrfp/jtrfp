/*******************************************************************************
 * This file is part of the JAVA FILE DESCRIPTION TOOLKIT (JFDT)
 * A library for parsing files and mapping their data to/from java Beans.
 * ...which is now part of the JAVA TERMINAL REALITY FILE PARSERS project.
 * Copyright (c) 2012,2013 Chuck Ritola and any contributors to these files.
 * 
 *     JFDT is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     JDFT is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with jTRFP.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/package org.jtrfp.common.util.jfdt1;

import java.io.Closeable;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class EndianAwareDataOutputStream extends FilterOutputStream implements
		Closeable, DataOutput
	{
	public EndianAwareDataOutputStream(DataOutputStream in){
		super(in);
		this.out=in;
		}

	ByteOrder order;
	DataOutputStream out;
	
	final byte [] w8 = new byte[8];
	final byte [] w4 = new byte[4];
	final byte [] w2 = new byte[2];
	@Override
	public void writeBoolean(boolean v) throws IOException{
		out.writeBoolean(v);
		}
	@Override
	public void writeByte(int v) throws IOException{
		out.writeByte(v);
		}
	@Override
	public void writeBytes(String v) throws IOException{
		out.writeBytes(v);
		}
	@Override
	public void writeChar(int v) throws IOException{
		out.writeChar(v);
		}
	@Override
	public void writeChars(String v) throws IOException{
		out.writeChars(v);
		}
	@Override
	public void writeDouble(double v) throws IOException{
		if(order==ByteOrder.BIG_ENDIAN)
			{out.writeDouble(v);}
		else{
			ByteBuffer.wrap(w8).order(ByteOrder.LITTLE_ENDIAN).asDoubleBuffer().put(v);
			out.write(w8);
			}
		}
	@Override
	public void writeFloat(float v) throws IOException{
		if(order==ByteOrder.BIG_ENDIAN)
			{out.writeFloat(v);}
		else{
			ByteBuffer.wrap(w4).order(ByteOrder.LITTLE_ENDIAN).asFloatBuffer().put(v);
			out.write(w4);
			}
		}
	@Override
	public void writeInt(int v) throws IOException{
		if(order==ByteOrder.BIG_ENDIAN)
			{out.writeInt(v);}
		else{
			ByteBuffer.wrap(w4).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer().put(v);
			out.write(w4);
			}
		}
	@Override
	public void writeLong(long v) throws IOException{
		if(order==ByteOrder.BIG_ENDIAN)
			{out.writeLong(v);}
		else{
			ByteBuffer.wrap(w8).order(ByteOrder.LITTLE_ENDIAN).asLongBuffer().put(v);
			out.write(w8);
			}
		}
	@Override
	public void writeShort(int v) throws IOException
		{
		if(order==ByteOrder.BIG_ENDIAN)
			{out.writeShort(v);}
		else{
			ByteBuffer.wrap(w2).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().put((short)v);
			out.write(w2);
			}
		}
	@Override
	public void writeUTF(String v) throws IOException{
		out.writeUTF(v);
		}
	/**
	 * @return the order
	 */
	public ByteOrder getOrder(){
		return order;
		}
	/**
	 * @param order the order to set
	 */
	public void setOrder(ByteOrder order){
		this.order = order;
		}
	/**
	 * @return the out
	 */
	public DataOutputStream getOut(){
		return out;
		}
	/**
	 * @param out the out to set
	 */
	public void setOut(DataOutputStream out){
		this.out = out;
		}
	}//end EndianAwareDataOutputStream
