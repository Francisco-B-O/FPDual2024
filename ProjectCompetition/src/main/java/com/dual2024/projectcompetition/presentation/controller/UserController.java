package com.dual2024.projectcompetition.presentation.controller;

import com.dual2024.projectcompetition.business.businessexception.BusinessException;
import com.dual2024.projectcompetition.business.businessexception.DuplicatedEmailException;
import com.dual2024.projectcompetition.business.businessexception.DuplicatedNickException;
import com.dual2024.projectcompetition.business.businessexception.UserNotFoundException;
import com.dual2024.projectcompetition.business.businessobject.RoleBO;
import com.dual2024.projectcompetition.business.businessobject.UserBO;
import com.dual2024.projectcompetition.business.service.UserService;
import com.dual2024.projectcompetition.business.service.security.AuthenticationService;
import com.dual2024.projectcompetition.presentation.dto.RegisterUserDTO;
import com.dual2024.projectcompetition.presentation.dto.RoleDTO;
import com.dual2024.projectcompetition.presentation.dto.UpdateUserDTO;
import com.dual2024.projectcompetition.presentation.dto.UserDTO;
import com.dual2024.projectcompetition.presentation.dto.converters.BOToDTOConverter;
import com.dual2024.projectcompetition.presentation.dto.converters.DTOToBOConverter;
import com.dual2024.projectcompetition.presentation.exception.NotFoundException;
import com.dual2024.projectcompetition.presentation.exception.PresentationException;
import com.dual2024.projectcompetition.utils.UserState;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for managing users.
 *
 * <p>This class defines RESTful endpoints for performing CRUD operations on users. It handles
 * requests related to retrieving all users, retrieving a user by ID, retrieving users by state,
 * retrieving a user by email or nick, registering new users, updating user details, deleting users,
 * and updating user roles. The endpoints are secured, and only authorized users with specific roles
 * can access them.</p>
 *
 * @author Franciosco Balonero Olivera
 * @see UserService
 * @see BOToDTOConverter
 * @see DTOToBOConverter
 * @see AuthenticationService
 */
@RequestMapping("user")
@RestController
@Tag(name = "User", description = "Operations related to users management")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * The Bo to dto converter.
     */
    @Autowired
    BOToDTOConverter boToDTOConverter;

    /**
     * The Dto to bo converter.
     */
    @Autowired
    DTOToBOConverter dtoToBOConverter;

    /**
     * The Password encoder.
     */
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * The Authentication service.
     */
    @Autowired
    AuthenticationService authenticationService;

    /**
     * Retrieves all users.
     *
     * @return List of all users
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Get all users")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/all")
    public List<UserDTO> getAllUsers() throws PresentationException {
        List<UserDTO> listUserDTO = new ArrayList<UserDTO>();
        try {
            userService.getAllUsers().forEach((UserBO user) -> listUserDTO.add(boToDTOConverter.userBOToDTO(user)));
            return listUserDTO;
        } catch (UserNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Gets user by ID.
     *
     * @param id The ID of the user
     * @return The user with the specified ID
     * @throws PresentationException if an error occurs during presentation
     */

    @Operation(summary = "Get user by ID")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") Long id) throws PresentationException {
        try {
            return boToDTOConverter.userBOToDTO(userService.getUser(id));
        } catch (UserNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Gets users by state.
     *
     * @param state The state of the users
     * @return List of users with the specified state
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Get users by state")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/state/{state}")
    public List<UserDTO> getUsersByState(@PathVariable("state") UserState state) throws PresentationException {
        List<UserDTO> listUserDTO = new ArrayList<UserDTO>();
        try {
            userService.getUsersByState(state).forEach((UserBO user) -> listUserDTO.add(boToDTOConverter.userBOToDTO(user)));
            return listUserDTO;
        } catch (UserNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Gets user by email.
     *
     * @param email The email of the user
     * @return The user with the specified email
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Get user by email")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/email/{email}")
    public UserDTO getUserByEmail(@PathVariable("email") String email) throws PresentationException {
        try {
            return boToDTOConverter.userBOToDTO(userService.getUserByEmail(email));
        } catch (UserNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Gets user by nick.
     *
     * @param nick The nickname of the user
     * @return The user with the specified nickname
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Get user by nickname")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/nick/{nick}")
    public UserDTO getUserByNick(@PathVariable("nick") String nick) throws PresentationException {
        try {
            return boToDTOConverter.userBOToDTO(userService.getUserByNick(nick));
        } catch (UserNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Register a new user.
     *
     * @param user The user to be registered
     * @return The registered user
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Register a new user")
    @PreAuthorize("permitAll")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody @Valid RegisterUserDTO user) throws PresentationException {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return boToDTOConverter.userBOToDTO(userService.registerUser(dtoToBOConverter.RegisterUserDTOToBO(user)));
        } catch (DuplicatedEmailException | DuplicatedNickException e) {
            throw new PresentationException(e.getMessage());
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Deletes a user.
     *
     * @param id The ID of the user to be deleted
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Delete a user")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR')")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @DeleteMapping("/deletePlayer/{id}")
    public void deleteUser(@PathVariable long id) {
        try {
            userService.deleteUser(id);
        } catch (UserNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Updates a user.
     *
     * @param user The updated user information
     * @return The updated user
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Update a user")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PutMapping("/update/")
    public UserDTO updateUser(@RequestBody @Valid UpdateUserDTO user) throws PresentationException {
        try {
            Long id = authenticationService.getUserAuthenticated();
            return boToDTOConverter.userBOToDTO(userService.updateUser(id, user.getAvatar(), user.getPassword()));
        } catch (UserNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Updates the role of a user.
     *
     * @param id   The ID of the user
     * @param user The updated user information
     * @return The updated user with the new role
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Update a user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PutMapping("/admin/update/{id}")
    public UserDTO updateUserRole(@PathVariable long id, @RequestBody @Valid UpdateUserDTO user)
            throws PresentationException {
        List<RoleBO> roles = new ArrayList<RoleBO>();
        user.getRoles().forEach((RoleDTO role) -> roles.add(dtoToBOConverter.roleDTOToBO(role)));
        try {
            return boToDTOConverter.userBOToDTO(userService.updateRoleUser(id, roles));
        } catch (UserNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }
}