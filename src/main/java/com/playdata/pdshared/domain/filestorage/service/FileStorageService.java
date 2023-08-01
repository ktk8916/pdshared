package com.playdata.pdshared.domain.filestorage.service;

import com.playdata.pdshared.domain.filestorage.domain.entity.FileStorage;
import com.playdata.pdshared.domain.filestorage.exception.FileStorageNotFoundException;
import com.playdata.pdshared.domain.filestorage.repository.FileStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FileStorageService {

    private final FileStorageRepository fileStorageRepository;

    private FileStorage findById(Long id){
        return fileStorageRepository
                .findById(id)
                .orElseThrow(FileStorageNotFoundException::new);
    }
}
