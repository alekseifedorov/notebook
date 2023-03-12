package my.assignment.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

@Getter
@Setter
@Builder
public class TestResponse<T> {
    private MockHttpServletResponse rawResponse;
    private T object;
    private HttpStatus status;
}
