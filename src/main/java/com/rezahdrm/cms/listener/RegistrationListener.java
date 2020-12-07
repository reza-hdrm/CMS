package com.rezahdrm.cms.listener;

import com.rezahdrm.cms.event.OnRegistrationCompleteEvent;
import com.rezahdrm.cms.model.User;
import com.rezahdrm.cms.service.IUserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.UUID;

public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    private final IUserService userService;
    private final MessageSource messageSource;
    private final JavaMailSender javaMailSender;

    public RegistrationListener(IUserService userService, MessageSource messageSource, JavaMailSender javaMailSender) {
        this.userService = userService;
        this.messageSource = messageSource;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {

    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        //userService.createVerificationToken(user,token);
        String recipientAddress = user.getEmail();
        String subject = "تأیید ثبت نام";
        String confirmationUrl=event.getAppUrl()+"/regitrationConfirm.html?token"+token;
        String message=messageSource.getMessage("message.regSucc",null,event.getLocale());

        SimpleMailMessage email=new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
        javaMailSender.send(email);
    }
}
