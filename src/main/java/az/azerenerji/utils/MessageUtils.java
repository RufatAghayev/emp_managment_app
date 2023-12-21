package az.azerenerji.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageUtils {

    private String ow
    private final JavaMailSender javaMailSender;
    public void sendEmail(String toEmail,String subject,String body){
        SimpleMailMessage msg=new SimpleMailMessage();
        msg.setFrom();
        msg.setTo(toEmail);
        msg.setText(body);
        msg.setSubject(subject);

        javaMailSender.send(msg);
      }

}
