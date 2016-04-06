package at.begin.web.user;


import at.begin.web.movie.MovieDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDto {

    private long id;
    private String name;
    private String email;
    private List<MovieDto> movies;

    public UserDto(User user) {
        this.id = user.id;
        this.name = user.name;
        this.email = user.email;
        movies = user.userLikesMovies.stream().map(MovieDto::new).collect(Collectors.toList());
    }

}
