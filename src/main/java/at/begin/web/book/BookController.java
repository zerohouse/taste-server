package at.begin.web.book;


import at.begin.infra.response.JsonResponse;
import at.begin.web.user.User;
import at.begin.web.user.inject.Logged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(method = RequestMethod.POST)
    public JsonResponse addMovie(@Logged User user, @RequestBody BookDto bookDto) {
        return bookService.addToCollection(user, bookDto);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public JsonResponse addMovie(@Logged User user, String id) {
        return bookService.removeFromCollection(user, id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public JsonResponse updateComment(@Logged User user, String link, String comment) {
        return bookService.updateComment(user, link, comment);
    }

}
