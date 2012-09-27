package jtrfp;

import java.io.File;
import java.io.IOException;

import jtrfp.common.FileLoadException;
import jtrfp.common.FileStoreException;
import jtrfp.common.pod.PodDataFormat;
import jtrfp.common.pod.PodFile;
import jtrfp.common.pod.PodLstFile;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class PodFileTest {
	
	@Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
	
	private static final String LST_FILE_NAME = "cockpit.lst";
	
	private static final String POD_FILE_NAME = "cockpit.pod";
	
	private static final String NEW_POD_FILE_NAME = "cockpit2.pod";
	
	private PodFile getMtm2PodFile() {
		File file = new File(ITestConfig.MTM2_DIR, POD_FILE_NAME);
		
		Assert.assertTrue("Test POD file does not exist.", file.exists() && file.isFile());
		
		return new PodFile(file);
	}
	
	@Test
	public void testCreateFromLst() throws FileLoadException, FileStoreException, IOException {
		PodFile podFile = getMtm2PodFile();

		PodLstFile plf = PodLstFile.fromPodData(podFile.getData());
		File outDir = temporaryFolder.newFolder();
		File file = new File(outDir, LST_FILE_NAME);
		plf.toFile(file);
		
		
		podFile.getData().extractAll(outDir);
		
		PodFile newPodFile = PodFile.createFromLst(
				outDir, 
				PodLstFile.fromFile(file), 
				new File(outDir, NEW_POD_FILE_NAME),
				PodDataFormat.POD1);
		
		Assert.assertEquals(
				"Mismatching entry count.", 
				podFile.getData().getEntryCount(), 
				newPodFile.getData().getEntryCount());
	}
}
