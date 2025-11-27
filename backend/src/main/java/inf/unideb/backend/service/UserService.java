package inf.unideb.backend.service;

import inf.unideb.backend.dto.auth.RegisterRequestDTO;
import inf.unideb.backend.dto.UpdateUserDTO;
import inf.unideb.backend.dto.UserDTO;
import inf.unideb.backend.mapper.UserMapper;
import inf.unideb.backend.model.User;
import inf.unideb.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    public UserDTO getOne(UUID id) {
        User user = userRepository.findById(id).orElseThrow();
        return UserMapper.toDTO(user);
    }

    public UserDTO create(RegisterRequestDTO user) {
        User entity = UserMapper.toEntity(user);
        User newUser = userRepository.save(entity);
        return UserMapper.toDTO(newUser);
    }

    public UserDTO update(UUID id, UpdateUserDTO user) {
        User existing = userRepository.findById(id).orElseThrow();
        UserMapper.updateEntity(existing, user);
        User saved =  userRepository.save(existing);
        return UserMapper.toDTO(saved);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

}
