package com.andes.metamon.service.user.dto.response;

import lombok.*;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginUserDto {
    private Long id;
    private String name;
    private String email;
    private String token;

    public static LoginUserDto of(Long id,String name, String email, String token) {
        return new LoginUserDto(id, name, email, token);
    }
}