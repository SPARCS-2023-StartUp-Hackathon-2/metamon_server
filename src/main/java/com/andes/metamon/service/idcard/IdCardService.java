package com.andes.metamon.service.idcard;

import com.andes.metamon.config.common.aws.AwsS3Uploader;
import com.andes.metamon.config.common.mail.RegisterMail;
import com.andes.metamon.domain.idcard.IdCard;
import com.andes.metamon.domain.idcard.IdCardRepository;
import com.andes.metamon.domain.user.User;
import com.andes.metamon.domain.user.UserRepository;
import com.andes.metamon.exception.badRequest.NotFoundUser;
import com.andes.metamon.exception.internelServer.MailPostErrorException;
import com.andes.metamon.service.idcard.dto.request.UploadRequestServiceIdCardDto;
import com.andes.metamon.service.idcard.dto.response.ResponseIdCardDto;
import com.andes.metamon.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IdCardService {
    private final UserRepository userRepository;
    private final IdCardRepository idCardRepository;
    private final AwsS3Uploader awsS3Uploader;
    private final RegisterMail registerMail;
    // qr 이미지 업로드
    public void saveIdCard(Long userId, UploadRequestServiceIdCardDto request) {
        validdateUserIdExists(userId);
        User foundUser = findUserById(userId);

        if (!request.getImgUrl().contains("https://sparcs-2023-startup-hackathon-m-1.s3.ap-northeast-2.amazonaws.com/example")) {
            // qr code 생성 후 s3 전송
            String savedQrImgUrl = generateQrCodeAndSaveS3(foundUser.getId().toString(), request.getNickname());
            request.setImgUrl(savedQrImgUrl);
        }
        // 신분증 이미지 전송
        try {
            registerMail.sendQRImgURl(request.getImgUrl());
        } catch (Exception e) {
            e.printStackTrace();
            throw new MailPostErrorException();
        }


        IdCard createdIdCard = IdCard.newInstance(request, foundUser);

        idCardRepository.save(createdIdCard);
    }

    public void saveUserIdCard(User userId) {
        // qr code 생성 후 s3 전송
        String savedQrImgUrl = generateQrCodeAndSaveS3(userId.getId().toString(), userId.getName());
        IdCard createdIdCard = IdCard.newInstance(userId, savedQrImgUrl);
        idCardRepository.save(createdIdCard);
    }
    public List<ResponseIdCardDto> findAllIdCard(Long userId) {
        validdateUserIdExists(userId);
        List<IdCard> idCards = idCardRepository.findAllByUserId(userId).orElseThrow(() -> new IllegalArgumentException("쿼리가 잘못되었다."));
        return idCards.stream()
                .map(idCard -> ResponseIdCardDto.of(idCard.getId(), idCard.getNickname()
                        , idCard.getPlatform().toString(),idCard.getQrImgUrl(),
                        idCard.getCreated_at(), idCard.getUpdated_at()
                        ,idCard.getUserId().getId(),idCard.getUserId().getName(),
                        idCard.getUserId().getEmail(), idCard.getUserId().getBirth()))
                .collect(Collectors.toList());

    }
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundUser());
    }
    public void validdateUserIdExists(Long userId) {
        if (!userRepository.existsUserById(userId)) {
            throw new NotFoundUser();
        }
    }
    public String generateQrCodeAndSaveS3(String userId, String name) {
        String fileName = awsS3Uploader.makeFileName(userId);
        String text = awsS3Uploader.makeText(name);
        return awsS3Uploader.uploadFileV1(text, fileName);
    }
}
