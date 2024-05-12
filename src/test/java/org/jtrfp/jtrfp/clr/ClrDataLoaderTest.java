package org.jtrfp.jtrfp.clr;

import org.jtrfp.jtrfp.internal.clr.ClrDataLoader;

public class ClrDataLoaderTest extends AbstractIClrDataTest {

    @Override
    protected IClrData getSubject() {
	try {return ClrDataLoader.load(getInputStream());}
	catch(Throwable t) {throw new RuntimeException(t);}
    }

}
