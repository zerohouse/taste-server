package at.begin.web.chat;

import at.begin.infra.response.JsonResponse;
import at.begin.web.chat.message.Message;
import at.begin.web.chat.message.MessageDto;
import at.begin.web.chat.message.MessageRepository;
import at.begin.web.user.User;
import at.begin.web.user.UserRepository;
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
public class ChatService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    MessageRepository messageRepository;


    public JsonResponse invite(User user, Long invitedUser) {
        User invited = userRepository.findOne(invitedUser);
        Chat chat = new Chat();
        chat.setHostUser(user);
        chat.setInvitedUser(invited);
        chat.setState(ChatState.NOT_ACCEPTED);
        chatRepository.save(chat);
        return successResponse(new ChatDto(chat));
    }


    public JsonResponse agree(User user, Long chatId) {
        Chat chat = chatRepository.findOne(chatId);
        if (!chat.isInvited(user))
            return errorResponse();
        chat.setState(ChatState.OPEN);
        return successResponse(new ChatDto(chat));
    }

    public JsonResponse decline(User user, Long chatId) {
        Chat chat = chatRepository.findOne(chatId);
        if (!chat.isInvited(user))
            return errorResponse();
        chat.exit(user);
        chat.setState(ChatState.DECLINED);
        return successResponse(new ChatDto(chat));
    }


    public JsonResponse leave(User user, Long chatId) {
        Chat chat = chatRepository.findOne(chatId);
        if (!chat.isJoinedUser(user))
            return errorResponse();
        if (chat.state == ChatState.NOT_ACCEPTED) { // 아직 수락하기 전이면
            if (chat.isInvited(user)) { // 초대된 유저면
                chat.setState(ChatState.DECLINED); // 채팅방 거부상태로 만들고
                chat.exit(user);
                return successResponse(new ChatDto(chat));
            }
            chat.setState(ChatState.CLOSED); // 초대한 유저면 채팅방 닫힘 상태
            chat.clearUser(); // 다나감처리
            return successResponse(new ChatDto(chat));
        }
        chat.exit(user);
        chat.setState(ChatState.CLOSED);
        return successResponse(new ChatDto(chat));
    }

    public JsonResponse chat(User user, String message, Long chatId) {
        Chat chat = chatRepository.findOne(chatId);
        if (!chat.isJoinedUser(user))
            return errorResponse("유효하지 않은 접근입니다.");
        if (!chat.chatable())
            return errorResponse("대화 가능한 상태가 아닙니다.");
        Message chatMessage = new Message();
        chatMessage.setChat(chat);
        chatMessage.setUser(user);
        chatMessage.setMessage(message);
        chatMessage.setCreateAt(new Date());
        chat.getMessages().add(chatMessage);
        messageRepository.save(chatMessage);
        return successResponse(new MessageDto(chatMessage));
    }

    public JsonResponse getMessages(User user, Long chatId) {
        Chat chat = chatRepository.findOne(chatId);
        if (!chat.isJoinedUser(user))
            return errorResponse();
        List<Message> messageList = chat.getMessages();
        return successResponse(messageList.stream().map(MessageDto::new).collect(Collectors.toList()));
    }
}
