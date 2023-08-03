package com.playdata.pdshared.domain.group.service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GroupServiceTest {

    @Autowired
    GroupService groupService;
    @Nested
    class 팀_조회{
        @Test
        void 팀_조회_성공(){
            //given
            //when
            //then
        }

        @Test
        void 없는_팀_조회시_예외(){
            //given
            //when
            //then
        }
    }

    @Nested
    class 팀_생성{
        @Test
        void 팀_생성_성공(){
            //given
            //when
            //then
        }
        
        @Test
        void 증복_이름_팀_생성시_예외(){
            //given
            //when
            //then
        }
    }

    @Nested
    class 팀_초대{
        @Test
        void 팀_초대_성공(){
            //given
            //when
            //then
        }

        @Test
        void 팀_중복멤버_초대시_예외(){
            //given
            //when
            //then
        }
    }
}