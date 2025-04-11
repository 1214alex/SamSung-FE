package com.samsung.dieat.member.command.domain.aggregate.vo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseFindUserVO {
    private String userName;
    private String userNickname;
    private String userEmail;
    private String userGender;
    private String userBirthday;
    private String role;

}
