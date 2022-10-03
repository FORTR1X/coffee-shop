package ru.shop.coffee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

  @Autowired
  private JavaMailSender emailSender;

  @Value("${spring.mail.username}")
  private String username;

  public MailSenderService() {
  }

  public void sendSimpleMessage(String to, String subject, String text) {
    SimpleMailMessage message = new SimpleMailMessage();

    message.setFrom(username);
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);

    emailSender.send(message);
  }
}
