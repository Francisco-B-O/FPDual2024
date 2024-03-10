package com.Dual2024.ProjectCompetition.Presentation.Controller;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedEmailException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedNickException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.UserNotFoundException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBO;
import com.Dual2024.ProjectCompetition.Business.Service.Security.AuthenticationService;
import com.Dual2024.ProjectCompetition.Business.Service.UserService;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.Converters.BOToDTOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.Converters.DTOToBOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.RegisterUserDTO;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.RoleDTO;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.UpdateUserDTO;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.UserDTO;
import com.Dual2024.ProjectCompetition.Presentation.Exception.NotFoundException;
import com.Dual2024.ProjectCompetition.Presentation.Exception.PresentationException;
import com.Dual2024.ProjectCompetition.Utils.UserState;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The User controller.
 *
 * @author Franciosco Balonero Olivera
 */
@RequestMapping("user")
@RestController
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
     * Gets all users.
     *
     * @return All users
     * @throws PresentationException the presentation exception
     */
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
     * Gets user by id.
     *
     * @param id The id
     * @return The user by id
     * @throws PresentationException the presentation exception
     */
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
     * @param state The state
     * @return The users by state
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/state/{state}")
    public List<UserDTO> getUsersByState(@PathVariable("state") UserState state) throws PresentationException {
        List<UserDTO> listUserDTO = new ArrayList<UserDTO>();
        try {
            userService.getUsersByState(state)
                    .forEach((UserBO user) -> listUserDTO.add(boToDTOConverter.userBOToDTO(user)));
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
     * @param email the email
     * @return the user by email
     * @throws PresentationException the presentation exception
     */
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
     * @param nick the nick
     * @return the user by nick
     * @throws PresentationException the presentation exception
     */
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
     * Register user user dto.
     *
     * @param user the user
     * @return the user dto
     * @throws PresentationException the presentation exception
     */
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
     * Delete user.
     *
     * @param id the id
     */
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
     * Update user.
     *
     * @param user the user
     * @return the user dto
     * @throws PresentationException
     */
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
     * Update user role.
     *
     * @param id   The id
     * @param user The user
     * @return The user dto
     * @throws PresentationException
     */
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
