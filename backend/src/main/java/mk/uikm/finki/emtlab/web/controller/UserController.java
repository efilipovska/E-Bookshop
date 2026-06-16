package mk.uikm.finki.emtlab.web.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import mk.uikm.finki.emtlab.model.domain.User;
import mk.uikm.finki.emtlab.model.dto.*;
import mk.uikm.finki.emtlab.model.enums.ViewMode;
import mk.uikm.finki.emtlab.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<RegisterUserResponseDto> findByUsername(@PathVariable String username) {
        return userApplicationService
                .findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/me")
    public ResponseEntity<RegisterUserResponseDto> me(@AuthenticationPrincipal User user) {
        return userApplicationService
                .findByUsername(user.getUsername())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> register(@RequestBody RegisterUserRequestDto registerUserRequestDto) {
        return userApplicationService
                .register(registerUserRequestDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDto> login(@RequestBody LoginUserRequestDto loginUserRequestDto) {
        return userApplicationService
                .login(loginUserRequestDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/book-view")
    public ResponseEntity<String> getBookView(Principal principal) {
        ViewMode viewMode = userApplicationService.getBookViewMode(principal.getName());
        return ResponseEntity.ok(viewMode.name());
    }

    @PutMapping("/book-view")
    public ResponseEntity<Void> updateBookView(@RequestBody BookViewPreferenceDto dto, Principal principal) {
        userApplicationService.updateBookViewMode(principal.getName(), ViewMode.valueOf(dto.viewMode()));
        return ResponseEntity.ok().build();
    }
}

