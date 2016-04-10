package at.begin.web.content.music;

import at.begin.infra.response.JsonResponse;
import at.begin.web.content.UserLikesContent;
import at.begin.web.content.UserLikesContentRepository;
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
    UserLikesContentRepository userLikesContentRepository;


    public JsonResponse addToCollection(User user, MusicDto musicDto) {
        Music music = musicRepository.findOne(musicDto.id);
        if (music == null) {
            music = new Music(musicDto);
            musicRepository.save(music);
        }
        UserLikesContent userLikesContent = new UserLikesContent(user, music);
        userLikesContentRepository.save(userLikesContent);
        return successResponse(new MusicDto(userLikesContent));
    }

    public JsonResponse removeFromCollection(User user, String id) {
        Music music = getEmptyMusic(id);
        userLikesContentRepository.deleteByUserAndMusic(user, music);
        return successResponse();
    }


    public JsonResponse updateComment(User user, String id, String comment) {
        Music music = musicRepository.findOne(id);
        UserLikesContent userLikesContent = userLikesContentRepository.findByUserAndMusic(user, music);
        userLikesContent.setComment(comment);
        userLikesContentRepository.save(userLikesContent);
        return successResponse();
    }


    private Music getEmptyMusic(String id) {
        Music music = new Music();
        music.setId(id);
        return music;
    }
}
