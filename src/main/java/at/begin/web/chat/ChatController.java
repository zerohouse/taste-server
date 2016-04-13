package at.begin.web.chat;

import at.begin.infra.response.JsonResponse;
import at.begin.web.user.User;
import at.begin.web.user.inject.Logged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    @Autowired
    ChatService chatService;

    @RequestMapping("/invite")
    public JsonResponse chatInvite(@Logged User user, Long invitedUser) {
        return chatService.invite(user, invitedUser);
    }

    @RequestMapping("/agree")
    public JsonResponse chatAgree(@Logged User user, Long chat) {
        return chatService.agree(user, chat);
    }

    @RequestMapping("/decline")
    public JsonResponse chatDecline(@Logged User user, Long chat) {
        return chatService.decline(user, chat);
    }

    @RequestMapping("/leave")
    public JsonResponse chatLeave(@Logged User user, Long chat) {
        return chatService.leave(user, chat);
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public JsonResponse message(@Logged User user, String message, Long chat) {
        return chatService.chat(user, message, chat);
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public JsonResponse getMessage(@Logged User user, Long chat) {
        return chatService.getMessages(user, chat);
    }


}
