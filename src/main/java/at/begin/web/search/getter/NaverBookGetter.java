package at.begin.web.search.getter;

import at.begin.web.book.BookDto;
import at.begin.web.movie.MovieDto;
import com.google.api.client.http.HttpRequestFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NaverBookGetter extends NaverGetter {

    public static final String NAVER_BOOK_URL = "https://openapi.naver.com/v1/search/book.xml?display=100&query=%s";

    public NaverBookGetter(HttpRequestFactory httpRequestFactory) {
        super(httpRequestFactory);
    }

    @Override
    public List<BookDto> getList(String query) {
        List<BookDto> bookList = new ArrayList<>();
        try {
            NodeList elements = getDocument(String.format(NAVER_BOOK_URL, query)).getElementsByTagName("item");
            for (int i = 0; i < elements.getLength(); i++) {
                bookList.add(new BookDto((Element) elements.item(i)));
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return bookList;
    }

}
