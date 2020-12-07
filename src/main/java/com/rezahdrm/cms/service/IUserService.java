package com.rezahdrm.cms.service;

import com.rezahdrm.cms.exception.UserAlreadyExistException;
import com.rezahdrm.cms.model.User;
import com.rezahdrm.cms.model.dto.UserDTO;

public interface IUserService {
    User registerNewUserAccount(UserDTO userDTO) throws UserAlreadyExistException;
}
