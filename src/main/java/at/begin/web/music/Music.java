package at.begin.web.music;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Music {


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
    MusicType type;


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
