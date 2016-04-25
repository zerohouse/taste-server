package at.begin.web.auth;

import at.begin.infra.response.JsonResponse;
import at.begin.infra.sender.SmsSender;
import at.begin.infra.util.Util;
import at.begin.web.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Date;

import static at.begin.infra.response.JsonResponseFactory.errorResponse;
import static at.begin.infra.response.JsonResponseFactory.successResponse;

@Service
public class AuthService {

    @Autowired
    PhoneAuthRepository phoneAuthRepository;

    SmsSender smsSender;

    @PostConstruct
    public void setup() {
        smsSender = new SmsSender("zerohouse", "01067662010");
    }

    public JsonResponse phoneRequest(User user, String phone) throws IOException {
        PhoneAuth phoneAuth = new PhoneAuth();
        phoneAuth.setUserId(user.getId());
        phoneAuth.setCreateAt(new Date());
        String auth = Util.rand("0123456789", 3);
        phoneAuth.setNumber(auth);
        phoneAuth.setPhone(phone);
        phoneAuthRepository.save(phoneAuth);
        if (smsSender.send(phone.replaceAll("-", ""), String.format("인증번호는 [%s]입니다.", auth)))
            return successResponse();
        return errorResponse("인증번호 발송중 오류가 발생했습니다.");
    }


}
