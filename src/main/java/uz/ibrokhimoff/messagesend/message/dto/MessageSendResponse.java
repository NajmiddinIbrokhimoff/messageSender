package uz.ibrokhimoff.messagesend.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author : ibrokhimoff
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageSendResponse {
    private List<MessageResponse> messages;

}
