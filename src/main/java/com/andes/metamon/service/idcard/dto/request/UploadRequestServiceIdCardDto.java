package com.andes.metamon.service.idcard.dto.request;

import lombok.*;

@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UploadRequestServiceIdCardDto {
    private String platform;
    private String nickname;
    private String imgUrl;

    public static UploadRequestServiceIdCardDto of(String platform, String nickname, String imgUrl) {
        return new UploadRequestServiceIdCardDto(platform, nickname, imgUrl);
    }
}
