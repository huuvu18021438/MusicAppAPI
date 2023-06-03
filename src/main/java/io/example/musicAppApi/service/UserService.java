package io.example.musicAppApi.service;

import io.example.musicAppApi.model.User;
import io.example.musicAppApi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements ReactiveUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username).map(User::toUsernamePasswordPrincipal);
    }

    public Mono<User> create(User user) {
        return userRepository.save(user);
    }
}
