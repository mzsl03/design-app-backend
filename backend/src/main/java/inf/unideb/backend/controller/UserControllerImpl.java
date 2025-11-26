package inf.unideb.backend.controller;

import inf.unideb.backend.dto.CreateUserDTO;
import inf.unideb.backend.dto.UpdateUserDTO;
import inf.unideb.backend.dto.UserDTO;
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
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @Override
    public UserDTO getOne(UUID id) {
        return userService.getOne(id);
    }

    @Override
    public UserDTO create(CreateUserDTO user) {
        return userService.create(user);
    }

    @Override
    public UserDTO update(UUID id, UpdateUserDTO user) {
        return userService.update(id, user);
    }

    @Override
    public void delete(UUID id) {
        userService.delete(id);
    }
}
