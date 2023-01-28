package com.andes.metamon.service.user.dto.response;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginUserDto {
    private Long id;
    private String userName;
    private String email;
    private String token;

    public static LoginUserDto of(Long id,String userName, String email, String token) {
        return new LoginUserDto(id, userName, email, token);
    }

    public static LoginUserDto of(Long id,String userName, String email) {
        return new LoginUserDto(id, userName, email, "");
    }
}