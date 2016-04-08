package at.begin.web.user;


import at.begin.web.book.BookDto;
import at.begin.web.movie.MovieDto;
import at.begin.web.music.MusicDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
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
    List<MovieDto> movies;
    List<MusicDto> musics;
    List<BookDto> books;

    public UserDto(User user) {
        id = user.id;
        name = user.name;
        email = user.email;
        gender = user.gender;
        age = user.age;
        district = user.district;
        movies = user.userLikesMovies.stream().map(MovieDto::new).collect(Collectors.toList());
        musics = user.userLikesMusics.stream().map(MusicDto::new).collect(Collectors.toList());
        books = user.userLikesBooks.stream().map(BookDto::new).collect(Collectors.toList());
    }

}
