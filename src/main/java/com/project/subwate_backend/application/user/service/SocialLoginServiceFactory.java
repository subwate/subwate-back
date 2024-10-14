package com.project.subwate_backend.application.user.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SocialLoginServiceFactory {

    Map<String, SocialLoginService> socialLoginServices;

    public SocialLoginServiceFactory(List<SocialLoginService> socialLoginServices) {
        this.socialLoginServices = socialLoginServices.stream()
                .collect(Collectors.toMap(service -> service.getClass().getSimpleName(), service -> service));
    }

    public SocialLoginService getSocialLoginService(String serviceName) {
        if (serviceName.equalsIgnoreCase("kakao")) {
            return socialLoginServices.get("KakaoLoginService");
        }

        throw new IllegalArgumentException("Unknown serviceName: " + serviceName);
    }

}
