package at.begin.web.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.w3c.dom.Element;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static at.begin.infra.util.Util.getListProperty;
import static at.begin.infra.util.Util.getProperty;

@Getter
@NoArgsConstructor
@ToString
public class BookDto {
    String id;
    String title;
    String link;
    String image;
    String description;
    String pubDate;
    String comment;
    List<String> authors;
    String publisher;

    public BookDto(Element item) {
        id = getProperty(item, "isbn");
        title = getProperty(item, "title");
        description = getProperty(item, "description");
        link = getProperty(item, "link");
        image = getProperty(item, "image");
        pubDate = getProperty(item, "pubdate");
        publisher = getProperty(item, "publisher");
        authors = getListProperty(item, "author");
    }


    public BookDto(UserLikesBook userLikesBook) {
        id = userLikesBook.book.id;
        title = userLikesBook.book.title;
        link = userLikesBook.book.link;
        image = userLikesBook.book.image;
        description = userLikesBook.book.description;
        pubDate = userLikesBook.book.pubDate;
        publisher = userLikesBook.book.publisher;
        comment = userLikesBook.comment;
        authors = Arrays.asList(userLikesBook.book.authors.split("\\|"));
    }


    public BookDto(Map googleApiData) {
        Map volumeInfo = (Map) googleApiData.get("volumeInfo");
        id = (String) googleApiData.get("id");
        title = (String) volumeInfo.get("title");
        link = (String) volumeInfo.get("infoLink");
        description = (String) volumeInfo.get("description");
        pubDate = (String) volumeInfo.get("publishedDate");
        publisher = (String) volumeInfo.get("publisher");
        authors = (List<String>) volumeInfo.get("authors");
        Map imageLinks = (Map) volumeInfo.get("imageLinks");
        if (imageLinks == null)
            return;
        image = (String) imageLinks.get("thumbnail");
        if (image == null)
            return;
        image = image.replace("zoom=1", "zoom=2");
        image = image.replace("&edge=curl", "");
    }
}