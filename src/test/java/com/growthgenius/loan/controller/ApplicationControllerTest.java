package com.growthgenius.loan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.growthgenius.loan.service.ApplicationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static com.growthgenius.loan.dto.ApplicationDto.Request;
import static com.growthgenius.loan.dto.ApplicationDto.Response;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ApplicationService applicationService;

    @Test
    @DisplayName("대출 신청")
    void createApplication() throws Exception {
        // given
        Request request = Request.builder()
            .name("seunggu")
            .cellPhone("010-1234-1234")
            .email("email@email.com")
            .hopeAmount(BigDecimal.valueOf(500000000))
            .build();
        // when && then
        this.mockMvc.perform(
                post("/applications")
                    .content(this.objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("result.desc").value("success"));
    }

    @Test
    @DisplayName("식별자로 신청서 조회")
    void findByApplicationId() throws Exception {
        // given
        String name = "seunggu";
        Request request = Request.builder()
            .name(name)
            .cellPhone("010-1234-1234")
            .email("email@email.com")
            .hopeAmount(BigDecimal.valueOf(500000000))
            .build();
        Response response = applicationService.create(request);
        // when
        this.mockMvc.perform(get("/applications/" + response.getApplicationId()))
            .andDo(print())
            .andExpect(jsonPath("result.desc").value("success"))
            .andExpect(jsonPath("data.name").value(name))
            .andExpect(jsonPath("data.applicationId").value(response.getApplicationId()))
        ;
        // then

    }

    @Test
    @DisplayName("신청서 수정")
    void updateByApplicationId() throws Exception {
        // given
        String name = "seunggu";
        Request request = Request.builder()
            .name(name)
            .cellPhone("010-1234-1234")
            .email("email@email.com")
            .hopeAmount(BigDecimal.valueOf(500000000))
            .build();
        Response response = applicationService.create(request);
        Long applicationId = response.getApplicationId();
        BigDecimal hopeAmount = BigDecimal.valueOf(55500202020L);
        request.setHopeAmount(hopeAmount);

        // when && then
        this.mockMvc.perform(
                patch("/applications/" + applicationId).contentType(MediaType.APPLICATION_JSON).content(this.objectMapper.writeValueAsString(
                    request))
            ).andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("data.hopeAmount").value(hopeAmount));

    }

}