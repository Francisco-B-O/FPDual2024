package com.dual2024.projectcompetition.controller;

import com.dual2024.projectcompetition.business.service.UserService;
import com.dual2024.projectcompetition.business.service.security.AuthenticationService;
import com.dual2024.projectcompetition.business.service.security.JwtService;
import com.dual2024.projectcompetition.dataaccess.dao.UserDAO;
import com.dual2024.projectcompetition.presentation.controller.UserController;
import com.dual2024.projectcompetition.presentation.dto.converters.BOToDTOConverter;
import com.dual2024.projectcompetition.presentation.dto.converters.DTOToBOConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;


@WebMvcTest(UserController.class)
@ComponentScan
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private UserService userService;
    @MockBean
    private UserDAO userDAO;
    @MockBean
    private DTOToBOConverter dtoToBOConverter;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private AuthenticationService authenticationService;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    BOToDTOConverter boToDTOConverter;

    public String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
//    public void prueba() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        RegisterUserDTO userRegister = RegisterUserDTO.builder().email("test@email.com").nick("test").password("atleticode").avatar("logo.png").build();
//        UserBO userBO = UserBO.builder().id(1L).email("test@email.com").nick("test").password("atleticode").avatar("logo.png").build();
//        UserDTO userDTO = UserDTO.builder().id(1L).email("test@email.com").nick("test").password("atleticode").avatar("logo.png").build();
//        BDDMockito.given(userService.registerUser(userBO)).willReturn(userBO);
//        BDDMockito.given(boToDTOConverter.userBOToDTO(userBO)).willReturn(userDTO);
//        BDDMockito.given(dtoToBOConverter.userDTOToBO(userDTO)).willReturn(userBO);
//        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(userRegister)))
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.view());
//
//    }
}
