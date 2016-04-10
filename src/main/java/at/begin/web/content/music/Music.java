package at.begin.web.content.music;

import at.begin.web.content.ContentType;
import at.begin.web.content.UserLikesContent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Music {

    @OneToMany(mappedBy = "music")
    private List<UserLikesContent> userLikesContents = new ArrayList<>();

    @Id
    @Column
    String id;

    @Column
    String songId;

    @Column
    String artistId;

    @Column
    String albumId;

    @Column
    String title;

    @Column
    String image;

    @Column
    String description;

    @Column
    String link;

    @Enumerated(EnumType.STRING)
    @Column
    ContentType type;


    public Music(MusicDto musicDto) {
        id = musicDto.id;
        songId = musicDto.songId;
        artistId = musicDto.artistId;
        albumId = musicDto.albumId;
        title = musicDto.title;
        image = musicDto.image;
        description = musicDto.description;
        type = musicDto.type;
        link = musicDto.link;
    }
}
