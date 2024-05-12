package org.jtrfp.jtrfp.clr.jfdt;

import org.jtrfp.jtrfp.clr.AbstractIClrDataTest;
import org.jtrfp.jtrfp.clr.IClrData;

public class CLRAdapterTest extends AbstractIClrDataTest {

    @Override
    protected IClrData getSubject() {
	try {return new CLRAdapter(new CLRFile(getInputStream()));}
	catch(Throwable t) {throw new RuntimeException(t);}
    }

}