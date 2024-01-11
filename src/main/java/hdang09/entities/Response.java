package hdang09.entities;

import hdang09.constants.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response<T> {
    private ResponseStatus status;
    private String message;
    private T data;
}
