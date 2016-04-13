package at.begin.web.user;


import at.begin.web.chat.ChatDto;
import at.begin.web.content.ContentDto;
import at.begin.web.content.book.BookDto;
import at.begin.web.content.movie.MovieDto;
import at.begin.web.content.music.MusicDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDto extends UserCoreDto {

    List<ContentDto> contents;
    List<ChatDto> chats;
    List<UserCoreDto> matchedUsers;

    public UserDto(User user) {
        super(user);
        chats = user.getAllChats().stream().map(ChatDto::new).collect(Collectors.toList());
        matchedUsers = user.userMatchingUsers.stream().map(userMatchedUser -> new UserCoreDto(userMatchedUser.getMatched())).collect(Collectors.toList());
        contents = user.userLikesContents.stream().map(userLikesContent -> {
            if (userLikesContent.getMusic() != null)
                return new MusicDto(userLikesContent);
            if (userLikesContent.getMovie() != null)
                return new MovieDto(userLikesContent);
            if (userLikesContent.getBook() != null)
                return new BookDto(userLikesContent);
            return null;
        }).collect(Collectors.toList());
    }

}
