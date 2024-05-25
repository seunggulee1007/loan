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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        CounselDto.Request request = getRequest(name);
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
        CounselDto.Response saved = saveAndGetResponse();
        // when
        // then
        mockMvc.perform(get("/counsels/" + saved.getCounselId()))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("result.desc").value("success"))
            .andExpect(jsonPath("data.counselId").isNumber())
            .andExpect(jsonPath("data.counselId").value(saved.getCounselId()));

    }

    @Test
    @DisplayName("상담 수정 기능")
    void updateCounsel() throws Exception {
        // given
        CounselDto.Response saved = saveAndGetResponse();
        String name = "승구";
        CounselDto.Request updateRequest = getRequest(name);
        // when & then
        this.mockMvc.perform(patch("/counsels/" + saved.getCounselId()).content(objectMapper.writeValueAsString(updateRequest)).contentType(
                MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("result.desc").value("success"))
            .andExpect(jsonPath("data.name").value(name));

    }

    @Test
    @DisplayName("상담 삭제 테스트 - 성공")
    void deleteById() throws Exception {
        // given
        CounselDto.Response saved = saveAndGetResponse();
        // when & then
        this.mockMvc.perform(delete("/counsels/" + saved.getCounselId()))
            .andDo(print())
            .andExpect(status().isOk());

        this.mockMvc.perform(get("/counsels/" + saved.getCounselId()))
            .andDo(print())
            .andExpect(status().is4xxClientError())
        ;

    }

    @Test
    @DisplayName("상담 삭제 실패 - 존재하지 않는 상담")
    void deleteById_fail_not_present_counsel() throws Exception {
        // given
        long id = 9999L;
        // when && then
        this.mockMvc.perform(delete("/counsels/" + id))
            .andDo(print())
            .andExpect(status().is4xxClientError());

    }

    private static CounselDto.Request getRequest(String name) {
        return CounselDto.Request.builder()
            .name(name)
            .cellPhone("010-2342-9232")
            .email("leesg107@naver.com")
            .memo("대출을 받고 싶습니다.")
            .zipCode("12345")
            .address("서울특별시 강남구는 다 내땅")
            .addressDetail("구 자체가 내 건물이야")
            .build();
    }

    private CounselDto.Response saveAndGetResponse() {
        CounselDto.Request request = getRequest("Seunggu lee");
        return counselService.create(request);
    }

}