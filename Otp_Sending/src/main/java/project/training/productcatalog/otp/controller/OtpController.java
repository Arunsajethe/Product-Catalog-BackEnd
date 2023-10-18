package project.training.productcatalog.otp.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import project.training.productcatalog.otp.model.Otp;
import project.training.productcatalog.otp.service.OtpService;

@RestController
public class OtpController {
	
	@Autowired
	private OtpService otpService;
	
	@CrossOrigin(origins = "http://localhost:4200/")
	@PostMapping("/sendOtp")
	public Otp sendOtp(@RequestBody String email) throws UnsupportedEncodingException, MessagingException
	{
		System.out.println(email);
		try 
		{
            
            String otp = otpService.sendMail(email);
            
            Otp otp2 = Otp.builder().otp(otp).build();
            System.out.println(otp2);            
            return otp2;
        } 
		catch (Exception e) {
			System.out.println(e);
            return null;
        }
	}
	
	@CrossOrigin(origins = "http://localhost:4200/")
	@PostMapping("/approving")
	public Otp authoriztionFromSuperAdmin(@RequestParam String email, @RequestParam String oper) throws UnsupportedEncodingException, MessagingException
	{
		System.out.println(email+" "+oper);
		
		otpService.approvingMail(email, oper);
		
		return null;
	}
	
	
	
	

}
