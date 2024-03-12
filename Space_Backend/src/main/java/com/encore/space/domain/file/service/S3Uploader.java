package com.encore.space.domain.file.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;

@Component
public class S3Uploader {

    private final AmazonS3 amazonS3Client;

    public S3Uploader(AmazonS3 amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public String uploadFile(String bucketName, String keyName, File file) {
        // Upload the file
        amazonS3Client.putObject(new PutObjectRequest(bucketName, keyName, file));

        // Generate the pre-signed URL
        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60 * 24 * 7; // Add 7 days
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, keyName)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);

        URL url = amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest);

        return url.toString();
    }
}