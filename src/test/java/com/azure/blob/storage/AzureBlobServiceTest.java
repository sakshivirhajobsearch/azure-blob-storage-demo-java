package com.azure.blob.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class AzureBlobServiceTest {

	private static final String CONNECTION_STRING = "<YOUR_AZURE_BLOB_CONNECTION_STRING>";
	private static final String CONTAINER_NAME = "javademocontainer";
	private static final String LOCAL_FILE_PATH = "test-file.txt";
	private static final String DOWNLOADED_FILE_PATH = "downloaded-test-file.txt";

	@Test
	public void testBlobUploadDownload() throws Exception {
		
		AzureBlobService service = new AzureBlobService(CONNECTION_STRING, CONTAINER_NAME);

		// Upload
		service.uploadFile(LOCAL_FILE_PATH);

		// List blobs
		service.listBlobs();

		// Download
		service.downloadFile("test-file.txt", DOWNLOADED_FILE_PATH);

		// Assert download exists
		assertTrue(Files.exists(Paths.get(DOWNLOADED_FILE_PATH)));
	}
}