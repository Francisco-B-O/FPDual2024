package com.Dual2024.ProjectCompetition.Presentation.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedEmailException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedNickException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.UserInActiveTournamentException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.UserNotFoundException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.RoleBO;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.UserBO;
import com.Dual2024.ProjectCompetition.Business.Service.UserService;
import com.Dual2024.ProjectCompetition.DataAccess.Model.UserState;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.BOToDTOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.DTOToBOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.RegisterUserDTO;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.RoleDTO;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.UpdateUserDTO;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.UserDTO;
import com.Dual2024.ProjectCompetition.Presentation.Exception.Body;
import com.Dual2024.ProjectCompetition.Presentation.Exception.NotFoundException;
import com.Dual2024.ProjectCompetition.Presentation.Exception.PresentationException;

import jakarta.validation.Valid;

@RequestMapping("user")
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	BOToDTOConverter boToDTOConverter;
	@Autowired
	DTOToBOConverter dtoToBOConverter;

	@GetMapping("/all")
	public List<UserDTO> getAllUsers() throws PresentationException {
		List<UserDTO> listUserBO = new ArrayList<UserDTO>();
		try {
			userService.getAllUsers().forEach((UserBO user) -> listUserBO.add(boToDTOConverter.userBOToDTO(user)));
			return listUserBO;
		} catch (UserNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}
	}

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

	@GetMapping("/state/{state}")
	public List<UserDTO> getUserByState(@PathVariable("state") UserState state) throws PresentationException {
		List<UserDTO> listUserBO = new ArrayList<UserDTO>();
		try {
			userService.getUsersByState(state)
					.forEach((UserBO user) -> listUserBO.add(boToDTOConverter.userBOToDTO(user)));
			return listUserBO;
		} catch (UserNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}

	}

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

	@PostMapping("/register")
	public UserDTO registerUser(@RequestBody @Valid RegisterUserDTO user) throws PresentationException {
		try {
			return boToDTOConverter.userBOToDTO(userService.registerUser(dtoToBOConverter.RegisterUserDTOToBO(user)));
		} catch (DuplicatedEmailException e) {
			throw new PresentationException("That email is already in use");
		} catch (DuplicatedNickException e) {
			throw new PresentationException("That nick is already in use");
		} catch (BusinessException e) {
			throw new PresentationException("Unregistered user, please check that the required fields are complete", e);
		}

	}

	@DeleteMapping("/delete")
	public ResponseEntity<Body> deleteUser(@RequestParam Long id) {
		try {
			userService.deleteUser(id);
			Body msg = new Body(HttpStatus.ACCEPTED, "user deleted");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(msg);
		} catch (UserNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (UserInActiveTournamentException e) {
			throw new PresentationException(e.getMessage(), e);
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}
	}

	@PutMapping("/update")
	public UserDTO updateUser(@RequestParam long id, @RequestBody @Valid UpdateUserDTO user)
			throws PresentationException {
		try {
			return boToDTOConverter.userBOToDTO(userService.UpdateUser(id, user.getAvatar(), user.getPassword()));
		} catch (UserNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}
	}

	@PutMapping("/admin/update")
	public UserDTO updateUserRole(@RequestParam long id, @RequestBody @Valid UpdateUserDTO user)
			throws PresentationException {
		List<RoleBO> roles = new ArrayList<RoleBO>();
		user.getRoles().forEach((RoleDTO role) -> roles.add(dtoToBOConverter.roleDTOToBO(role)));
		try {
			return boToDTOConverter.userBOToDTO(userService.UpdateRoleUser(id, roles));
		} catch (UserNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		} catch (BusinessException e) {
			throw new PresentationException(e.getMessage(), e);
		}
	}
}
