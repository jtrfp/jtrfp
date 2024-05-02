package org.jtrfp.jtrfp.bin;

import org.jtrfp.jtrfp.internal.bin.BinDataLoader;

public class BinDataLoaderTest extends AbstractIBinDataTest {

    @Override
    protected IBinData getSubject() {
	try {return new BinDataLoader().load(getInputStream());}
	catch(Throwable t) {
	    throw new RuntimeException(t);
	}
    }//end getSubject()

}//end BINDataLoaderTest
