package org.jtrfp.jtrfp.trk.jfdt;

import org.jtrfp.jtrfp.trk.AbstractITrkDataTest;
import org.jtrfp.jtrfp.trk.ITrkData;

public class TrkAdapterTest extends AbstractITrkDataTest {

    @Override
    protected ITrkData getSubject() {
	try {return new TRKAdapter(new TRKFile(getInputStream()));}
	catch(Exception e) {throw new RuntimeException(e);}
    }

}
