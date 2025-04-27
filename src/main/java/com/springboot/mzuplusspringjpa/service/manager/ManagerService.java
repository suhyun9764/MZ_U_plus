package com.springboot.mzuplusspringjpa.service.manager;

import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.manager.ManagerLoginDto;
import com.springboot.mzuplusspringjpa.dto.manager.ManagerRegisterDto;

public interface ManagerService {
    ResponseDto login(ManagerLoginDto managerLoginDto);

    ResponseDto register(ManagerRegisterDto registerDto);
}
