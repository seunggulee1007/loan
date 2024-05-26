package com.growthgenius.loan.service;

import com.growthgenius.loan.dto.CounselDto.Request;
import com.growthgenius.loan.dto.CounselDto.Response;

public interface CounselService {

    Response create(Request request);

    Response get(Long counselId);

    Response update(Long counselId, Request request);

    boolean delete(Long counselId);

}
