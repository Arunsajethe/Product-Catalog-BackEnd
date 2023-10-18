package project.training.productcatalog.register.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Registration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int register_id;
	
	private String firstname;
	
	private String lastname;
	
	private int age;
	
	private String gender;
	
	private String address;
	
	private String phno;
	
	private String email;
	
	private String password;
	
	private String checkstatus;
	
	private String role;

}
