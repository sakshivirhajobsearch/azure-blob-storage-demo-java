package com.azure.blob.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AzureBlobServiceTest {

	@Test
	public void testBlobUploadDownload() throws Exception {
		AzureBlobService blobService = new AzureBlobService();

		String blobName = "test-blob.txt";
		String originalContent = "Hello Azure Blob!";
		blobService.uploadBlob(blobName, originalContent);

		String downloadedContent = blobService.downloadBlob(blobName);
		assertEquals(originalContent, downloadedContent);
	}
}
