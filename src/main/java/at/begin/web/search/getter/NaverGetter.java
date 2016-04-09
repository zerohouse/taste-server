package at.begin.web.search.getter;

import at.begin.web.movie.MovieDto;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public abstract class NaverGetter extends HttpObjectGetter {

    protected NaverGetter(HttpRequestFactory httpRequestFactory) {
        super(httpRequestFactory);
    }

    protected Document getDocument(String url) throws IOException, ParserConfigurationException, SAXException {
        HttpRequest request = httpRequestFactory.buildGetRequest(new GenericUrl(url));
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", "Up9YCrPxnduZfLunjJfD");
        headers.set("X-Naver-Client-Secret", "F0DHva9svj");
        request.setHeaders(headers);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader((request.execute().parseAsString())));
        return builder.parse(inputSource);
    }


}
