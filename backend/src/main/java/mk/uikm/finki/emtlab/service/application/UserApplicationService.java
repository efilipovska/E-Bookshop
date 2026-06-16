package mk.uikm.finki.emtlab.service.application;

import mk.uikm.finki.emtlab.model.dto.*;
import mk.uikm.finki.emtlab.model.enums.ViewMode;

import java.security.Principal;
import java.util.Optional;


public interface UserApplicationService {
    Optional<RegisterUserResponseDto> register(RegisterUserRequestDto registerUserRequestDto);

    Optional<LoginUserResponseDto> login(LoginUserRequestDto loginUserRequestDto);

    Optional<RegisterUserResponseDto> findByUsername(String username);

    ViewMode getBookViewMode(String username);

    void updateBookViewMode(String username, ViewMode viewMode);
}
