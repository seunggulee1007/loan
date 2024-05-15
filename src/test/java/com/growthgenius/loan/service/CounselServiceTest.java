package com.growthgenius.loan.service;

import com.growthgenius.loan.domain.Counsel;
import com.growthgenius.loan.dto.CounselDto;
import com.growthgenius.loan.repository.CounselRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CounselServiceTest {

    @InjectMocks
    private CounselServiceImpl counselService;

    @Mock
    private CounselRepository counselRepository;

    @Test
    @DisplayName("")
    void should_return_response_of_new_counselEntity_when_request_counsel() {
        // given
        Counsel counsel = Counsel.builder()
            .name("Member Lee")
            .cellPhone("010-2342-9232")
            .email("leesg107@naver.com")
            .memo("대출을 받고 싶습니다.")
            .zipCode("123345")
            .address("서울특별시 강남구는 다 내땅")
            .addressDetail("구 자체가 내 건물이야")
            .build();

        CounselDto.Request request = CounselDto.Request.builder()
            .name("Member Lee")
            .cellPhone("010-2342-9232")
            .email("leesg107@naver.com")
            .memo("대출을 받고 싶습니다.")
            .zipCode("123345")
            .address("서울특별시 강남구는 다 내땅")
            .addressDetail("구 자체가 내 건물이야")
            .build();
        // when
        when(counselRepository.save(any())).thenReturn(counsel);

        CounselDto.Response response = counselService.create(request);

        // then
        assertThat(response.getName()).isSameAs(counsel.getName());

    }

}