package at.begin.web.search.getter;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson.JacksonFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public abstract class HttpObjectGetter<T> {

    protected final HttpRequestFactory httpRequestFactory;

    HttpObjectGetter(HttpRequestFactory httpRequestFactory) {
        this.httpRequestFactory = httpRequestFactory;
    }

    public abstract List<T> getList(String query) throws IOException;

    protected Map getJsonMap(String url) throws IOException {
        HttpRequest request = httpRequestFactory.buildGetRequest(new GenericUrl(url));
        request.setParser(new JsonObjectParser(new JacksonFactory()));
        return request.execute().parseAs(Map.class);
    }
}
