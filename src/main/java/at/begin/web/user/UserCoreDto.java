package at.begin.web.user;

import at.begin.web.chat.ChatDto;
import at.begin.web.content.book.BookDto;
import at.begin.web.content.movie.MovieDto;
import at.begin.web.content.music.MusicDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.stream.Collectors;

@Getter
@Setter
public class UserCoreDto {
    long id;
    String name;
    String email;
    String gender;
    String photo;
    String introduce;
    Integer age;
    String district;

    public UserCoreDto(User user) {
        id = user.id;
        name = user.name;
        email = user.email;
        gender = user.gender;
        introduce = user.introduce;
        photo = user.photo;
        age = user.age;
        district = user.district;
    }

}
