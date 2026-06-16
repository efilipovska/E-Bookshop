package mk.uikm.finki.emtlab.service.domain.impl;

import java.util.Optional;

import mk.uikm.finki.emtlab.model.domain.User;
import mk.uikm.finki.emtlab.model.enums.ViewMode;
import mk.uikm.finki.emtlab.model.exception.IncorrectPasswordException;
import mk.uikm.finki.emtlab.model.exception.UserNotFoundException;
import mk.uikm.finki.emtlab.model.exception.UsernameAlreadyExistsException;
import mk.uikm.finki.emtlab.repository.UserRepository;
import mk.uikm.finki.emtlab.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User register(User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            throw new UsernameAlreadyExistsException(user.getUsername());
        return userRepository.save(new User(
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getUsername(),
                passwordEncoder.encode(user.getPassword())
        ));
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new IncorrectPasswordException();
        return user;
    }

    @Override
    public ViewMode getBookViewMode(String username) {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow();

        return user.getBookViewMode();
    }

    @Override
    public void updateBookViewMode(String username, ViewMode viewMode) {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow();

        user.setBookViewMode(viewMode);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }


}

