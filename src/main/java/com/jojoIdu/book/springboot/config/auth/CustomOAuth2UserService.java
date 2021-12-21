package com.jojoIdu.book.springboot.config.auth;

import com.jojoIdu.book.springboot.config.auth.dto.OAuthAttributes;
import com.jojoIdu.book.springboot.config.auth.dto.SessionUser;
import com.jojoIdu.book.springboot.domain.user.Users;
import com.jojoIdu.book.springboot.domain.user.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UsersRepository usersRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();

        OAuth2User oauth2User = delegate.loadUser(userRequest);

        //현재 로그인 진행중인 서비스 구분 코드(여러 소셜 로그인 구현 시 필요)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        //OAuth2 로그인 진행시 키가 되는 필드값 = PK
        //구글 경우 지원("sub"), 이외 미지원
        String userNameAttributeName =
                userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        //OAuth2UserService를 통해가져온 OAut2User의 attribute 담을 클래스
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oauth2User.getAttributes());

        Users user = saveOrUpdate(attributes);

        //session에 사용자 정보 저장하기위한 dto
        httpSession.setAttribute("user",new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                                                attributes.getAttributes(),
                                                attributes.getNameAttributeKey());

    }

    //소셜 사용자 정보 업데이트시 반영
    private Users saveOrUpdate(OAuthAttributes attributes){
        Users user = usersRepository.findByEmail(attributes.getEmail())
                                        .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                                        .orElse(attributes.toEntity());

        return usersRepository.save(user);
    }

}
