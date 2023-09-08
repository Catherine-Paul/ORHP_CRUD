package com.orhp.crud;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orhp.crud.controllers.UserController;
import com.orhp.crud.entitites.Course;
import com.orhp.crud.entitites.User;
import com.orhp.crud.repositories.CourseRepository;
import com.orhp.crud.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private UserController userController;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void contextLoads() {
        Assertions
                .assertThat(userController)
                .isNotNull();
        Assertions
                .assertThat(userRepository)
                .isNotNull();
        Assertions
                .assertThat(courseRepository)
                .isNotNull();
    }

    @Test
    public void givenGetUsersApiCall_whenUserListRetrieved_thenSizeMatchAndListContainsUserNames() throws Exception {
        ResponseEntity<Iterable<User>> responseEntity = restTemplate.exchange("http://localhost:" + port + "/users", HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<User>>() {
        });
        Iterable<User> users = responseEntity.getBody();
        Assertions
                .assertThat(users)
                .hasSize(5);
        assertThat(users, hasItem(hasProperty("name", is("John"))));
        assertThat(users, hasItem(hasProperty("name", is("Julie"))));
        assertThat(users, hasItem(hasProperty("name", is("Jennifer"))));
        assertThat(users, hasItem(hasProperty("name", is("Helen"))));
        assertThat(users, hasItem(hasProperty("name", is("Rachel"))));

    }


    @Test
    public void whenFindByName_thenReturnUser() {
        // given
        User alex = new User("Alex", "alex@domain.com");
        alex.setCourses(new HashSet<Course>());
        userRepository.save(alex);
        // when
        User found = userRepository.findByName(alex.getName());
        // then
        Assertions
                .assertThat(found.getName()).isEqualTo("Alex");

        Assertions
                .assertThat(userRepository.findAll())
                .hasSize(6);


    }


}
