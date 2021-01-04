package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail() throws MessagingException {

        String from = "springbootmailsender@gmail.com";
        String to = "dejsakda@gmail.com";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject("Email from spring boot");
        helper.setFrom(from);
        helper.setTo(to);

        String content = "<b>Dear guru</b>,<br><i>Please look at this nice picture:.</i>"
                + "<br><img src='cid:image001'/><br><b>Best Regards</b>";
        helper.setText(content, true);

        FileSystemResource image = new FileSystemResource(new File("C:\\IntelliJ workspace\\FilesTest\\images\\test.jpg"));
        helper.addInline("image001", image);

        FileSystemResource file = new FileSystemResource(new File("C:\\IntelliJ workspace\\FilesTest\\files\\Java.pdf"));
        helper.addAttachment("Java.pdf", file);

        mailSender.send(message);

    }
}
