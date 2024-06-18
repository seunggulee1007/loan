package com.growthgenius.loan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.growthgenius.loan.dto.CounselDto;
import com.growthgenius.loan.service.CounselService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CounselControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CounselService counselService;

    @Test
    @DisplayName("상담 신청 성공")
    void create_counsel() throws Exception {
        // given
        String name = "Member Lee";
        CounselDto.Request request = CounselDto.Request.builder()
            .name(name)
            .cellPhone("010-2342-9232")
            .email("leesg107@naver.com")
            .memo("대출을 받고 싶습니다.")
            .zipCode("12345")
            .address("서울특별시 강남구는 다 내땅")
            .addressDetail("구 자체가 내 건물이야")
            .build();
        // when && then
        this.mockMvc.perform(
                post("/counsels").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))
            ).andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("result.desc").value("success"))
            .andExpect(jsonPath("data.name").isString())
            .andExpect(jsonPath("data.name").value(name))
        ;

    }

    @Test
    @DisplayName("상담 식별자로 상담 조회 - 성공")
    void findCounselByCounselId() throws Exception {
        // given
        CounselDto.Request request = CounselDto.Request.builder()
            .name("Seunggu lee")
            .cellPhone("010-2342-9232")
            .email("leesg107@naver.com")
            .memo("대출을 받고 싶습니다.")
            .zipCode("12345")
            .address("서울특별시 강남구는 다 내땅")
            .addressDetail("구 자체가 내 건물이야")
            .build();
        CounselDto.Response saved = counselService.create(request);
        // when
        // then
        mockMvc.perform(get("/counsels/" + saved.getCounselId()))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("result.desc").value("success"))
            .andExpect(jsonPath("data.counselId").isNumber())
            .andExpect(jsonPath("data.counselId").value(saved.getCounselId()));

    }

}