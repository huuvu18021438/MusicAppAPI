package io.example.musicAppApi.rest.controller;

import io.example.musicAppApi.model.User;
import io.example.musicAppApi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("{username}")
    public Mono<UserDetails> findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PostMapping
    public Mono<User> create(@RequestBody final User user) {
        return userService.create(user);
    }
}
