package com.growthgenius.loan.controller;

import com.growthgenius.loan.dto.ApplicationDto;
import com.growthgenius.loan.dto.ResponseDto;
import com.growthgenius.loan.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.growthgenius.loan.dto.ResponseDto.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applications")
public class ApplicationController extends AbstractController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseDto<ApplicationDto.Response> create(@RequestBody ApplicationDto.Request request) {
        return ok(applicationService.create(request));
    }

}
