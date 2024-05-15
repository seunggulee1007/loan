package com.growthgenius.loan.controller;

import com.growthgenius.loan.dto.CounselDto;
import com.growthgenius.loan.dto.ResponseDto;
import com.growthgenius.loan.service.CounselService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.growthgenius.loan.dto.ResponseDto.ok;

@RestController
@RequestMapping("/counsels")
@RequiredArgsConstructor
public class CounselController {

    private final CounselService counselService;

    public ResponseDto<CounselDto.Response> create(@RequestBody CounselDto.Request request) {
        return ok(counselService.create(request));
    }

}
