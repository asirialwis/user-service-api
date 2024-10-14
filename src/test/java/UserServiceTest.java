import com.example.crudapp.dto.UserRequest;
import com.example.crudapp.dto.UserResponse;
import com.example.crudapp.model.User;
import com.example.crudapp.repository.UserRepository;
import com.example.crudapp.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetUserById_WhenUserExists() {
        // Arrange
        User user = new User("1", "John Doe", "john@example.com", 30);
        Mockito.when(userRepository.findById("1")).thenReturn(Optional.of(user));

        // Act
        UserResponse userResponse = userService.findUserById("1");

        // Assert
        Assertions.assertNotNull(userResponse);
        Assertions.assertEquals("John Doe", userResponse.getName());
    }




    @Test
    void shouldThrowException_WhenUserNotFound() {
        // Arrange: Simulate that the user is not found in the repository.
        Mockito.when(userRepository.findById("1")).thenReturn(Optional.empty());

        // Act & Assert: Check that ResponseStatusException is thrown when user is not found.
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            userService.findUserById("1");
        });

        // Assert: Verify that the exception message is "User Not Found".
        Assertions.assertEquals("User Not Found", exception.getReason());
    }


    @Test
    void shouldCreateUser() {
        // Arrange
        UserRequest userRequest = new UserRequest("Jane Doe", "jane@example.com", 25);
        User user = User.builder().name("Jane Doe").email("jane@example.com").age(25).build();

        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);

        // Act
        userService.createUser(userRequest);

        // Assert
        Mockito.verify(userRepository, Mockito.times(1)).save(ArgumentMatchers.any(User.class));
    }

    @Test
    void shouldUpdateUser_WhenUserExists() {
        // Arrange
        User existingUser = new User("1", "John Doe", "john@example.com", 30);
        UserRequest updateRequest = new UserRequest("John Updated", "johnupdated@example.com", 35);

        Mockito.when(userRepository.findById("1")).thenReturn(Optional.of(existingUser));

        // Act
        userService.updateUser("1", updateRequest);

        // Assert
        Mockito.verify(userRepository, Mockito.times(1)).save(ArgumentMatchers.any(User.class));
    }

    @Test
    void shouldDeleteUser_WhenUserExists() {
        // Act
        userService.deleteUser("1");

        // Assert
        Mockito.verify(userRepository, Mockito.times(1)).deleteById("1");
    }
}
