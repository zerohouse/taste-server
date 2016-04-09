package at.begin.web.search.getter;

import at.begin.web.music.MusicDto;
import com.google.api.client.http.HttpRequestFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MelonMusicGetter extends HttpObjectGetter<MusicDto> {

    public static final String MELON_MUSIC_URL = "http://www.melon.com/search/keyword/index.json?query=%s";

    public MelonMusicGetter(HttpRequestFactory httpRequestFactory) {
        super(httpRequestFactory);
    }

    @Override
    public List<MusicDto> getList(String query) throws IOException {
        Map map = getJsonMap(String.format(MELON_MUSIC_URL, query));
        List<MusicDto> musicDtos = new ArrayList<>();
        List<Map> artistContents = (List) map.get("ARTISTCONTENTS");
        List<Map> songContents = (List) map.get("SONGCONTENTS");
        List<Map> albumContents = (List) map.get("ALBUMCONTENTS");
        musicDtos.addAll(artistContents.stream().map(artistContent -> {
            MusicDto musicDto = new MusicDto();
            musicDto.setAsArtistContent(artistContent);
            return musicDto;
        }).collect(Collectors.toList()));
        musicDtos.addAll(songContents.stream().map(songContent -> {
            MusicDto musicDto = new MusicDto();
            musicDto.setAsSongContent(songContent);
            return musicDto;
        }).collect(Collectors.toList()));
        musicDtos.addAll(albumContents.stream().map(albumContent -> {
            MusicDto musicDto = new MusicDto();
            musicDto.setAsAlbumContent(albumContent);
            return musicDto;
        }).collect(Collectors.toList()));
        return musicDtos;
    }

}
