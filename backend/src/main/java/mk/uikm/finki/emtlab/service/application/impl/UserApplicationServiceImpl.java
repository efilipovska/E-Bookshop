package mk.uikm.finki.emtlab.service.application.impl;

import java.security.Principal;
import java.util.Optional;
import mk.uikm.finki.emtlab.helpers.JwtHelper;
import mk.uikm.finki.emtlab.model.domain.User;
import mk.uikm.finki.emtlab.model.dto.*;
import mk.uikm.finki.emtlab.model.enums.ViewMode;
import mk.uikm.finki.emtlab.service.application.UserApplicationService;
import mk.uikm.finki.emtlab.service.domain.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<RegisterUserResponseDto> register(RegisterUserRequestDto registerUserRequestDto) {
        User user = userService.register(registerUserRequestDto.toUser());
        RegisterUserResponseDto displayUserDto = RegisterUserResponseDto.from(user);
        return Optional.of(displayUserDto);
    }

    @Override
    public Optional<LoginUserResponseDto> login(LoginUserRequestDto loginUserRequestDto) {
        User user = userService.login(loginUserRequestDto.username(), loginUserRequestDto.password());

        String token = jwtHelper.generateToken(user);

        return Optional.of(new LoginUserResponseDto(token));
    }

    @Override
    public Optional<RegisterUserResponseDto> findByUsername(String username) {
        return userService
                .findByUsername(username)
                .map(RegisterUserResponseDto::from);
    }

    @Override
    public ViewMode getBookViewMode(String username) {
        return userService.getBookViewMode(username);
    }

    @Override
    public void updateBookViewMode(String username, ViewMode viewMode) {
        userService.updateBookViewMode(username, viewMode);
    }
}


