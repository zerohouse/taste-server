package at.begin.web.search.getter;

import at.begin.web.content.book.BookDto;
import com.google.api.client.http.HttpRequestFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GoogleBookGetter extends HttpObjectGetter<BookDto> {

    public static final String BOOK_URL = "https://www.googleapis.com/books/v1/volumes?q=%s";

    public GoogleBookGetter(HttpRequestFactory httpRequestFactory) {
        super(httpRequestFactory);
    }

    @Override
    public List<BookDto> getList(String query) throws IOException {
        Map map = getJsonMap(String.format(BOOK_URL, query));
        List<Map> mapList = (List<Map>) map.get("items");
        return mapList.stream().map(BookDto::new).collect(Collectors.toList());
    }


}
