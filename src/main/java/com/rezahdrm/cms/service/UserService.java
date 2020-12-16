package com.rezahdrm.cms.service;

import com.rezahdrm.cms.exception.UserAlreadyExistException;
import com.rezahdrm.cms.model.User;
import com.rezahdrm.cms.model.dto.UserDTO;
import com.rezahdrm.cms.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class UserService implements IUserService {
    UserRepository userRepository;
    PasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User registerNewUserAccount(UserDTO userDTO) throws Exception {

        if (emailExist(userDTO.getEmail())) {
            throw new UserAlreadyExistException(
                    "There is an account with that email address: "
                            + userDTO.getEmail());
        }
        User user = new User();
        user.setName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setRememberToken(UUID.randomUUID().toString());
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new Exception("confirm password isn't mache");//TODO Exception Monaseb
        }
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public List<User> findAll() {
        return userRepository.findAllByDeletedAtIsNull();
    }

    public void registrationConfirm(String email, String token) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE,24*60);
        new Date(calendar.getTime().getTime());
        User user = userRepository.findByEmail(email);
        if (user.getRememberToken().equals(token)) {
            user.setEmailVerifiedAt(new Date());
            user.setStatus(User.Status.ACTIVATE);
            userRepository.save(user);
        }
    }
}
