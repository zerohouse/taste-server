package at.begin.web.reply;


import at.begin.infra.response.JsonResponse;
import at.begin.web.user.User;
import at.begin.web.user.inject.Logged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reply")
public class ReplyController {

    @Autowired
    ReplyService replyService;

    @RequestMapping(method = RequestMethod.POST)
    public JsonResponse writeReply(@Logged User user, String message){
        return replyService.writeReply(user, message);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public JsonResponse deleteReply(@Logged User user, Long id){
        return replyService.deleteReply(user, id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public JsonResponse getReplies(){
        return replyService.getList();
    }

}
