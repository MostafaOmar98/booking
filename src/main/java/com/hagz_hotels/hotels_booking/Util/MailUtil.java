package com.hagz_hotels.hotels_booking.Util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class MailUtil {
    private static final String user = "badawymahmoud206@gmail.com";
    private static final String password = "yasserwsym77";

    public static void sendMail(String to, String subject, String body) throws GeneralSecurityException, MessagingException {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.port", "465");
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
        MailSSLSocketFactory sf = null;
        sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtps.ssl.trust", "*");
        props.put("mail.smtps.ssl.socketFactory", sf);

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        Message message = new MimeMessage(session);
        message.setSubject(subject);
        message.setText(body);

        Address fromAddress = new InternetAddress(user);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);

        Transport transport = session.getTransport();
        transport.connect(user, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
