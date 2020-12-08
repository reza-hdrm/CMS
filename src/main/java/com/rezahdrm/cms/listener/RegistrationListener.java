package com.rezahdrm.cms.listener;

import com.rezahdrm.cms.event.OnRegistrationCompleteEvent;
import com.rezahdrm.cms.model.User;
import com.rezahdrm.cms.service.Notifiable;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    private final MessageSource messageSource;
    private final Notifiable emailService;

    public RegistrationListener(MessageSource messageSource, Notifiable emailService) {
        this.messageSource = messageSource;
        this.emailService = emailService;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String to = user.getEmail();
        String subject = "تأیید ثبت نام";
        String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token" + user.getRememberToken();
        String text = "<a href='http://127.0.0.1:8080/register/registrationConfirm?email="+to+"&token="+user.getRememberToken()+"'></a>";
        emailService.send(to, subject, text);
    }
}
