package com.rezahdrm.cms.service;

public interface Notifiable {
    void send(String to, String subject, String text);
}
