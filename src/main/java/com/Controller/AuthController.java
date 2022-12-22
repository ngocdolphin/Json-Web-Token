package com.Controller;

import com.Form.UserCreateForm;
import com.Jwt.JwtTokenProvider;
import com.Service.IUserService;
import com.UserDetails.CustomUserDetails;
import com.payload.HelloWorld;
import com.payload.LoginRequest;
import com.payload.LoginResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private IUserService service;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()
                )
        );
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

    @PostMapping("/register")
    public void register(@RequestBody UserCreateForm form) {
        service.create(form);
    }

    @GetMapping("/hello")
    public HelloWorld helloWorld(){
        return new HelloWorld("JWT Hợp lệ mới có thể thấy được message này");
    }

}
