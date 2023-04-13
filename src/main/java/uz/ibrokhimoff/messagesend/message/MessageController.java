package uz.ibrokhimoff.messagesend.message;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author : ibrokhimoff
 **/
@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageSender messageSender;
    @PostMapping("/send-message")
    public ResponseEntity<?> sendMessage(@RequestParam String phone) throws IOException {
        Integer data = messageSender.send(phone);
        return ResponseEntity.ok(data);
    }
}
