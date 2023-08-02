package com.playdata.pdshared.domain.filestorage.service;

import com.playdata.pdshared.domain.board.repository.BoardRepository;
import com.playdata.pdshared.domain.filestorage.domain.entity.FileStorage;
import com.playdata.pdshared.domain.filestorage.exception.FileSaveFailException;
import com.playdata.pdshared.domain.filestorage.repository.FileStorageRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
class FileStorageServiceTest {

    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    FileStorageRepository fileStorageRepository;
    @Autowired
    BoardRepository boardRepository;


    @Nested
    class 업로드_기능{
        @Test
        void 업로드_성공시_파일스토리지_저장() {
            //given
            String name = "image";
            String originalFilename = "test.png";
            MockMultipartFile file = new MockMultipartFile(name,
                    originalFilename,
                    "image/png",
                    "testdata".getBytes());

            //when
            fileStorageService.upload(1L, file);
            FileStorage fileStorage = fileStorageRepository.findById(1L).get();

            //then
            Assertions.assertThat(fileStorage.getOriginalName()).isEqualTo(originalFilename);
            Assertions.assertThat(fileStorage.getId()).isEqualTo(1L);
        }

        @Test
        void 업로드_실패시_예외(){
            // 숙제 ㅎㅎ;
        }
    }


}