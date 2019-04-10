package edu.nju.ise.auction.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

@Service
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Value("${spring.mail.username}")
    private String from;
    @Value("${mail.to}")
    private String to;
    @Autowired
    Configuration configuration;

    @Autowired
    private JavaMailSender sender;

    public void sendSimpleMail(String subject, String context) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setText(context);
        message.setFrom(from);
        message.setTo(to);

        sender.send(message);
    }

    public void sendTemplateMail(String subject, Map<String, Object> params) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = sender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);

        Template template = configuration.getTemplate("mail.ftl");

        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
        helper.setText(text, true);

        sender.send(mimeMessage);
}

}
