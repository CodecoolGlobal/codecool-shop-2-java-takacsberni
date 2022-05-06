package com.codecool.shop.send_email;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.service.OrderService;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Properties;

public class SendEmail {
    private static String userEmail;

    public SendEmail(String userEmail) {
        super();
        this.userEmail = userEmail;
    }

    public static void sendMail(String emailData)
    {
        String email = "petshopgirlstest@gmail.com"; // sender email
        String password = "cicanyuszi"; // sender password

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication(){

                return new PasswordAuthentication(email, password);
            }
        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setText("This is a message from the test");
            message.setText(emailData);

            Transport.send(message);

        }catch(Exception e){
            System.out.println("SendEmail File Error" + e);
        }
    }
}
