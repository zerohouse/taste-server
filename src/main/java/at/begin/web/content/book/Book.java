package at.begin.web.content.book;

import at.begin.web.content.UserLikesContent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static at.begin.infra.util.Util.joinString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Book {

    @OneToMany(mappedBy = "book")
    private List<UserLikesContent> userLikesContents = new ArrayList<>();

    @Id
    @Column
    String id;

    @Column
    String link;

    @Column
    String title;

    @Column
    String image;

    @Column(length = 1250)
    String description;

    @Column
    String pubDate;

    @Column
    String authors;

    @Column
    String publisher;

    public Book(BookDto bookDto) {
        id = bookDto.id;
        link = bookDto.link;
        title = bookDto.title;
        image = bookDto.image;
        description = bookDto.description;
        pubDate = bookDto.pubDate;
        publisher = bookDto.publisher;
        authors = joinString(bookDto.authors);
    }
}
