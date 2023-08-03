package com.playdata.pdshared.domain.board.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playdata.pdshared.domain.board.domain.entity.ViewType;
import com.playdata.pdshared.domain.board.domain.request.BoardRequest;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class BoardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void save() throws Exception {

        //given
        BoardRequest boardRequest = new BoardRequest("내용","제목", ViewType.PUBLIC,null);

        String s = objectMapper.writeValueAsString(boardRequest);
        MockMultipartFile board = new MockMultipartFile(
                "req", "req.json", "application/json", s.getBytes(StandardCharsets.UTF_8));
        MockMultipartFile file = new MockMultipartFile(
                "file", "test.txt", "text/plain", "Hello, World!".getBytes());


        //when and then

        mockMvc.perform(
                        multipart(HttpMethod.POST,"/board")
                                .file(file)
                                .file(board)
                                .header("Authorization", "your-auth-token")

//                                .content(objectMapper.writeValueAsString(boardRequest))
//                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())
                .andDo(document("board/save",
                        requestHeaders(
                                headerWithName("Authorization").description("Authorization token")
                        ),
                        requestParts(
                                partWithName("file").description("업로드된 파일"),
                                partWithName("req").description("JSON 형식의 BoardRequest 객체")
                        ),
                        requestPartFields("req",
                                fieldWithPath("content").description("BoardRequest의 내용 필드에 대한 설명"),
                                fieldWithPath("title").description("BoardRequest의 제목 필드에 대한 설명"),
                                fieldWithPath("viewType").description("BoardRequest의 viewType 필드에 대한 설명").optional(),
                                fieldWithPath("hashtags").description("BoardRequest의 additionalProperty 필드에 대한 설명").optional()
                        )
                ));



    }

    @Test
    void find() {
    }

    @Test
    void like() {
    }

    @Test
    void comment() {
    }

    @Test
    void group() {
    }
}