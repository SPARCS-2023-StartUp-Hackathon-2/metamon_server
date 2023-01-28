package com.andes.metamon.service.idcard;

import com.andes.metamon.domain.idcard.IdCard;
import com.andes.metamon.domain.idcard.IdCardRepository;
import com.andes.metamon.domain.user.User;
import com.andes.metamon.domain.user.UserRepository;
import com.andes.metamon.exception.badRequest.NotFoundUser;
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
    // qr 이미지 업로드
    public void saveIdCard(Long userId, UploadRequestServiceIdCardDto request) {
        validdateUserIdExists(userId);
        User foundUser = findUserById(userId);
        IdCard createdIdCard = IdCard.newInstance(request, foundUser);
        idCardRepository.save(createdIdCard);
    }
    public void saveUserIdCard(User userId) {
        IdCard createdIdCard = IdCard.newInstance(userId);
        idCardRepository.save(createdIdCard);
    }
    public List<ResponseIdCardDto> findAllIdCard(Long userId) {
        validdateUserIdExists(userId);
        List<IdCard> idCards = idCardRepository.findAllByUserId(userId).orElseThrow(() -> new IllegalArgumentException("쿼리가 잘못되었다."));
        return idCards.stream()
                .map(idCard -> ResponseIdCardDto.of(idCard.getId(), idCard.getNickname()
                        , idCard.getPlatform().toString(),idCard.getQrImgUrl()
                        ,idCard.getUserId().getId(),idCard.getUserId().getEmail(), idCard.getUserId().getBirth()))
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
}
