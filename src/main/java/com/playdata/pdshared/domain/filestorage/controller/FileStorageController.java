package com.playdata.pdshared.domain.filestorage.controller;

import com.playdata.pdshared.domain.filestorage.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/filestorage")
public class FileStorageController {

    private final FileStorageService fileStorageService;
}
