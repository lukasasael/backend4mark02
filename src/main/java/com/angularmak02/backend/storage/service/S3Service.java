package com.angularmak02.backend.storage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private static final String BUCKET_NAME = "mark02-storage";

    private final S3Client s3Client;

    public String upload(MultipartFile file) throws IOException {

        String key =
                UUID.randomUUID()
                        + "-"
                        + file.getOriginalFilename();

        PutObjectRequest request =
                PutObjectRequest.builder()
                        .bucket(BUCKET_NAME)
                        .key(key)
                        .contentType(file.getContentType())
                        .build();

        s3Client.putObject(
                request,
                RequestBody.fromBytes(file.getBytes())
        );

        return key;
    }
}
