package com.springboot.mzuplusspringjpa.service.manager;

import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.manager.ManagerLoginDto;

public interface ManagerService {
    ResponseDto login(ManagerLoginDto managerLoginDto);
}
