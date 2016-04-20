package at.begin.web.reply;

import at.begin.infra.response.JsonResponse;
import at.begin.web.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static at.begin.infra.response.JsonResponseFactory.errorResponse;
import static at.begin.infra.response.JsonResponseFactory.successResponse;

@Service
@Transactional
public class ReplyService {

    @Autowired
    ReplyRepository replyRepository;


    public JsonResponse deleteReply(User user, Long id) {
        Reply reply = replyRepository.findOne(id);
        if (reply == null)
            return errorResponse("없는 댓글입니다.");
        if (!reply.getUser().equals(user))
            return errorResponse("권한이 없습니다.");
        replyRepository.delete(reply);
        return successResponse();
    }

    public JsonResponse writeReply(User user, String message) {
        Reply reply = new Reply();
        reply.setUser(user);
        reply.setMessage(message);
        reply.setCreateAt(new Date());
        replyRepository.save(reply);
        return successResponse(new ReplyDto(reply));
    }


    public JsonResponse getList() {
        List<ReplyDto> replies = replyRepository.findAll().stream().map(ReplyDto::new).collect(Collectors.toList());
        return successResponse(replies);
    }
}
