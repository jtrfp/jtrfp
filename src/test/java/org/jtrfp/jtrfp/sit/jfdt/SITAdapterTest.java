package org.jtrfp.jtrfp.sit.jfdt;

import org.jtrfp.jtrfp.sit.AbstractISitDataTest;
import org.jtrfp.jtrfp.sit.ISitData;

public class SITAdapterTest extends AbstractISitDataTest {

    @Override
    protected ISitData getSubject() {
	try {return new SITAdapter(new SITFile(getInputStream()));}
	catch(Exception e) {throw new RuntimeException(e);}
    }
}
