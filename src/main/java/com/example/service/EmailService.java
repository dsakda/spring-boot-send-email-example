package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendEmail() throws MessagingException {

        String sentMailTo = "dejsakda@gmail.com";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        //Test attachment
//        FileSystemResource file = new FileSystemResource(new File("C:\\IntelliJ workspace\\FilesTest\\files\\Java.pdf"));
//        helper.addAttachment("Java.pdf", file);

        //Variable in html page
        final Context ctx = new Context();
        ctx.setVariable("name", "Developer!");
        ctx.setVariable("location", "Thailand");
        ctx.setVariable("sign", "Java Developer");
        ctx.setVariable("image001", "image001");

        String content = templateEngine.process("email/emailTemplate.html", ctx);

        helper.setText(content, true);
        helper.setSubject("Email from spring boot");
        helper.setFrom("springbootmailsender@gmail.com");
        helper.setTo(sentMailTo);

        helper.addInline("image001", new ClassPathResource("static/images/email/gallery2.png"), "image/png");

        mailSender.send(message);

    }
}
