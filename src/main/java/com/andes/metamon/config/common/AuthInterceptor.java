package com.andes.metamon.config.common;

import com.andes.metamon.config.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {
    private final JwtProvider jwtProvider;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod) || getLoginAnnotation(handler) == null) {
            return true;
        }
        if (hasAuthorization(request)) {
            validateAuthorization(request);
            return true;
        }

        validateTokenRequired(handler);
        return true;
    }

    private void validateTokenRequired(Object handler) {
        Login auth = getLoginAnnotation(handler);
        if (auth != null && auth.required()) {
            throw new IllegalArgumentException("토큰이 필요하지 않은 핸들러입니다.");
        }
    }

    private void validateAuthorization(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!jwtProvider.isValidToken(authorizationHeader)) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }
    }

    private boolean hasAuthorization(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION) != null;
    }

    private Login getLoginAnnotation(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        return handlerMethod.getMethodAnnotation(Login.class);
    }
}
