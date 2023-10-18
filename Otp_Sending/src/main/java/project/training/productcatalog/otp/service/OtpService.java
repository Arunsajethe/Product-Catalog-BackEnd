package project.training.productcatalog.otp.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import org.springframework.stereotype.Service;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class OtpService {
	
	public String sendMail(String mail) throws UnsupportedEncodingException, MessagingException {

		System.out.println("Outlook Email Start");

        String smtpHostServer = "smtp.office365.com";
        final String emailID = "productcatalog@outlook.com"; 
        final String password = "myproject123"; 
        
        String toEmail = mail;
        String subject = "Subject: One Time Password (OTP) for Reset Password";
        
        String otp = generateOtp();

        String messageBody =  "Hello Admin "+mail+",\r\n"
        						+ "\r\n"+
        						"We are sending you this email because you requested a password reset"
        						+"\r\n"
        						+"Your reset password password is "+otp
        						+"\r\n"
        						+"If you didn't want to reset password, you can ignore this email. Your password will not be changed."; 

        Properties props = new Properties();
        
        props.put("mail.smtp.host", smtpHostServer); 
        props.put("mail.smtp.port", "587"); 
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.smtp.starttls.enable", "true");


        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailID, password);
            }
        });

        //sendToEmail(session, emailID, subject, messageBody,toEmail);
        
        return otp;

	}


	public static void sendToEmail(Session session, String fromEmail, String subject, String body,String toEmail) throws MessagingException, UnsupportedEncodingException{

        MimeMessage msg = new MimeMessage(session);

        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

        msg.addHeader("format", "flowed");

        msg.addHeader("Content-Transfer-Encoding", "8bit");

        msg.setFrom(new InternetAddress(fromEmail, "ProBot"));

        msg.setReplyTo(InternetAddress.parse(toEmail, false));

        msg.setSubject(subject, "UTF-8");

        msg.setText(body, "UTF-8");

        msg.setSentDate(new Date());

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

        System.out.println("Message is ready");

        Transport.send(msg);

        System.out.println("EMail Sent Successfully!!");

	}
	
	private String generateOtp()
	{
		return String.valueOf((int)((Math.random()*900000)+10000));
	}
	
	public String approvingMail(String email, String oper) throws UnsupportedEncodingException, MessagingException {

		System.out.println("Outlook Email Start");

        String smtpHostServer = "smtp.office365.com";
        final String emailID = "Probot80373@outlook.com"; 
        final String password = "onamani123"; 
        
        String toEmail = email;
        String subject = "Subject: Super Admin has "+oper+" your account";
        
        String messageBody =  "Hello Admin "+email+",\r\n"
        						+ "\r\n"+
        						"We are sending you this email because "+ operationControl(oper)
        						+"\r\n";

        Properties props = new Properties();
        
        props.put("mail.smtp.host", smtpHostServer); 
        props.put("mail.smtp.port", "587"); 
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.smtp.starttls.enable", "true");


        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailID, password);
            }
        });

        sendToEmail(session, emailID, subject, messageBody,toEmail);
        
        return oper;

	}
	
	private String operationControl(String oper)
	{
		if(oper.equals("Approve"))
		{
			return "Super Admin has approved your account.Now, you can log with email and password.";
		}
		else
		{
			return "Super Admin has suspended your account. Due to unathourized activity from you.";
		}
	}

}