package at.begin.infra.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class JsonResponse {

    private Object result;
    private ResultStatus status;

    public JsonResponse(ResultStatus status, Object result) {
        this.status = status;
        this.result = result;
    }

    public JsonResponse(ResultStatus status) {
        this.status = status;
    }
}
