package com.growthgenius.loan.service;

import com.growthgenius.loan.domain.Counsel;
import com.growthgenius.loan.dto.CounselDto.Request;
import com.growthgenius.loan.dto.CounselDto.Response;
import com.growthgenius.loan.exception.BaseException;
import com.growthgenius.loan.exception.ResultType;
import com.growthgenius.loan.repository.CounselRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CounselServiceImpl implements CounselService {

    private final CounselRepository counselRepository;

    @Override
    public Response create(Request request) {
        Counsel counsel = Counsel.from(request);
        counselRepository.save(counsel);
        return counsel.mapToResponse();
    }

    @Override
    public Response get(Long counselId) {
        Counsel counsel =
            counselRepository.findById(counselId).orElseThrow(() -> new BaseException(ResultType.SYSTEM_ERROR, "존재하지 않는 상담입니다."));
        return counsel.mapToResponse();
    }

    @Override
    @Transactional
    public Response update(Long counselId, Request request) {
        Counsel counsel = counselRepository.findById(counselId).orElseThrow(() -> new BaseException(ResultType.SYSTEM_ERROR));
        counsel.update(request);
        return counsel.mapToResponse();
    }

    @Override
    public void delete(Long counselId) {
        Counsel counsel = counselRepository.findById(counselId).orElseThrow(() -> new BaseException(ResultType.SYSTEM_ERROR));
        if (StringUtils.hasText(counsel.getMemo())) {
            throw new BaseException(ResultType.SYSTEM_ERROR, "상담 메모가 있는 경우 삭제하실 수 없습니다.");
        }
        counselRepository.deleteById(counselId);
    }

}
