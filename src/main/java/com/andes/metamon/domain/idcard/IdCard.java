package com.andes.metamon.domain.idcard;

import com.andes.metamon.domain.user.User;
import com.andes.metamon.service.idcard.dto.request.UploadRequestServiceIdCardDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "IDCARDS")
public class IdCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String qrImgUrl;
    @Column
    private String nickname;
    // enum
    @Enumerated(EnumType.STRING)
    private Platform platform;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    private IdCard(String qrImgUrl, String nickname, Platform platform, User userId) {
        this.qrImgUrl = qrImgUrl;
        this.nickname = nickname;
        this.platform = platform;
        this.userId = userId;
    }

    public static IdCard newInstance(String qrImgUrl, String nickname, Platform platform, User userId) {
        return new IdCard(qrImgUrl, nickname, platform, userId);
    }
    public static IdCard newInstance(User userId) {
        // 사용자 qr 이미지는 바뀌어야 할지도?
        return new IdCard("defalutImgUrl", null, Platform.USER, userId);
    }
    public static IdCard newInstance(UploadRequestServiceIdCardDto request, User userId) {
        IdCard newIdCard = new IdCard("defalutImgUrl", "defaultUser", Platform.ZEPETO, userId);
        // QR 이미지가 없을 경우, 기본 이미지로 설정 -> 변경되어야하는 값.
        if (!request.getImgUrl().isBlank()) {
            newIdCard.setUserIdCardQrImgUrl(request.getImgUrl());
        }
        // 유저 닉네임이 없을 경우, 기본 이름으로 설정
        if (!request.getNickname().isBlank()) {
            newIdCard.setUserIdCardNickname(request.getNickname());
        }
        // 신분증의 메타버스 플랫폼 값 설정
        if (!request.getPlatform().isBlank()) {
            newIdCard.setUserIdCardPlatform(request.getPlatform());
        }
        return newIdCard;
    }

    // platform에 들어오는 값에 따라 결정.
    private void setUserIdCardPlatform(String platform) {
        if (platform.equals("ZEPETO")) {
            this.platform = Platform.ZEPETO;
        }
    }

    public void setUserIdCardQrImgUrl(String qrImgUrl) {
        this.qrImgUrl = qrImgUrl;
    }
    public void setUserIdCardNickname(String nickname) {
        this.nickname = nickname;
    }
}
