package at.begin.web.search.getter;

import at.begin.web.content.movie.MovieDto;
import com.google.api.client.http.HttpRequestFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DaumMovieGetter extends HttpObjectGetter<MovieDto> {

    public static final String DAUM_MOVIE_URL = "https://apis.daum.net/contents/movie?apikey=d8e8f43397c3266afefaf3522c5ba7a6&q=%s&output=json";

    public DaumMovieGetter(HttpRequestFactory httpRequestFactory) {
        super(httpRequestFactory);
    }

    @Override
    public List<MovieDto> getList(String query) throws IOException {
        Map map = getJsonMap(String.format(DAUM_MOVIE_URL, query));
        List<Map> mapList = (List<Map>) ((Map) map.get("channel")).get("item");
        if (mapList == null)
            return null;
        return mapList.stream().map(MovieDto::new).collect(Collectors.toList());
    }

}
