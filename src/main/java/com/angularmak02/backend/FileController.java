package com.angularmak02.backend;

import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.angularmak02.backend.storage.service.S3Service;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {

    private final S3Service s3Service;

    @PostMapping
    public ResponseEntity<String> upload(
            @RequestParam MultipartFile file
    ) throws IOException {

        return ResponseEntity.ok(
                s3Service.upload(file)
        );
    }
}