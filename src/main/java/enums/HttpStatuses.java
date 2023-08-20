package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpStatuses {

    STATUS_OK(200),
    STATUS_CREATED(201),
    STATUS_UNAUTHORIZED(401),
    STATUS_UNPROCESSABLE_ENTITY(422);

    private final int statusCode;
}
