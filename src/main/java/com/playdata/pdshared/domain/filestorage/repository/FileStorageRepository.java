package com.playdata.pdshared.domain.filestorage.repository;

import com.playdata.pdshared.domain.filestorage.domain.entity.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileStorageRepository extends JpaRepository<FileStorage, Long> {
}
