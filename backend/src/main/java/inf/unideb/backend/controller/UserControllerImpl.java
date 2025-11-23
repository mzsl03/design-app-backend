package inf.unideb.backend.controller;

import inf.unideb.backend.model.User;
import inf.unideb.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.UUID;

@RestController
public class UserControllerImpl implements UserController {

    private final UserRepository repo;

    public UserControllerImpl(UserRepository ur) {
        this.repo = ur;
    }

    @Override
    public List<User> getAll() {
        return repo.findAll();
    }

    @Override
    public User getOne(UUID id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public User create(User user) {
        return repo.save(user);
    }

    @Override
    public User update(UUID id, User user) {
        User existing = repo.findById(id).orElseThrow();

        existing.setUsername(user.getUsername());
        existing.setEmail(user.getEmail());

        return repo.save(existing);
    }

    @Override
    public void delete(UUID id) {
        repo.deleteById(id);
    }
}
