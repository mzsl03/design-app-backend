package inf.unideb.backend.service;

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
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getOne(UUID id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(UUID id, User user) {
        User existing = userRepository.findById(id).orElseThrow();

        existing.setUsername(user.getUsername());
        existing.setEmail(user.getEmail());

        return userRepository.save(existing);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
    
}
