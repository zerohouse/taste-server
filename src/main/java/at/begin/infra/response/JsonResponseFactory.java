package at.begin.infra.response;

public class JsonResponseFactory {

    public static JsonResponse successResponse(Object result) {
        return new JsonResponse(ResultStatus.SUCCESS, result);
    }

    public static JsonResponse successResponse() {
        return new JsonResponse(ResultStatus.SUCCESS);
    }

    public static JsonResponse errorResponse(String message) {
        return new JsonResponse(ResultStatus.ERROR, message);
    }

    public static JsonResponse warningResponse(String message) {
        return new JsonResponse(ResultStatus.WARNING, message);
    }

    public static JsonResponse errorResponse() {
        return new JsonResponse(ResultStatus.ERROR);
    }

}
