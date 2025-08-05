package com.dual2024.projectcompetition.integration;

import com.dual2024.projectcompetition.presentation.dto.RegisterUserDTO;
import com.dual2024.projectcompetition.utils.UserState;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DomainUserIntegrationTest extends ContainerConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    private RegisterUserDTO registerUserDTO;


    @Order(1)
    @DisplayName("JUnit test for save a User")
    @Test
    public void givenUserObject_whenSave_thenReturnSavedUserObject() throws Exception {
        registerUserDTO = RegisterUserDTO.builder().email("test@email.com").nick("testUser").password("mysupersecret").build();
        ResultActions resultActions = mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerUserDTO)));

        resultActions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nick", is(registerUserDTO.getNick())))
                .andExpect(jsonPath("$.email", is(registerUserDTO.getEmail())));
    }

    @Order(2)
    @WithMockUser(username = "usuario1", roles = "ADMIN")
    @DisplayName("JUnit test for get all users")
    @Test
    public void givenNothing_whenGetUsers_thenReturnUserObjectList() throws Exception {


        ResultActions resultActions = mockMvc.perform(get("/user/all"));

        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(3)));
    }

    @Order(3)
    @WithMockUser(username = "usuario1", roles = "ADMIN")
    @DisplayName("JUnit test for get a user by his id")
    @Test
    public void givenUserId_whenGetById_thenReturnUserObject() throws Exception {

        ResultActions resultActions = mockMvc.perform(get("/user/{id}", 1));

        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nick", is("usuario1")))
                .andExpect(jsonPath("$.email", is("@Mail1")));
    }

    @Order(4)
    @WithMockUser(username = "usuario1", roles = "ADMIN")
    @DisplayName("JUnit test for get a user by his nick")
    @Test
    public void givenUserNick_whenGetByNick_thenReturnUserObject() throws Exception {


        ResultActions resultActions = mockMvc.perform(get("/user/nick/{nick}", "usuario1"));

        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nick", is("usuario1")))
                .andExpect(jsonPath("$.email", is("@Mail1")));
    }

    @Order(4)
    @WithMockUser(username = "usuario1", roles = "ADMIN")
    @DisplayName("JUnit test for get a user by his nick")
    @Test
    public void givenUserEmail_whenGetByEmail_thenReturnUserObject() throws Exception {


        ResultActions resultActions = mockMvc.perform(get("/user/email/{email}", "@Mail1"));

        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nick", is("usuario1")))
                .andExpect(jsonPath("$.email", is("@Mail1")));
    }

    @Order(5)
    @WithMockUser(username = "usuario1", roles = "ADMIN")
    @DisplayName("JUnit test for get users by a state")
    @Test
    public void givenUserState_whenGetState_thenReturnUserObjectList() throws Exception {


        ResultActions resultActions = mockMvc.perform(get("/user/state/{state}", UserState.CONNECTED));

        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(3)));
    }

    @Order(6)
    @WithMockUser(username = "usuario1", roles = "ADMIN")
    @DisplayName("JUnit test for delete a user")
    @Test
    public void givenUserId_whenDeleteUserDTO_thenDelete() throws Exception {


        ResultActions resultActions = mockMvc.perform(delete("/user/deletePlayer/{id}", 1));

        resultActions.andExpect(status().isAccepted())
                .andDo(print());
    }


}
