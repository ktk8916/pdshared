package com.playdata.pdshared.domain.filestorage.controller;

import com.playdata.pdshared.domain.filestorage.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/filestorage")
public class FileStorageController {

    private final FileStorageService fileStorageService;

    @PostMapping
    public void upload(@RequestParam("file") MultipartFile file){
        fileStorageService.upload(1L, file);
    }

    @GetMapping("/{fileStorageId}")
    public ResponseEntity<Resource> findVideo(@PathVariable("fileStorageId") Long fileStorageId) {
        Long memberId = 1L; // 임의의 멤버 id
        return fileStorageService.downloadFileById(fileStorageId, memberId);
    }

}
