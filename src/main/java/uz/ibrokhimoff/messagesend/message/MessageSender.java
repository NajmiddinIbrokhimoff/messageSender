package uz.ibrokhimoff.messagesend.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.ibrokhimoff.messagesend.message.dto.ContentResponse;
import uz.ibrokhimoff.messagesend.message.dto.MessageResponse;
import uz.ibrokhimoff.messagesend.message.dto.MessageSendResponse;
import uz.ibrokhimoff.messagesend.message.dto.SmsResponse;
import uz.ibrokhimoff.messagesend.otp.OTPService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

/**
 * @author : ibrokhimoff
 **/
@Component
public class MessageSender {
    @Autowired
    private OTPService otpService;

    private final String messageSendUrl = "https://send.smsxabar.uz/broker-api/send";

    private final String authorization = "Basic ...";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MessageSender() {
    }

    public Integer send(String phone) throws IOException {
        int OTP = otpService.generateOTP(phone);
        String message = createMessage(phone, OTP);
        HttpURLConnection http = post();
        byte[] out = message.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
        System.out.println(OTP);
        return OTP;
    }

    public HttpURLConnection post() throws IOException {
        URL url = new URL(messageSendUrl);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("Authorization", authorization);
        return http;
    }

    private String createMessage(String phone, int OTP) throws JsonProcessingException {
        String content = String.format("""
                Kod: %d - Tasdiqlash kodi. (hech kimga aytmang)
                """, OTP);
        MessageResponse messageDTO = new MessageResponse();
        messageDTO.setMessageId(UUID.randomUUID().toString());
        messageDTO.setRecipient(phone);
        messageDTO.setSms(new SmsResponse("3700", new ContentResponse(content)));
        List<MessageResponse> messageDTOS = new ArrayList<>();
        messageDTOS.add(messageDTO);
        MessageSendResponse messages = new MessageSendResponse(messageDTOS);
        return objectMapper.writeValueAsString(messages);
    }
    private static String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

}
