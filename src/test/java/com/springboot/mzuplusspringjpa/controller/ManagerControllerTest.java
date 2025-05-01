package com.springboot.mzuplusspringjpa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.manager.ManagerLoginDto;
import com.springboot.mzuplusspringjpa.dto.manager.ManagerRegisterDto;
import com.springboot.mzuplusspringjpa.enums.Result;
import com.springboot.mzuplusspringjpa.service.manager.ManagerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ManagerController.class)
class ManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean  // @MockBean을 사용하여 ManagerService를 모킹
    private ManagerService managerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 로그인_성공_테스트() throws Exception {
        // given
        ManagerLoginDto loginDto = new ManagerLoginDto("manager@test.com", "1234");
        ResponseDto responseDto = ResponseDto.builder()
                .result(Result.SUCCESS)
                .build();

        when(managerService.login(any(ManagerLoginDto.class))).thenReturn(responseDto);

        // when & then
        mockMvc.perform(post("/api/managers/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto))
                        .session(new MockHttpSession()))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.result").value("SUCCESS"));
    }

    @Test
    void 로그인_실패_테스트() throws Exception {
        // given
        ManagerLoginDto loginDto = new ManagerLoginDto("wrong@test.com", "1234");
        ResponseDto responseDto = ResponseDto.builder()
                .result(Result.FAIL)
                .build();

        when(managerService.login(any(ManagerLoginDto.class))).thenReturn(responseDto);

        // when & then
        mockMvc.perform(post("/api/managers/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto))
                        .session(new MockHttpSession()))
                .andExpect(status().isInternalServerError());
        // 실패 시에는 본문이 없으므로 jsonPath를 검증하지 않음
    }
    @Test
    void 회원가입_성공_테스트() throws Exception {
        ManagerRegisterDto registerDto = new ManagerRegisterDto("홍길동", "email@test.com", "pw123");
        ResponseDto responseDto = ResponseDto.builder()
                .result(Result.SUCCESS)
                .data("등록된매니저")
                .build();

        when(managerService.register(any(ManagerRegisterDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/managers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("SUCCESS"))
                .andExpect(jsonPath("$.data").value("등록된매니저"));
    }

    @Test
    void 회원가입_중복_테스트() throws Exception {
        ManagerRegisterDto registerDto = new ManagerRegisterDto("홍길동", "email@test.com", "pw123");
        ResponseDto responseDto = ResponseDto.builder()
                .result(Result.DUPLICATE)
                .build();

        when(managerService.register(any(ManagerRegisterDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/managers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerDto)))
                .andExpect(status().isConflict());
    }

    @Test
    void 회원가입_실패_테스트() throws Exception {
        ManagerRegisterDto registerDto = new ManagerRegisterDto("홍길동", "email@test.com", "pw123");
        ResponseDto responseDto = ResponseDto.builder()
                .result(Result.FAIL)
                .build();

        when(managerService.register(any(ManagerRegisterDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/managers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerDto)))
                .andExpect(status().isInternalServerError());
    }
}
