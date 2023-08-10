package com.cafe.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.entity.User;
import com.cafe.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:4200","*"}, allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserService ms;
	
	@GetMapping("/home")
	public String home()
	{
		return "User Service";
	}
	
    @GetMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = ms.login(username, password);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password incorrect.");
        }
    }
    
	@GetMapping
	public List<User> getAllUsers()
	{
		return ms.getAllUsers();
	}
		
	@PostMapping
	public User addUser(@RequestBody User user)
	{
		System.out.println("What i have received: "+user);
		return ms.createUser(user);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user)
	{
		System.out.println("entering modify method "+ user);
		return ms.updateUser(id, user);
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id)
	{
		System.out.println("entering getUserbyId method "+ id);
		return ms.getUserById(id);
	}
	
	@DeleteMapping("/{id}")
	public User deleteUser(@PathVariable("id") Long id)
	{
		System.out.println("entering delete method "+ id);
		return ms.deleteUser(id);
	}
	
//	@GetMapping("/user/count")
//	public User getUserCount() {
//		return ms.getUsersCount();
//	}

//	@GetMapping("/user/otp/{email}")
//	public String getOtp(@PathVariable String email)
//	{
//		long otp=(long) (Math.random()*(1000000));
//		sendEmail(email, otp+"");
//		return otp+"";
//	}
	
//	@GetMapping("/user/{username}/{password}/{role}")
//	public Optional<User> login(@PathVariable String username, @PathVariable String password,@PathVariable String role )
//	{
//		Optional<User> user = ms.read(username);
//		System.out.println("logging --> " + user);
//		if(user.isPresent()) {
//			if(user.get().getPassword().equals(password))
//				return user;
//			else {
//				return Optional.ofNullable(new User("Incorrect Password"));
//			}
//		}else {
//			return null;
//		}
//	}

//	private void sendEmail(String to, String otp)
//	{
//		 Properties props = new Properties();
//	        props.put("mail.smtp.host", "true");
//	        props.put("mail.smtp.starttls.enable", "true");
//	        props.put("mail.smtp.host", "smtp.gmail.com");
//	        props.put("mail.smtp.port", "587");
//	        props.put("mail.smtp.auth", "true");
//	        //Establishing a session with required user details
//	        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//	            protected PasswordAuthentication getPasswordAuthentication() {
//	                return new PasswordAuthentication("alluhit45@gmail.com", "kranthi961796");
////	                return new PasswordAuthentication("hit45.vijju@gmail.com", "ks961796");
//	                
//	            }
//	        });
//	        try {
//	            //Creating a Message object to set the email content
//	            MimeMessage msg = new MimeMessage(session);
//	            //Storing the comma seperated values to email addresses
//                //String to = "alluhit45@gmail.com.com";
//	            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
//	            addresses in an array of InternetAddress objects*/
//	            InternetAddress[] address = InternetAddress.parse(to, true);
//	            //Setting the recepients from the address variable
//	            msg.setRecipients(Message.RecipientType.TO, address);
//	            String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
//	            msg.setSubject("Sample Mail : " + timeStamp);
//	            msg.setSentDate(new Date());
//	            msg.setText(otp+" is the One time password to signup in our website. Do not share this with any one.");
//	            msg.setHeader("XPriority", "1");
//	           
//	            Transport.send(msg);
//	            System.out.println("Mail has been sent successfully");
//	        } catch (MessagingException mex) {
//	            System.out.println("Unable to send an email:\n" + mex);
//	        }
//	}

}

