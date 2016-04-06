package at.begin.web.book;


import at.begin.infra.exception.handler.UniqueKeys;
import at.begin.web.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(uniqueConstraints = @UniqueConstraint(name = UniqueKeys.BOOK_ALREADY_EXIST, columnNames = {"user", "book"}))
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserLikesBook {

    @ManyToOne
    @JoinColumn
    User user;

    @ManyToOne
    @JoinColumn
    Book book;

    @Id
    @Column
    @GeneratedValue
    long id;

    @Lob
    String comment;

    public UserLikesBook(User user, Book book) {
        this.user = user;
        this.book = book;
    }
}
