package uz.ibrokhimoff.messagesend.otp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : ibrokhimoff
 **/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OTPDomain {
    private Integer code;
    private Boolean isVerified;
}
