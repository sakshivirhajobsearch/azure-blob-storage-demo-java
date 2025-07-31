package com.azure.blob.storage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

public class AzureBlobService {

	private final BlobServiceClient blobServiceClient;
	private final String containerName = "mycontainer";

	public AzureBlobService() {
		String connectionString = System.getenv("AZURE_STORAGE_CONNECTION_STRING");
		if (connectionString == null || connectionString.isEmpty()) {
			throw new IllegalArgumentException("AZURE_STORAGE_CONNECTION_STRING environment variable is not set.");
		}

		blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();

		// Ensure container exists
		BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
		if (!containerClient.exists()) {
			containerClient.create();
		}
	}

	public void uploadBlob(String blobName, String content) throws IOException {
		BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerName).getBlobClient(blobName);
		InputStream dataStream = new ByteArrayInputStream(content.getBytes());
		blobClient.upload(dataStream, content.length(), true);
	}

	public String downloadBlob(String blobName) throws IOException {
		BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerName).getBlobClient(blobName);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		blobClient.download(outputStream);
		return outputStream.toString();
	}
}
