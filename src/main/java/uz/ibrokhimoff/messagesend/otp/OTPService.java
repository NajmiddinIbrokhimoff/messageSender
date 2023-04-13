package uz.ibrokhimoff.messagesend.otp;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OTPService {

    private static final Integer EXPIRE_MINUTES = 10;
    private LoadingCache<String, OTPDomain> otpCache;

    public OTPService() {
        otpCache = CacheBuilder.newBuilder().
                expireAfterWrite(EXPIRE_MINUTES, TimeUnit.MINUTES)
                .build(new CacheLoader<String, OTPDomain>() {
                    public OTPDomain load(String key) {
                        return new OTPDomain();
                    }
                });
    }

    public int generateOTP(String key) {
        Random random = new Random();
        int code = 1000 + random.nextInt(9000);
        otpCache.put(key, new OTPDomain(code, false));
        return code;
    }


    public OTPDomain getOtp(String key) {
        try {
            return otpCache.get(key);
        } catch (Exception e) {
            return new OTPDomain();
        }
    }

    public void clearOTP(String key) {
        otpCache.invalidate(key);
    }
}
