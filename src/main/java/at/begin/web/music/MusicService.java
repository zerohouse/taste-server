package at.begin.web.music;

import at.begin.infra.response.JsonResponse;
import at.begin.web.movie.*;
import at.begin.web.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static at.begin.infra.response.JsonResponseFactory.successResponse;

@Service
@Transactional
public class MusicService {

    @Autowired
    MusicRepository musicRepository;

    @Autowired
    UserLikesMusicRepository userLikesMusicRepository;


    public JsonResponse addToCollection(User user, MusicDto musicDto) {
        Music music = musicRepository.findOne(musicDto.id);
        if (music == null) {
            music = new Music(musicDto);
            musicRepository.save(music);
        }
        UserLikesMusic userLikesMusic = new UserLikesMusic(user, music);
        userLikesMusicRepository.save(userLikesMusic);
        return successResponse(new MusicDto(userLikesMusic));
    }

    public JsonResponse removeFromCollection(User user, String id) {
        Music music = getEmptyMusic(id);
        userLikesMusicRepository.deleteByUserAndMusic(user, music);
        return successResponse();
    }


    public JsonResponse updateComment(User user, String id, String comment) {
        Music music = musicRepository.findOne(id);
        UserLikesMusic userLikesMusic = userLikesMusicRepository.findByUserAndMusic(user, music);
        userLikesMusic.setComment(comment);
        userLikesMusicRepository.save(userLikesMusic);
        return successResponse();
    }


    private Music getEmptyMusic(String id) {
        Music music = new Music();
        music.setId(id);
        return music;
    }
}
