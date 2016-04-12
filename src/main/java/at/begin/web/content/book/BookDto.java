package at.begin.web.content.book;

import at.begin.web.content.ContentDto;
import at.begin.web.content.ContentType;
import at.begin.web.content.UserLikesContent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.w3c.dom.Element;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static at.begin.infra.util.Util.getListProperty;
import static at.begin.infra.util.Util.getProperty;

@Getter
@NoArgsConstructor
@ToString
public class BookDto extends ContentDto {
    String id;
    String title;
    String link;
    String image;
    String description;
    String pubDate;
    String comment;
    List<String> authors;
    String publisher;
    ContentType type;
    Date createAt;
    Date updateAt;

    public BookDto(Element item) {
        id = getProperty(item, "isbn");
        title = getProperty(item, "title");
        description = getProperty(item, "description");
        link = getProperty(item, "link");
        image = getProperty(item, "image");
        if(image != null)
            image = image.replace("type=m1", "type=m5");
        pubDate = getProperty(item, "pubdate");
        publisher = getProperty(item, "publisher");
        authors = getListProperty(item, "author");
        type = ContentType.BOOK;
    }


    public BookDto(UserLikesContent userLikesContent) {
        id = userLikesContent.getBook().id;
        title = userLikesContent.getBook().title;
        link = userLikesContent.getBook().link;
        image = userLikesContent.getBook().image;
        description = userLikesContent.getBook().description;
        pubDate = userLikesContent.getBook().pubDate;
        publisher = userLikesContent.getBook().publisher;
        comment = userLikesContent.getComment();
        authors = Arrays.asList(userLikesContent.getBook().authors.split("\\|"));
        type = ContentType.BOOK;
        createAt = userLikesContent.getCreatedAt();
        updateAt = userLikesContent.getUpdateAt();
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
        type = ContentType.BOOK;
    }
}
