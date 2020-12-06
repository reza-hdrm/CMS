package com.rezahdrm.cms.validation;

import com.rezahdrm.cms.validation.annotation.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    private final static String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*()<>-]).{6,}$";

    @Override
    public void initialize(Password constraintAnnotation) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return passwordValidate(password);
    }

    private boolean passwordValidate(String password) {
        return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }
}
