package at.begin.web.user;


import at.begin.web.content.ContentDto;
import at.begin.web.content.book.BookDto;
import at.begin.web.content.movie.MovieDto;
import at.begin.web.content.music.MusicDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDto {

    long id;
    String name;
    String email;
    String gender;
    Integer age;
    String district;
    List<ContentDto> contents;

    public UserDto(User user) {
        id = user.id;
        name = user.name;
        email = user.email;
        gender = user.gender;
        age = user.age;
        district = user.district;
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
