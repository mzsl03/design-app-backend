package inf.unideb.backend.mapper;

import inf.unideb.backend.dto.auth.RegisterRequestDTO;
import inf.unideb.backend.dto.UpdateUserDTO;
import inf.unideb.backend.dto.UserDTO;
import inf.unideb.backend.model.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(user.getUsername());
    }

    public static User toEntity(RegisterRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        return user;
    }


    public static void updateEntity(User user, UpdateUserDTO dto) {
        if (dto.username() != null) {
            user.setUsername(dto.username());
        }
        if (dto.email() != null) {
            user.setEmail(dto.email());
        }
    }

}
