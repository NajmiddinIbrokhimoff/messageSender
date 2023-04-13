package uz.ibrokhimoff.messagesend.message.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class MessageResponse {

    private String recipient;

    @JsonProperty(value = "message-id")
    private String messageId;

    private SmsResponse sms;

    public MessageResponse(MessageResponse messageResponse) {

    }
}
