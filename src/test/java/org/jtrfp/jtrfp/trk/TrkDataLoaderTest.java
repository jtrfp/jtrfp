package org.jtrfp.jtrfp.trk;

import org.jtrfp.jtrfp.internal.trk.TrkDataLoader;

public class TrkDataLoaderTest extends AbstractITrkDataTest {

    @Override
    protected ITrkData getSubject() {
	try {return TrkDataLoader.load(getInputStream());}
	catch(Exception e) {throw new RuntimeException(e);}
    }

}
