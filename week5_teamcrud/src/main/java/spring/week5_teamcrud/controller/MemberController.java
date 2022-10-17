package spring.week5_teamcrud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import spring.week5_teamcrud.controller.request.LoginRequest;
import spring.week5_teamcrud.controller.request.MemberRequest;
import spring.week5_teamcrud.global.GlobalResDto;
import spring.week5_teamcrud.jwt.JwtUtil;
import spring.week5_teamcrud.service.MemberService;
import spring.week5_teamcrud.service.UserDetailsImpl;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final JwtUtil jwtUtil;
    private final MemberService memberService;

    @PostMapping("/members/signup")
    public GlobalResDto signup(@RequestBody @Valid MemberRequest memberRequest) {
        return memberService.signup(memberRequest);
    }

    @PostMapping("/members/login")
    public GlobalResDto login(@RequestBody @Valid LoginRequest loginReqDto, HttpServletResponse response) {
        return memberService.login(loginReqDto, response);
    }

    @GetMapping("/issue/token")
    public GlobalResDto issuedToken(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response){
        response.addHeader(JwtUtil.ACCESS_TOKEN, jwtUtil.createToken(userDetails.getMember().getUserName(), "Access"));
        return new GlobalResDto("Success IssuedToken", HttpStatus.OK.value());
    }

}
