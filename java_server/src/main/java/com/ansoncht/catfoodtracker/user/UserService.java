package com.ansoncht.catfoodtracker.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDAO registerUser(UserDTO userDTO) {
        UserDAO userDAO = new UserDAO();
        return userRepository.save(userDAO);
    }

    public UserDAO loginUser(String email) {
        return userRepository.findByEmail(email);
    }
}
