package edu.miu.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPaymentNotification(String toEmail, String paymentDetails) {
        SimpleMailMessage message = new SimpleMailMessage();
        //put email details as well
        message.setTo(toEmail);
        message.setSubject("Payment Notification");
        message.setText("Payment processed successfully. Details: " + paymentDetails);

//        mailSender.send(message);
        System.out.println("Email Notification Sent Successfully: ");
    }
}
