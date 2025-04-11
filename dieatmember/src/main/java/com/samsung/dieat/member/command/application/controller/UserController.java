package com.samsung.dieat.member.command.application.controller;


import com.samsung.dieat.member.command.application.dto.UserDTO;
import com.samsung.dieat.member.command.application.service.EmailVerificationService;
import com.samsung.dieat.member.command.application.service.UserService;
import com.samsung.dieat.member.command.domain.aggregate.vo.*;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {

    private Environment env;
    private UserService userService;
    private ModelMapper modelMapper;
    private EmailVerificationService emailVerificationService;

    @Value("${admin.secret}")
    private String adminSecretCode;

    @Autowired
    public UserController(Environment env, UserService userService, ModelMapper modelMapper, EmailVerificationService emailVerificationService) {
        this.env = env;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.emailVerificationService = emailVerificationService;
    }


    @GetMapping("/health")
    public String status(){
        return "살 빼!!!!" + env.getProperty("local.serve`r.port");
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseRegistUserVO> registUser(@RequestBody RequestRegistUserVO newUser) {
        log.info("adminSecretCode: '{}'", adminSecretCode);
        log.info("inviteCode: '{}'", newUser.getInviteCode());

        // 1️⃣ 이메일 인증 여부 확인
        if (!emailVerificationService.isEmailVerified(newUser.getUserEmail())) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(null); // 또는 메시지 포함된 VO 반환 가능
        }

        // 2️⃣ 유저 생성
        UserDTO userDTO = modelMapper.map(newUser, UserDTO.class);

        // 3️⃣ 역할 설정
        if (newUser.getInviteCode() != null && newUser.getInviteCode().trim().equals(adminSecretCode.trim())) {
            userDTO.setRole("ADMIN");
        } else {
            userDTO.setRole("USER");
        }

        // 4️⃣ 회원가입 서비스 호출
        userService.registUser(userDTO);

        // 5️⃣ 응답 반환
        ResponseRegistUserVO successRegistUser = modelMapper.map(userDTO, ResponseRegistUserVO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(successRegistUser);
    }


    @GetMapping("/users/{memNo}")
    public ResponseEntity<ResponseFindUserVO> getUsers(@PathVariable String memNo) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserId = auth.getName();

        UserDTO userDTO = userService.getUserById(memNo);

        if (!loggedInUserId.equals(userDTO.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }



        ResponseFindUserVO findUserVO = modelMapper.map(userDTO, ResponseFindUserVO.class);

        return ResponseEntity.status(HttpStatus.OK)
                .body(findUserVO);
    }

    @GetMapping("/users/me")
    public ResponseEntity<ResponseFindUserVO> getMyInfo() {
        // 현재 인증된 사용자 정보 추출
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        // userId로 유저 조회
        UserDTO userDTO = userService.getUserByUserId(userId);

        // VO로 변환 후 응답
        ResponseFindUserVO response = modelMapper.map(userDTO, ResponseFindUserVO.class);
        return ResponseEntity.ok(response);
    }





}
