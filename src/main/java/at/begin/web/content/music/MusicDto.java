package at.begin.web.content.music;

import at.begin.web.content.ContentDto;
import at.begin.web.content.ContentType;
import at.begin.web.content.UserLikesContent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class MusicDto extends ContentDto {

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
    ContentType type;
    String comment;

    public MusicDto(UserLikesContent userLikesContent) {
        id = userLikesContent.getMusic().id;
        songId = userLikesContent.getMusic().songId;
        artistId = userLikesContent.getMusic().artistId;
        albumId = userLikesContent.getMusic().albumId;
        title = userLikesContent.getMusic().title;
        description = userLikesContent.getMusic().description;
        type = userLikesContent.getMusic().type;
        link = userLikesContent.getMusic().link;
        comment = userLikesContent.getComment();
    }

    public void setAsArtistContent(Map artistContent) {
        type = ContentType.ARTIST;
        artistId = (String) artistContent.get("ARTISTID");
        title = (String) artistContent.get("ARTISTNAME");
//        image = STATIC_MELON_COM + artistContent.get("ARITSTIMG");
        description = artistContent.get("NATIONALITYNAME") + " " + artistContent.get("SEX") + " " + artistContent.get("ACTTYPENAMES");
        id = type + artistId;
        link = ARTIST_LINK + artistId;
    }

    public void setAsSongContent(Map songContent) {
        type = ContentType.SONG;
        songId = (String) songContent.get("SONGID");
        albumId = (String) songContent.get("ALBUMID");
        title = (String) songContent.get("SONGNAME");
//        image = STATIC_MELON_COM + songContent.get("ALBUMIMG");
        description = songContent.get("ARTISTNAME") + " - " + songContent.get("ALBUMNAME");
        id = type + songId;
        link = SONG_LINK + songId;
    }

    public void setAsAlbumContent(Map albumContent) {
        type = ContentType.ALBUM;
        albumId = (String) albumContent.get("ALBUMID");
        title = (String) albumContent.get("ALBUMNAME");
//        image = STATIC_MELON_COM + albumContent.get("ALBUMIMG");
        description = (String) albumContent.get("ARTISTNAME");
        id = type + albumId;
        link = ALBUM_LINK + albumId;
    }
}
