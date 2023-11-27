package sg.edu.nus.iss.Day13Workshop.Model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
  
  @Size(min = 8, message = "Contact ID must be at least 8 characters")
  private String contactId;

  @NotEmpty(message = "First name is mandatory.")
  @Size(min = 3, max = 64, message = "First Name must be between 3 to 64 characters.")
  private String name;

  @NotEmpty(message = "Email is mandatory.")
  @Email(message = "Invalid email format.")
  private String email;

  @NotEmpty(message = "Phone Number is mandatory.")
  @Pattern(regexp = "(8|9)[0-9]{7}", message = "Invalid phone number entered, must start with 8 or 9 and be 8 digits long.")
  private String phoneNumber;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Past(message = "Birth date must be a past date less than today.")
  private Date birthday;

  public Contact(String name, String email, String phoneNumber, Date birthday) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.birthday = birthday;
    this.contactId = ""; // Proper Id to be set when saving the contact, using the service.
  }

}
