package com.azure.blob.storage;

import java.io.File;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;

public class AzureBlobService {

	private final BlobServiceClient blobServiceClient;
	private final BlobContainerClient containerClient;

	public AzureBlobService(String connectionString, String containerName) {
		
		this.blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
		this.containerClient = blobServiceClient.getBlobContainerClient(containerName);
		if (!this.containerClient.exists()) {
			this.containerClient.create();
		}
	}

	public void uploadFile(String filePath) {

		File file = new File(filePath);
		BlobClient blobClient = containerClient.getBlobClient(file.getName());
		blobClient.uploadFromFile(filePath, true);
	}

	public void downloadFile(String blobName, String downloadPath) {

		BlobClient blobClient = containerClient.getBlobClient(blobName);
		blobClient.downloadToFile(downloadPath, true);
	}

	public void listBlobs() {

		for (BlobItem blobItem : containerClient.listBlobs()) {
			System.out.println("Blob: " + blobItem.getName());
		}
	}
}