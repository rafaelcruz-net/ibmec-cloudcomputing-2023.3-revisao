package br.edu.ibmec.cloudcomputing.revisao.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

@Service
public class AzureStorageAccountService {
    
    public String uploadFileToAzure(MultipartFile file) throws IOException {
        String connectionString = "DefaultEndpointsProtocol=https;AccountName=ibmecstoragerc;AccountKey=TNA9ebEDTMqXSnwQH/fNry40GmBbDtWC+dKbdYf12JDvfK9fwmNUBUd/+SlA6mpkrJ2Zu/U/vkKi+AStCzQ4BQ==;EndpointSuffix=core.windows.net";

        BlobContainerClient client = new BlobContainerClientBuilder()
            .connectionString(connectionString)
            .containerName("images")
            .buildClient();

        BlobClient blob = client.getBlobClient(file.getOriginalFilename());

        blob.upload(file.getInputStream(), file.getSize(), true);

        return "https://ibmecstoragerc.blob.core.windows.net/images/" + file.getOriginalFilename();

    }
    
}
