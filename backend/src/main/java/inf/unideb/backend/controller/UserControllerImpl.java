package inf.unideb.backend.controller;

import inf.unideb.backend.model.User;
import inf.unideb.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.UUID;

@RestController
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService service) {
        this.userService = service;
    }

    @Override
    public List<User> getAll() {
        return userService.getAll();
    }

    @Override
    public User getOne(UUID id) {
        return userService.getOne(id);
    }

    @Override
    public User create(User user) {
        return userService.create(user);
    }

    @Override
    public User update(UUID id, User user) {
        return userService.update(id, user);
    }

    @Override
    public void delete(UUID id) {
        userService.delete(id);
    }
}
