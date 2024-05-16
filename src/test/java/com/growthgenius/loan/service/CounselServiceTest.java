package com.growthgenius.loan.service;

import com.growthgenius.loan.domain.Counsel;
import com.growthgenius.loan.dto.CounselDto;
import com.growthgenius.loan.exception.BaseException;
import com.growthgenius.loan.exception.ResultType;
import com.growthgenius.loan.repository.CounselRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    @DisplayName("상담 식별자로 조회 성공")
    void findByCounselId_success() {
        // given
        Long counselId = 1L;
        Counsel counsel = Counsel.builder().id(counselId).build();
        // when
        when(counselRepository.findById(counselId)).thenReturn(Optional.of(counsel));

        CounselDto.Response response = counselService.get(counselId);
        // then
        assertThat(response.getCounselId()).isEqualTo(counselId);

    }

    @Test
    @DisplayName("상담 식별자로 조회 실패 - 존재하지 않음")
    void findByCounselId_fail_not_exist() {
        // given
        Long counselId = 1L;
        // when
        when(counselRepository.findById(counselId)).thenThrow(new BaseException(ResultType.SYSTEM_ERROR));
        // then
        assertThrows(BaseException.class, () -> counselService.get(counselId));

    }

}