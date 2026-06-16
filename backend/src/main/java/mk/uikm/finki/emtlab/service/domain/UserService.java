package mk.uikm.finki.emtlab.service.domain;

import java.util.Optional;
import mk.uikm.finki.emtlab.model.domain.User;
import mk.uikm.finki.emtlab.model.enums.ViewMode;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Optional<User> findByUsername(String username);

    User register(User user);

    User login(String username, String password);

    ViewMode getBookViewMode(String username);

    void updateBookViewMode(String username, ViewMode viewMode);
}


