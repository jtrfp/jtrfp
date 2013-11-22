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
 ******************************************************************************/
package org.jtrfp.common.util.jfdt1;

import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class EndianAwareDataInputStream extends FilterInputStream implements
		Closeable, DataInput{
	ByteOrder order;
	DataInputStream in;
	long readTally;
	long markedReadTally;
	
	final byte [] w8 = new byte[8];
	final byte [] w4 = new byte[4];
	final byte [] w2 = new byte[2];
	
	public EndianAwareDataInputStream(DataInputStream in){
		super(in);
		this.in=in;
		}

	public long getReadTally()
		{return readTally;}
	
	@Override
	public int read() throws IOException{
		int result=in.read();
		if(result!=-1)readTally+=result;
		return result;
		}
	
	@Override
	public int read(byte [] b) throws IOException{
		int result=in.read(b);
		readTally+=result;
		return result;
		}
	
	@Override
	public boolean readBoolean() throws IOException{
		readTally++;
		return in.readBoolean();
		}

	@Override
	public byte readByte() throws IOException{
		readTally++;
		return in.readByte();
		}

	@Override
	public char readChar() throws IOException{
		readTally+=2;
		return in.readChar();
		}

	@Override
	public double readDouble() throws IOException{
		readTally+=8;
		if(order==ByteOrder.BIG_ENDIAN)
			{return readDouble();}
		else{
			in.read(w8);
			return ByteBuffer.wrap(w8).order(ByteOrder.LITTLE_ENDIAN).asDoubleBuffer().get();
			}
		}//end readDouble(...)

	@Override
	public float readFloat() throws IOException{
		readTally+=4;
		if(order==ByteOrder.BIG_ENDIAN)
			{return in.readFloat();}
		else{
			in.read(w4);
			return ByteBuffer.wrap(w4).order(ByteOrder.LITTLE_ENDIAN).asFloatBuffer().get();
			}
		}//end readFloat()

	@Override
	public void readFully(byte[] b) throws IOException
		{
		in.readFully(b);
		readTally+=b.length;
		}

	@Override
	public void readFully(byte[] b, int off, int len) throws IOException
		{
		in.readFully(b, off, len);
		readTally+=b.length;
		}

	@Override
	public int readInt() throws IOException
		{
		readTally+=4;
		if(order==ByteOrder.BIG_ENDIAN)
			{return in.readInt();}
		else
			{
			in.read(w4);
			return ByteBuffer.wrap(w4).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer().get();
			}
		}

	@Override
	@Deprecated
	public String readLine() throws IOException
		{return in.readLine();}

	@Override
	public long readLong() throws IOException{
		readTally+=8;
		if(order==ByteOrder.BIG_ENDIAN)
			{return in.readLong();}
		else{
			in.read(w8);
			return ByteBuffer.wrap(w8).order(ByteOrder.LITTLE_ENDIAN).asLongBuffer().get();
			}
		}//end readLong()

	@Override
	public short readShort() throws IOException{
		readTally+=2;
		if(order==ByteOrder.BIG_ENDIAN)
			{return readShort();}
		else{
			in.read(w2);
			return ByteBuffer.wrap(w2).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get();
			}
		}//end readShort()

	@Override
	public String readUTF() throws IOException{
		String result = in.readUTF();
		readTally+=result.length()+1;//+1 for terminator
		return result;
		}

	@Override
	public int readUnsignedByte() throws IOException{
		readTally++;
		return in.readUnsignedByte();
		}

	@Override
	public int readUnsignedShort() throws IOException{
		readTally+=2;
		return in.readUnsignedShort();
		}

	@Override
	public int skipBytes(int n) throws IOException{
		readTally+=n;
		return in.skipBytes(n);
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
	 * @return the in
	 */
	public DataInputStream getIn(){
		return in;
		}

	/**
	 * @param in the in to set
	 */
	public void setIn(DataInputStream in){
		this.in = in;
		}

	
	@Override
	public void mark(int len){
		markedReadTally=readTally;
		in.mark(len);
		}
	
	@Override
	public void reset() throws IOException{
		readTally=markedReadTally;
		in.reset();
		}
	}//end EndianAwareDataInputStream
