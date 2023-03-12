package my.assignment;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import lombok.SneakyThrows;
import my.assignment.dto.NotebookCreateOrUpdateRequest;
import my.assignment.util.AbstractTestController;
import my.assignment.util.IntegrationTest;
import my.assignment.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@IntegrationTest
@AutoConfigureMockMvc(addFilters = false)
class NotebookControllerIT extends AbstractTestController {
    private static final String POST_NOTEBOOK_PATH = "/notebooks";

    @SneakyThrows
    @Test
    void postNotebook_shouldReturnClientError() {
        var notebook = objectMapper.readValue(
            TestUtils.getResourceFileAsString("stub/json/notebook.json"),
            NotebookCreateOrUpdateRequest.class);

        //given
        var request = preparePostRequest(POST_NOTEBOOK_PATH, notebook);

        //then
        mockMvc.perform(request).andExpect(status().is4xxClientError());
    }
}
