package org.jtrfp.jtrfp.sit;

import org.jtrfp.jtrfp.internal.sit.SitDataLoader;

public class SitDataLoaderTest extends AbstractISitDataTest {

    @Override
    protected ISitData getSubject() {
	try {return SitDataLoader.load(getInputStream());}
	catch(Exception e) {throw new RuntimeException(e);}
    }

}//end SitDataLoaderTest
