package com.ansoncht.catfoodtracker.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public UserDTO.SignUpResponse registerUser(UserDTO.SignUpRequest userDTO) {
        UserDAO usernameUser = userRepository.findByUsername(userDTO.getUsername());
        UserDAO emailUser = userRepository.findByEmail(userDTO.getEmail());
        if (usernameUser != null || emailUser != null) {
            return null;
        }

        UserDAO userDAO = createUserDAO(userDTO);

        userDAO.setPassword(encryptPassword(userDTO.getPassword()));

        UserDAO createdUser = this.userRepository.save(userDAO);

        return createUserDTOSignUpResponse(createdUser);
    }

    public UserDAO loginUser(String email) {
        return userRepository.findByEmail(email);
    }

    public String encryptPassword(String password) {
        return this.bCryptPasswordEncoder.encode(password);
    }

    // helper function for serializing & deserializing UserDTO and UserDAO objects
    private UserDAO createUserDAO(UserDTO.SignUpRequest userDTO) {
        return new UserDAO(
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getPassword());
    }

    private UserDTO.SignUpResponse createUserDTOSignUpResponse(UserDAO userDAO) {
        return new UserDTO.SignUpResponse(
                userDAO.getId(), userDAO.getEmail(), userDAO.getUsername());
    }
}
