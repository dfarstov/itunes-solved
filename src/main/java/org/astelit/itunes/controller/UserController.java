package org.astelit.itunes.controller;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.user.CreateUserRequest;
import org.astelit.itunes.dto.user.UpdateUserRequest;
import org.astelit.itunes.dto.user.UserResponse;
import org.astelit.itunes.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserResponse create(@Valid @RequestBody CreateUserRequest request) {
        return userService.create(request);
    }

    @PatchMapping
    public UserResponse update(@Valid @RequestBody UpdateUserRequest object) {
        return userService.update(object);
    }

    @GetMapping("{id}")
    public UserResponse view(@PathVariable long id) {
        return userService.view(id);
    }

    @GetMapping
    public Page<UserResponse> search(SearchRequest request) {
        return userService.search(request);
    }
}
