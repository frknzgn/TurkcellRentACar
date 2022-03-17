package com.turkcell.rentacar.business.requests.individualCustomer;

import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {
	
	@Email
	//String regex = "^[A-Za-z0-9+_.-]+@(.+)$"; 
    private String email;
	//parola kuralları REGEX
//	@NotNull(message = "identifier must not be empty.")
//    @NotEmpty(message = "identifier must not be empty.")
//    @NotBlank(message = "identifier must not be empty.")
//    @Size(min = 36, max = 36)
//    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid password.") //***regex araştır
    private String password;
    //regex uygulanır.
    private String firstName;
	private String lastName;
	
}
