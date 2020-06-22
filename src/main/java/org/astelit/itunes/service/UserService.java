package org.astelit.itunes.service;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.user.CreateUserRequest;
import org.astelit.itunes.dto.user.UpdateUserRequest;
import org.astelit.itunes.dto.user.UserResponse;
import org.astelit.itunes.entity.User;
import org.astelit.itunes.exception.BadRequestException;
import org.astelit.itunes.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static org.astelit.itunes.utils.Exceptions.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public UserResponse create(CreateUserRequest request) {
        if (repository.existsByLogin(request.getLogin()))
            throw new BadRequestException("Login " + request.getLogin() + " already taken");

        User user = new User();
        user.setLogin(request.getLogin());
        user.setName(request.getName());

        repository.save(user);
        return new UserResponse(user);
    }

    public UserResponse update(UpdateUserRequest request) {
        if (repository.existsByLoginAndIdNot(request.getLogin(), request.getId()))
            throw new BadRequestException("Login " + request.getLogin() + " already taken");

        User user = repository.findById(request.getId()).orElseThrow(USER_NOT_FOUND);
        user.setLogin(request.getLogin());
        user.setName(request.getName());

        repository.save(user);
        return new UserResponse(user);
    }

    public UserResponse view(long id) {
        User user = repository.findById(id).orElseThrow(USER_NOT_FOUND);
        return new UserResponse(user);
    }

    public Page<UserResponse> search(SearchRequest request) {
        return repository.findByLoginIsLikeOrderByLoginAsc(request.getQuery(), request.pageable())
                .map(UserResponse::new);
    }
}
