package com.samsung.dieat.member.command.application.service;


import com.samsung.dieat.member.command.application.dto.UserDTO;
import com.samsung.dieat.member.command.domain.aggregate.vo.RequestWeightVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void registUser(UserDTO userDTO);

    UserDTO getUserById(String memNo);

    UserDTO getUserByUserId(String userId);
}
