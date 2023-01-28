package com.andes.metamon.service.user.dto.response;

import lombok.*;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfoDto {
    private Long userId;
    private String userName;
    private String email;
    private String userBirth;

    public static UserInfoDto of(Long userId, String userName, String email, String userBirth) {
        return new UserInfoDto(userId, userName, email, userBirth);
    }
}