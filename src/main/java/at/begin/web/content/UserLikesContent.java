package at.begin.web.content;


import at.begin.infra.exception.handler.UniqueKeys;
import at.begin.web.content.book.Book;
import at.begin.web.content.movie.Movie;
import at.begin.web.content.music.Music;
import at.begin.web.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(uniqueConstraints = @UniqueConstraint(name = UniqueKeys.CONTENT_ALREADY_EXIST, columnNames = {"user", "book", "music", "movie"}))
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserLikesContent {

    @ManyToOne
    @JoinColumn
    User user;

    @ManyToOne
    @JoinColumn
    Book book;

    @ManyToOne
    @JoinColumn
    Music music;

    @ManyToOne
    @JoinColumn
    Movie movie;

    @Id
    @Column
    @GeneratedValue
    long id;

    @Lob
    String comment;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    Date updateAt;

    public UserLikesContent(User user, Movie movie) {
        this.user = user;
        this.movie = movie;
        createTime();
    }

    public UserLikesContent(User user, Book book) {
        this.user = user;
        this.book = book;
        createTime();
    }

    public UserLikesContent(User user, Music music) {
        this.user = user;
        this.music = music;
        createTime();
    }

    public void setComment(String comment) {
        this.comment = comment;
        updateAt = new Date();
    }

    private void createTime() {
        updateAt = new Date();
        createdAt = new Date();
    }

}
