package at.begin.web.auth;


import at.begin.infra.response.JsonResponse;
import at.begin.web.user.User;
import at.begin.web.user.inject.Logged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @RequestMapping("/phone")
    public JsonResponse chatInvite(@Logged User user, String phone) throws IOException {
        return authService.phoneRequest(user, phone);
    }


}
