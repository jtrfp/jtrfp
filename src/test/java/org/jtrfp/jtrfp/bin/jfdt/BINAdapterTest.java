package org.jtrfp.jtrfp.bin.jfdt;

import org.jtrfp.jtrfp.bin.AbstractIBinDataTest;
import org.jtrfp.jtrfp.bin.IBinData;

public class BINAdapterTest extends AbstractIBinDataTest {

    @Override
    protected IBinData getSubject() {
	try {return new BINAdapter(new BINFile.Model(getInputStream()));}
	catch(Throwable t) {
	    throw new RuntimeException(t);
	}
    }//end getSubject()

}//end BINAdapterTest
