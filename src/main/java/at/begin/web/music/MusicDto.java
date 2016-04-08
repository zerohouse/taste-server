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
    static final String ALBUM_LINK = "http://www.melon.com/album/detail.htm?albumId=";
    static final String SONG_LINK = "http://www.melon.com/song/detail.htm?songId=";
    static final String ARTIST_LINK = "http://www.melon.com/artist/timeline.htm?artistId=";

    String id;
    String songId;
    String artistId;
    String albumId;
    String link;
    String title;
    String image;
    String description;
    MusicType type;
    String comment;

    public MusicDto(UserLikesMusic userLikesMusic) {
        id = userLikesMusic.music.id;
        songId = userLikesMusic.music.songId;
        artistId = userLikesMusic.music.artistId;
        albumId = userLikesMusic.music.albumId;
        title = userLikesMusic.music.title;
        image = userLikesMusic.music.image;
        description = userLikesMusic.music.description;
        type = userLikesMusic.music.type;
        link = userLikesMusic.music.link;
        comment = userLikesMusic.comment;
    }

    public void setAsArtistContent(Map artistContent) {
        type = MusicType.ARTIST;
        artistId = (String) artistContent.get("ARTISTID");
        title = (String) artistContent.get("ARTISTNAME");
        image = STATIC_MELON_COM + artistContent.get("ARITSTIMG");
        description = artistContent.get("NATIONALITYNAME") + " " + artistContent.get("SEX") + " " + artistContent.get("ACTTYPENAMES");
        id = type + artistId;
        link = ARTIST_LINK + artistId;
    }

    public void setAsSongContent(Map songContent) {
        type = MusicType.SONG;
        songId = (String) songContent.get("SONGID");
        albumId = (String) songContent.get("ALBUMID");
        title = (String) songContent.get("SONGNAME");
        image = STATIC_MELON_COM + songContent.get("ALBUMIMG");
        description = songContent.get("ARTISTNAME") + " - " + songContent.get("ALBUMNAME");
        id = type + songId;
        link = SONG_LINK + songId;
    }

    public void setAsAlbumContent(Map albumContent) {
        type = MusicType.ALBUM;
        albumId = (String) albumContent.get("ALBUMID");
        title = (String) albumContent.get("ALBUMNAME");
        image = STATIC_MELON_COM + albumContent.get("ALBUMIMG");
        description = (String) albumContent.get("ARTISTNAME");
        id = type + albumId;
        link = ALBUM_LINK + albumId;
    }
}
