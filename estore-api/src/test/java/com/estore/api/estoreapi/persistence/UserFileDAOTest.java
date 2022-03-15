package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.estore.api.estoreapi.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Persistence-tire")
public class UserFileDAOTest {
    
    //CuT
    private UserFileDAO userFileDAO;

    private ObjectMapper mockObjectMapper;

    private User[] testUsers;

    @BeforeEach
    void setup() throws IOException{
        mockObjectMapper = mock(ObjectMapper.class);
        testUsers = new User[2];
        testUsers[0] = new User(0, "admin");
        testUsers[1] = new User(1, "user");

        when(mockObjectMapper.readValue(new File ("randomFile.txt"), User[].class)).thenReturn(testUsers);
        userFileDAO = new UserFileDAO("randomFile.txt", mockObjectMapper);
    }

    @Test
    public void testGetUser() throws IOException {
        User user = userFileDAO.getUser("user");
        assertNotNull(user);
        assertEquals(testUsers[1], user);
    }

    @Test
    public void testCreateUser() throws IOException {
        User user = userFileDAO.createUser(new User(2, "user2"));
        User result = assertDoesNotThrow(() -> userFileDAO.createUser(user), "Unhandled!");
        assertNotNull(result);

        User actual = userFileDAO.getUser("user2");
        assertEquals(result.getId(), actual.getId());
        assertEquals(result.getName(), actual.getName());
    }
}
