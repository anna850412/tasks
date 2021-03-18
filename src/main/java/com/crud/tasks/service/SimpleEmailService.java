package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {
    private final JavaMailSender javaMailSender;

    public void send(final Mail mail) {
        log.info("Starting email preparation...");

        try {
            SimpleMailMessage mailMessage = createMailMessage(mail);
//            ofNullable(mailMessage.getCc()).ifPresent(cc->mail.getMailTo());
            javaMailSender.send(mailMessage);
            log.info("Email has been sent.");
        } catch (MailException e) {
            log.error("Failed to process email sending: " + e.getMessage(), e);
        }
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        try {
            mailMessage.setTo(mail.getMailTo());
            mailMessage.setSubject(mail.getSubject());
            mailMessage.setText(mail.getMessage());
            mailMessage.setCc(String.valueOf(mail.getToCcs()));

            return
                                  mailMessage;
//                   ofNullable(mailMessage.getCc()).ifPresent(cc->mail.getMailTo());

//                    (SimpleMailMessage) Optional.ofNullable(mailMessage.getCc())
//                            .map(Arrays::asList)
//                            .orElse(Collections.singletonList(mail.getMailTo()));


        } catch (MailException e) {
            log.error("Failed to process Mail Message creation: " + e.getMessage(), e);
        }

        return mailMessage;
    }
}
