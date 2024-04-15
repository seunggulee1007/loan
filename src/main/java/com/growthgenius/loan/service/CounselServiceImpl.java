package com.growthgenius.loan.service;

import com.growthgenius.loan.domain.Counsel;
import com.growthgenius.loan.dto.CounselDto.Request;
import com.growthgenius.loan.dto.CounselDto.Response;
import com.growthgenius.loan.repository.CounselRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
