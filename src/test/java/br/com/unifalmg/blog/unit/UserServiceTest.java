package br.com.unifalmg.blog.unit;

import br.com.unifalmg.blog.entity.User;
import br.com.unifalmg.blog.exception.UserNotFoundException;
import br.com.unifalmg.blog.repository.UserRepository;
import br.com.unifalmg.blog.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;
    @Test
    @DisplayName("#findById > When the id is null > Throw an exception")
    void test1() {
        assertThrows(IllegalArgumentException.class, () ->
                service.findById(null));
    }


    @Test
    @DisplayName("#findById > When the id is not null > When a user is found > Return the user")
    void test2() {
        when(repository.findById(1)).thenReturn(Optional.of(User.builder()
                    .id(1)
                    .name("Tiago")
                    .username("Tiago")
                .build()));
        User response = service.findById(1);
        assertAll(
                () -> assertEquals(1, response.getId()),
                () -> assertEquals("Tiago", response.getName()),
                () -> assertEquals("Tiago", response.getUsername())
        );
    }

    @Test
    @DisplayName("findById > When the id is not null > When no user is found > Throw an exception")
    void test3() {
        when(repository.findById(2)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () ->
                service.findById(2));
    }


    // TODO: Implement test cases for getAllUsers
    @Test
    @DisplayName("getAllUsers > When the repository is empty > Return a empty list")
    void getAllUsersWhenTheRepositoryIsEmptyReturnAEmptyList() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<User> result = service.getAllUsers();

        assertTrue(result.isEmpty());
    }


    @Test
    @DisplayName("getAllUsers > When all fields is corrects > Return the users")
    void getAllUsersWhenAllFieldsIsCorrectsReturnTheUsers() {
        when(repository.findAll()).thenReturn(Arrays.asList(
                User.builder()
                    .id(1)
                    .name("Tiago")
                    .username("Tiago")
                    .build(),
                User.builder()
                     .id(2)
                     .name("Luiz")
                     .username("Luiz")
                    .build()));

        List<User> userList = service.getAllUsers();

        assertAll(
                () -> assertEquals(2, userList.size()),
                () -> assertEquals("Tiago", userList.get(0).getName()),
                () -> assertEquals("Tiago", userList.get(0).getUsername()),
                () -> assertEquals("Luiz", userList.get(1).getName()));
    }
}

