package inf.unideb.backend.controller;

import inf.unideb.backend.dto.auth.RegisterRequestDTO;
import inf.unideb.backend.dto.UpdateUserDTO;
import inf.unideb.backend.dto.UserDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.UUID;

public interface UserController {

    @GetMapping("/api/users")
    List<UserDTO> getAll();

    @GetMapping("/api/users/{id}")
    UserDTO getOne(@PathVariable UUID id);

    @PostMapping("/api/users")
    UserDTO create(@RequestBody RegisterRequestDTO user);

    @PutMapping("/api/users/{id}")
    UserDTO update(@PathVariable UUID id, @RequestBody UpdateUserDTO user);

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/users/{id}")
    void delete(@PathVariable UUID id);

}
