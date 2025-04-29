package com.springboot.mzuplusspringjpa.service.manager;

import com.springboot.mzuplusspringjpa.common.annotation.LogExecutionTime;
import com.springboot.mzuplusspringjpa.dto.ResponseDto;
import com.springboot.mzuplusspringjpa.dto.manager.ManagerDto;
import com.springboot.mzuplusspringjpa.dto.manager.ManagerLoginDto;
import com.springboot.mzuplusspringjpa.dto.manager.ManagerRegisterDto;
import com.springboot.mzuplusspringjpa.entity.Manager;
import com.springboot.mzuplusspringjpa.entity.Role;
import com.springboot.mzuplusspringjpa.enums.Result;
import com.springboot.mzuplusspringjpa.repository.RoleRepository;
import com.springboot.mzuplusspringjpa.repository.manager.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final RoleRepository roleRepository;

    @Override
    @LogExecutionTime
    public ResponseDto login(ManagerLoginDto managerLoginDto) {
        Optional<Manager> optionalManager = managerRepository.findByEmail(managerLoginDto.getEmail());
        if (optionalManager.isPresent()) {
            Manager manager = optionalManager.get();
            if (manager.getPassword().equals(managerLoginDto.getPassword())) {
                return processLogin(manager);
            }
            return ResponseDto.builder()
                    .result(Result.FAIL)
                    .build();
        }
        return ResponseDto.builder()
                .result(Result.NOT_FOUND)
                .build();
    }

    private static ResponseDto processLogin(Manager manager) {
        Map<Integer, String> roles = manager.getRoles().stream()
                .collect(Collectors.toMap(
                        Role::getId,
                        Role::getName
                ));
        ManagerDto managerDto = ManagerDto.builder()
                .id(manager.getId())
                .email(manager.getEmail())
                .name(manager.getName())
                .roles(roles)
                .build();

        return ResponseDto.builder()
                .result(Result.SUCCESS)
                .data(managerDto)
                .build();
    }

    @Override
    @Transactional
    public ResponseDto register(ManagerRegisterDto registerDto) {
        Result result;
        try {
            checkValidateRegister(registerDto);
            Manager manager = Manager.builder()
                    .email(registerDto.getEmail())
                    .name(registerDto.getName())
                    .password(registerDto.getPassword())
                    .build();

            List<Role> role = roleRepository.findRoleByName("manager");
            manager.addRole(role.get(0));
            managerRepository.save(manager);
            result = Result.SUCCESS;

        }catch (DuplicateKeyException e){
            result = Result.DUPLICATE;
        }
        catch (Exception e) {
            result = Result.FAIL;
        }
        return ResponseDto.builder()
                .result(result)
                .build();
    }

    private void checkValidateRegister(ManagerRegisterDto registerDto) {
        if(managerRepository.existsByEmail(registerDto.getEmail()))
            throw new DuplicateKeyException("이미 존재하는 이메일 입니다");
    }
}
