package com.dual2024.projectcompetition.integration;

import com.dual2024.projectcompetition.presentation.dto.RegisterFormatDTO;
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
public class DomainFormatIntegrationTest extends ContainerConfig {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Order(1)
    @DisplayName("JUnit test for get all formats")
    @WithMockUser(username = "usuario1", roles = "ADMIN")
    @Test
    public void givenNothing_whenGetformats_thenReturnListformatObject() throws Exception {


        ResultActions resultActions = mockMvc.perform(get("/format/all"));

        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(2)));
    }

    @Order(2)
    @DisplayName("JUnit test for save a format")
    @WithMockUser(username = "usuario1", roles = "ADMIN")
    @Test
    public void givenformatObject_whenSaveformatDTO_thenReturnSavedformatObject() throws Exception {
        RegisterFormatDTO formatDTO = RegisterFormatDTO.builder().name("futbolin").build();
        ResultActions resultActions = mockMvc.perform(post("/format/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(formatDTO)));

        resultActions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("futbolin")));

    }

    @Order(3)
    @DisplayName("JUnit test for delete a format")
    @WithMockUser(username = "usuario1", roles = "ADMIN")
    @Test
    public void givenformatId_whenDeleteformatDTO_thenDelete() throws Exception {


        ResultActions resultActions = mockMvc.perform(delete("/format/delete/{id}", 1));

        resultActions.andExpect(status().isAccepted())
                .andDo(print());
    }


}
