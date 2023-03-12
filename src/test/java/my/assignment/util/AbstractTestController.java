package my.assignment.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public abstract class AbstractTestController {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @SneakyThrows
    protected MockHttpServletRequestBuilder preparePostRequest(String path, Object body) {
        return MockMvcRequestBuilders
            .post(path)
            .content(objectMapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON);
    }

    protected MockHttpServletRequestBuilder prepareGetRequest(String path) {
        return MockMvcRequestBuilders
            .get(path)
            .contentType(MediaType.APPLICATION_JSON);
    }

    @SneakyThrows
    protected <T> TestResponse<T> perform(MockHttpServletRequestBuilder builder, TypeReference<T> type) {
        var rawResponse = mockMvc.perform(builder).andReturn().getResponse();
        var responseString = rawResponse.getContentAsString();
        var status = HttpStatus.resolve(rawResponse.getStatus());

        T object = objectMapper.readValue(responseString, type);

        return TestResponse.<T>builder()
            .rawResponse(rawResponse)
            .object(object)
            .status(status)
            .build();
    }
}
