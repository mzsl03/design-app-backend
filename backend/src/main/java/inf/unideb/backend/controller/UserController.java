package inf.unideb.backend.controller;

import inf.unideb.backend.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface UserController {

    @GetMapping("/api/users")
    List<User> getAll();

    @GetMapping("/api/users/{id}")
    User getOne(@PathVariable UUID id);

    @PostMapping("/api/users")
    User create(@RequestBody User user);

    @PutMapping("/api/users/{id}")
    User update(@PathVariable UUID id, @RequestBody User user);

    @DeleteMapping("/api/users/{id}")
    void delete(@PathVariable UUID id);
}
