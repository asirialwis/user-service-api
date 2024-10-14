

import com.example.crudapp.controller.UserController;
import com.example.crudapp.dto.UserRequest;
import com.example.crudapp.dto.UserResponse;
import com.example.crudapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@ContextConfiguration(classes = {UserController.class, UserService.class})
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void shouldGetAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateUser() throws Exception {
        UserRequest userRequest = new UserRequest("Jane", "jane@example.com", 25);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Jane\", \"email\": \"jane@example.com\", \"age\": 25 }"))
                .andExpect(status().isOk());
    }
}
