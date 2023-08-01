package com.playdata.pdshared.domain.filestorage.repository;

import com.playdata.pdshared.domain.filestorage.domain.entity.Downloads;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DownloadsRepository extends JpaRepository<Downloads, Long> {
}
