package com.ssafy.myapp.api.service;

import com.ssafy.myapp.api.request.ReviewRegisterReq;
import com.ssafy.myapp.api.response.ExpectListGetRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpectService {
    Page<ExpectListGetRes> findExpectation(Long id, Pageable pageable);
    Long addExpectation(Long id, ReviewRegisterReq expectInfo);
    boolean modifyExpectation(Long expectationId, ReviewRegisterReq expectInfo);
    boolean removeExpectation(Long expectationId);
}
