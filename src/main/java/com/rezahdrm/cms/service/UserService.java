package com.rezahdrm.cms.service;

import com.rezahdrm.cms.exception.UserAlreadyExistException;
import com.rezahdrm.cms.model.User;
import com.rezahdrm.cms.model.dto.UserDTO;
import com.rezahdrm.cms.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements IUserService {
    UserRepository userRepository;
    PasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User registerNewUserAccount(UserDTO userDTO) {

        if (emailExist(userDTO.getEmail())) {
            throw new UserAlreadyExistException(
                    "There is an account with that email address: "
                            + userDTO.getEmail());
        }
        User user = new User();
        user.setName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAllByDeletedAtIsNull();
    }
}
