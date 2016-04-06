package at.begin.web.music;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class MusicDto {

    static final String STATIC_MELON_COM = "http://static.melon.com";

    String songId;
    String artistId;
    String albumId;
    String name;
    String img;
    String description;
    MusicType type;

    public void setAsArtistContent(Map artistContent) {
        type = MusicType.ARTIST;
        artistId = (String) artistContent.get("ARTISTID");
        name = (String) artistContent.get("ARTISTNAME");
        img = STATIC_MELON_COM + artistContent.get("ARITSTIMG");
        description = artistContent.get("NATIONALITYNAME") + " " + artistContent.get("SEX") + " " + artistContent.get("ACTTYPENAMES");
    }

    public void setAsSongContent(Map songContent) {
        type = MusicType.SONG;
        songId = (String) songContent.get("SONGID");
        albumId = (String) songContent.get("ALBUMID");
        name = (String) songContent.get("SONGNAME");
        img = STATIC_MELON_COM + songContent.get("ALBUMIMG");
        description = songContent.get("ARTISTNAME") + " - " + songContent.get("ALBUMNAME");
    }

    public void setAsAlbumContent(Map albumContent) {
        type = MusicType.ALBUM;
        albumId = (String) albumContent.get("ALBUMID");
        name = (String) albumContent.get("ALBUMNAME");
        img = STATIC_MELON_COM + albumContent.get("ALBUMIMG");
        description = (String) albumContent.get("ARTISTNAME");
    }
}
