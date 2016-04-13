package at.begin.infra.exception.handler;

import at.begin.infra.exception.DataNotExistException;
import at.begin.infra.exception.LoginNeededException;
import at.begin.infra.exception.SendErrorMessage;
import at.begin.infra.exception.SendWarningMessage;
import at.begin.infra.response.JsonResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;

import static at.begin.infra.response.JsonResponseFactory.errorResponse;
import static at.begin.infra.response.JsonResponseFactory.warningResponse;

@Service
@ControllerAdvice
@RestController
public class ExceptionHandleController {

    @ExceptionHandler(LoginNeededException.class)
    public JsonResponse loginException(LoginNeededException e) {
        return errorResponse("로그인이 필요한 서비스입니다.");
    }

    @ExceptionHandler(DataNotExistException.class)
    public JsonResponse dataNotExist(DataNotExistException e) {
        return errorResponse();
    }

    @ExceptionHandler(SendErrorMessage.class)
    public JsonResponse messagingError(SendErrorMessage e) {
        return errorResponse(e.getMessage());
    }

    @ExceptionHandler(SendWarningMessage.class)
    public JsonResponse messagingWarning(SendWarningMessage e) {
        return warningResponse(e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResponse ConstraintViolationException(ConstraintViolationException e) {
        return warningResponse(e.getConstraintViolations().iterator().next().getMessageTemplate());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public JsonResponse dataIntegrityViolationException(DataIntegrityViolationException e) {
        return warningResponse(UniqueKeys.getErrorMessage(e.getCause().getCause().getMessage()));
    }

}
