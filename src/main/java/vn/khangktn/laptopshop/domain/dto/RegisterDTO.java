package vn.khangktn.laptopshop.domain.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterDTO {
    @NotBlank(message = "FirstName not blank")
    String firstName;
    @NotBlank(message = "LastName not blank")
    String lastName;
    @NotBlank(message = "Email not blank")
    String email;

    @NotBlank(message = "Password not blank")
    @Length(min = 6, message = "Password must be least 6 character")
    String password;
    String confirmPassword;
}
