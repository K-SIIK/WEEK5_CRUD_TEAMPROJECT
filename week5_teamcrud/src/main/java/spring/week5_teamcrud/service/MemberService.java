package spring.week5_teamcrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.week5_teamcrud.controller.request.LoginRequest;
import spring.week5_teamcrud.controller.request.MemberRequest;
import spring.week5_teamcrud.domain.Member;
import spring.week5_teamcrud.domain.RefreshToken;
import spring.week5_teamcrud.global.GlobalResDto;
import spring.week5_teamcrud.jwt.JwtUtil;
import spring.week5_teamcrud.jwt.TokenDto;
import spring.week5_teamcrud.repository.MemberRepository;
import spring.week5_teamcrud.repository.RefreshTokenRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public GlobalResDto signup(MemberRequest memberRequest) {
        // userName 중복 검사
        if(memberRepository.findByuserName(memberRequest.getUserName()).isPresent()){
            throw new RuntimeException("Overlap Check");
        }

        memberRequest.setEncodePwd(passwordEncoder.encode(memberRequest.getPassword()));
        Member account = new Member(memberRequest);

        memberRepository.save(account);
        return new GlobalResDto("Success signup", HttpStatus.OK.value());
    }

    @Transactional
    public GlobalResDto login(LoginRequest loginReqDto, HttpServletResponse response) {

        Member account = memberRepository.findByuserName(loginReqDto.getUserName()).orElseThrow(
                () -> new RuntimeException("Not found Account")
        );

        if(!passwordEncoder.matches(loginReqDto.getPassword(), account.getPassword())) {
            throw new RuntimeException("Not matches Password");
        }

        TokenDto tokenDto = jwtUtil.createAllToken(loginReqDto.getUserName());

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByuserName(loginReqDto.getUserName());

        if(refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().updateToken(tokenDto.getRefreshToken()));
        }else {
            RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken(), loginReqDto.getUserName());
            refreshTokenRepository.save(newToken);
        }

        setHeader(response, tokenDto);

        return new GlobalResDto("Success Login", HttpStatus.OK.value());

    }

    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }
}
