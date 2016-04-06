package at.begin.web.book;

import at.begin.infra.response.JsonResponse;
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
    UserLikesBookRepository userLikesBookRepository;


    public JsonResponse addToCollection(User user, BookDto bookDto) {
        Book book = bookRepository.findOne(bookDto.id);
        if (book == null) {
            book = new Book(bookDto);
            bookRepository.save(book);
        }
        UserLikesBook userLikesBook = new UserLikesBook(user, book);
        userLikesBookRepository.save(userLikesBook);
        return successResponse(new BookDto(userLikesBook));
    }

    public JsonResponse removeFromCollection(User user, String id) {
        Book book = getEmptyBook(id);
        userLikesBookRepository.deleteByUserAndBook(user, book);
        return successResponse();
    }


    public JsonResponse updateComment(User user, String id, String comment) {
        Book book = bookRepository.findOne(id);
        UserLikesBook userLikesBook = userLikesBookRepository.findByUserAndBook(user, book);
        userLikesBook.setComment(comment);
        userLikesBookRepository.save(userLikesBook);
        return successResponse();
    }


    private Book getEmptyBook(String id) {
        Book book = new Book();
        book.setId(id);
        return book;
    }
}
