package uz.ibrokhimoff.messagesend.message.dto;

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
public class SmsResponse {
    private String originator;
    private ContentResponse content;
}
