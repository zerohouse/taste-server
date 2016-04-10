package at.begin.web.content.book;

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
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserLikesContentRepository userLikesContentRepository;


    public JsonResponse addToCollection(User user, BookDto bookDto) {
        Book book = bookRepository.findOne(bookDto.id);
        if (book == null) {
            book = new Book(bookDto);
            bookRepository.save(book);
        }
        UserLikesContent userLikesContent = new UserLikesContent(user, book);
        userLikesContentRepository.save(userLikesContent);
        return successResponse(new BookDto(userLikesContent));
    }

    public JsonResponse removeFromCollection(User user, String id) {
        Book book = getEmptyBook(id);
        userLikesContentRepository.deleteByUserAndBook(user, book);
        return successResponse();
    }


    public JsonResponse updateComment(User user, String id, String comment) {
        Book book = bookRepository.findOne(id);
        UserLikesContent userLikesContent = userLikesContentRepository.findByUserAndBook(user, book);
        userLikesContent.setComment(comment);
        userLikesContentRepository.save(userLikesContent);
        return successResponse();
    }


    private Book getEmptyBook(String id) {
        Book book = new Book();
        book.setId(id);
        return book;
    }
}
