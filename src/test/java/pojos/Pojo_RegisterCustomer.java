package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pojo_RegisterCustomer {
    /*
        {
    "first_name": "sdad",
    "last_name": "sdsd",
    "username":"sdsadas",
    "email": "daasdasdss@gmail.com",
    "password": "As.123123",
    "password_confirmation": "As.123123",
    "user_type": "customer",
    "phone":"121123123",
    "referral_code": "44546545464546"
        }
         */

    private String first_name;
    private String last_name;
    private String username;
    private String email;
    private String password;
    private String password_confirmation;
    private String user_type;
    private String phone;
    private String referral_code;

}
