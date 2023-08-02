package com.playdata.pdshared.domain.filestorage.service;

import com.playdata.pdshared.domain.filestorage.domain.entity.Downloads;
import com.playdata.pdshared.domain.filestorage.domain.entity.FileStorage;
import com.playdata.pdshared.domain.filestorage.exception.FileNotExistsException;
import com.playdata.pdshared.domain.filestorage.exception.FileSaveFailException;
import com.playdata.pdshared.domain.filestorage.exception.FileStorageNotFoundException;
import com.playdata.pdshared.domain.filestorage.repository.DownloadsRepository;
import com.playdata.pdshared.domain.filestorage.repository.FileStorageRepository;
import com.playdata.pdshared.domain.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Transactional
public class FileStorageService {

    private final FileStorageRepository fileStorageRepository;
    private final DownloadsRepository downloadsRepository;

    @Value("${file.dir}")
    private String SAVE_DIR;

    private FileStorage findById(Long id){
        return fileStorageRepository
                .findById(id)
                .orElseThrow(FileStorageNotFoundException::new);
    }

    public ResponseEntity<Resource> downloadFileById(Long fileStorageId, Long memberId){
        FileStorage fileStorage = findById(fileStorageId);
        String filePath = SAVE_DIR + fileStorage.getSavePath();
        Resource resource = getResource(filePath);

        Member member = Member.builder()
                .id(memberId)
                .build();

        Downloads downloads = Downloads.of(member, fileStorage);
        fileStorage.getBoard().addDownloadCount();
        downloadsRepository.save(downloads);

        return getResourceResponseEntity(resource);
        }

        private Resource getResource(String filePath) {
            Path path = Paths.get(filePath);
            try {
                return new UrlResource(path.toUri());
            } catch (MalformedURLException e){
            throw new RuntimeException("나중에 바꿔줄 예욈");
        }
    }

    private ResponseEntity<Resource> getResourceResponseEntity(Resource resource) {

        if (!resource.exists()) {
            throw new FileNotExistsException(resource.getFilename());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("Content-Disposition", "attachment; filename=" + resource.getFilename());

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    public void upload(Long boardId, MultipartFile file){

        FileStorage fileStorage = FileStorage.of(boardId, file);
        String savedPath = SAVE_DIR + fileStorage.getSavePath();

        fileStorageRepository.save(fileStorage);

        try {
            file.transferTo(new File(savedPath));
        } catch (IOException e) {
            throw new FileSaveFailException();
        }
    }
}
