package com.rezahdrm.cms.validation.annotation;

import com.rezahdrm.cms.validation.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface Email {
    String message() default "Invalid Email";
    Class<?>[] groups()default {};
    Class<? extends Payload>[] payload() default {};
}
