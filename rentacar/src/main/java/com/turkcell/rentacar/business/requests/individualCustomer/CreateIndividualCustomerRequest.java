package com.turkcell.rentacar.business.requests.individualCustomer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {
	

    private Long nationalityId;
	

    private String firstName;
    
 
    private String lastName;
    
    
    private String email;
    
    
    private String password;
	
}


//parola kuralları REGEX
//@NotNull(message = "identifier must not be empty.")
//@NotEmpty(message = "identifier must not be empty.")
//@NotBlank(message = "identifier must not be empty.")
//@Size(min = 36, max = 36)
//@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid password.") //***regex araştır