package com.andes.metamon.controller.user;

import com.andes.metamon.config.common.qrcode.QrCodeGenerator;
import com.andes.metamon.controller.user.dto.request.LoginRequestUserDto;
import com.andes.metamon.controller.user.dto.request.SignUpUserDto;
import com.andes.metamon.exception.BaseResponse;
import com.andes.metamon.service.user.UserService;
import com.andes.metamon.service.user.dto.response.LoginUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class UserController {

    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<BaseResponse> signup(@RequestBody @Valid final SignUpUserDto request) {
        LoginUserDto loginUserDto = userService.registerUser(request.toServiecDto());
        return ResponseEntity.ok().body(new BaseResponse<LoginUserDto>(loginUserDto));
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody @Valid final LoginRequestUserDto request) {
        LoginUserDto loginUserDto = userService.login(request.toServiecDto());
        return ResponseEntity.ok().body(new BaseResponse<LoginUserDto>(loginUserDto));
    }

//    @GetMapping("/qr")
//    public ResponseEntity<String> generateQrCode(String name) {
//        String qrCodeImage = "";
//        try {
//            qrCodeImage = QrCodeGenerator.getQRCodeImage(name, 200, 200);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("e = " + e);
//        }
//        return ResponseEntity.ok().body(qrCodeImage);
//    }




}
