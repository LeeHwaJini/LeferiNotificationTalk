package com.leferi.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leferi.notification.dao.AdminRepository;
import com.leferi.notification.dto.AdminResponseDto;
import com.leferi.notification.exception.NotFoundException;
import com.leferi.notification.model.Admin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Service
public class AdminService {

//	@Autowired
//	private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public AdminResponseDto getAdminInfo(String id) throws NotFoundException {
    	Admin admin = new Admin();
    	admin.setAdmId(id);
//    	AdminResponseDto result = adminRepository.findOneByAdmId(id)
//        		//findOneByMemId(id)
//                .map(AdminResponseDto::of)
//                .orElseThrow(NotFoundException::new);
//        return result;
        return null;
    }
    
    @Transactional(readOnly = true)
    public boolean checkPassword(String id, String targetPwd) throws Exception {
//    	AdminResponseDto findOne = adminRepository.findOneByAdmId(id)
//        		//findOneByMemId(id)
//                .map(AdminResponseDto::of)
//                .orElse(null);
//        if(findOne == null) {
//            return false;
//        }
//
//        return (passwordEncoder.matches(targetPwd, findOne.getAdmPw()));
        return true;
    }
    
}
