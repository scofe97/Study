package com.example.security1.config.oauth;


import com.example.security1.config.auth.PrincipalDetails;
import com.example.security1.model.User;
import com.example.security1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    // 구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
    // 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 구글로 부터 받은 userRequest 데이터에 대한 후처리가 되는 함수
        // userRequest = org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest@485cfb01
        System.out.println("userRequest = " + userRequest);
        System.out.println("userRequest.getClientRegistration = " + userRequest.getClientRegistration());
        System.out.println("userRequest.getAccessToken = " + userRequest.getAccessToken());
        System.out.println("loadUser = " + super.loadUser(userRequest).getAttributes());

//                userRequest = org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest@13eded25
//                userRequest.getClientRegistration = ClientRegistration{registrationId='google',
//                    clientId='30818697785-7oraok4rh34lkjuig9deo0tcttcsi6vv.apps.googleusercontent.com',
//                    clientSecret='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',
//                    clientAuthenticationMethod=org.springframework.security.oauth2.core.ClientAuthenticationMethod@4fcef9d3,
//                    uthorizationGrantType=org.springframework.security.oauth2.core.AuthorizationGrantType@5da5e9f3,
//                    redirectUri='{baseUrl}/{action}/oauth2/code/{registrationId}',
//                    scopes=[email, profile],
//                    providerDetails=org.springframework.security.oauth2.client.registration.ClientRegistration$ProviderDetails@3d18c3e0,
//                    clientName='Google'}
//                userRequest.getAccessToken = org.springframework.security.oauth2.core.OAuth2AccessToken@378b1437
//                loadUser = {sub=114477815481377286595,
//                    name=심보현[부울경_2반_E205]팀원,
//                    given_name=심보현[부울경_2반_E205]팀원,
//                    picture=https://lh3.googleusercontent.com/a/AEdFTp7sA6LZmEpbsfNlnMc4Y-2tIZz19V-n4oEog3aQ=s96-c,
//                    email=tscofet@gmail.com,
//                    email_verified=true,
//                    locale=ko}

        // username = google_sub
        // password = 암호화
        // email = email
        // role = ROLE_USER
        // provider = google
        // providerId = sub


        OAuth2User oAuth2User = super.loadUser(userRequest);
        // 구글 로긍니 버튼 클릭 -> 구글 로그인 창 -> 로그인을 완료 -> code를 리턴(Oauth-CLient라이브러리) -> AccessToken 요청
        // userRequest 정보 -> 회원프로필 받아야함(loadUser 함수) -> 구글로부터 회원 프로필 받아준다.
        System.out.println("oAuth2User.getAttributes() = " + oAuth2User.getAttributes());

        // 회원가입을 강제로 진행할 예정
        OAuth2UserInfo oAuth2UserInfo = null;
        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            System.out.println("구글 로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        }  else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
        } else{
            System.out.println("구글하고 페이스북, 네이버 로그인이 없다고요? 나가");
        }

        String provider = oAuth2UserInfo.getProvider();// google
        String providerId = oAuth2UserInfo.getProviderId();
        String username = oAuth2UserInfo + "_" + providerId;
        String password = "겟인데어";
        String email = oAuth2User.getAttribute("email");
        String role = "ROLE_USER";

        User userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            // 유저가 없으므로 강제로 회원가입 시킴
            userEntity = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            System.out.println("userEntity = " + userEntity);
            userRepository.save(userEntity);
        }else{
            System.out.println("이미 로그인한적이 있습니다!");
        }

        // 정보를 토대로 강제로 회원가입 진행하기
        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}
