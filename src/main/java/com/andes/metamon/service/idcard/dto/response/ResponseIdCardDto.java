package com.andes.metamon.service.idcard.dto.response;

import com.andes.metamon.domain.idcard.Platform;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseIdCardDto {
    private Long authCardId;
    private String nickname;
    private String platform;
    private String qrImgUrl;
    private Long userId;
    private String userName;
    private String userEmail;
    private String userBirth;

    public static ResponseIdCardDto of(Long id, String nickname, String platform, String qrImgUrl, Long userId, String userName, String userEmail, String userBirth) {
        return new ResponseIdCardDto(id, nickname, platform, qrImgUrl, userId, userName, userEmail, userBirth);
    }
}
