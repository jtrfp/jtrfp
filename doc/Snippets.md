# Examples showing how to use jtrfp API

## #1

Shows how to store all BIN files from a specified POD file to disk.

See: https://github.com/jtrfp/jtrfp/blob/master/src/snippets/java/jtrfp/Snippet01.java

{{{
String podFilePath = ...; // path to POD file
String outDir = ...; // path to output directory

File file = new File(podFilePath);
PodFile podFile = new PodFile(file);

IPodData data = podFile.getData();
IBinPodFileEntry[] entries = (IBinPodFileEntry[]) data.findEntries(IBinPodFileEntry.class);

for (IBinPodFileEntry entry : entries) {
	File binFile = new File(outDir, entry.getPath());
	binFile.getParentFile().mkdir(); // ensure directory exists
	entry.toFile(binFile);
}
}}}