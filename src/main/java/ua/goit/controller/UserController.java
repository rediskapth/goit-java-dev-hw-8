package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.exception.UserAlreadyExistsException;
import ua.goit.model.UserRole;
import ua.goit.model.dto.UserDto;
import ua.goit.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(path = "/registration")
    public String getRegistrationForm() {
        return "registration";
    }

    @PostMapping(path = "/registration")
    public String registerUser(@ModelAttribute("userDto") @Valid UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        try {
            userDto.setUserRole(UserRole.ROLE_USER);
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userService.saveOrUpdate(userDto);
        } catch (UserAlreadyExistsException ex) {
            model.addAttribute("message", ex.getMessage());
            return "registration";
        }
        return "login";
    }

    @ModelAttribute
    public UserDto getDefaultUserDto() {
        return new UserDto();
    }

    @GetMapping(path = "/findUsers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String findAllUsers(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "findUsers";
    }

    @GetMapping(path = "/form/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createUserForm(Model model) {
        List<UserRole> userRoles = List.of(UserRole.values());
        model.addAttribute("userRoles", userRoles);
        return "createUserForm";
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView createUser(@ModelAttribute("userDto") @Valid UserDto userDto, BindingResult bindingResult,
                                   ModelAndView model) {
        if (bindingResult.hasErrors()) {
            List<UserRole> userRoles = List.of(UserRole.values());
            model.addObject("userRoles", userRoles);
            model.setViewName("createUserForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        try {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userService.saveOrUpdate(userDto);
            model.setViewName("createUser");
            model.setStatus(HttpStatus.CREATED);
        } catch (UserAlreadyExistsException ex) {
            List<UserRole> userRoles = List.of(UserRole.values());
            model.addObject("userRoles", userRoles);
            model.addObject("message", ex.getMessage());
            model.setViewName("createUserForm");
        }
        return model;
    }

    @GetMapping(path = "/form/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateUserForm(@PathVariable("id") UUID id, Model model) {
        List<UserRole> userRoles = List.of(UserRole.values());
        UserDto userDto = userService.findUserById(id);
        model.addAttribute("userDto", userDto);
        model.addAttribute("userRoles", userRoles);
        return "updateUserForm";
    }

    @PostMapping(path = "/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateUser(@PathVariable("id") UUID id,
                                   @ModelAttribute("userDto") @Valid UserDto userDto, BindingResult bindingResult,
                                   Model model) {
        if (bindingResult.hasErrors()) {
            List<UserRole> userRoles = List.of(UserRole.values());
            model.addAttribute("userRoles", userRoles);
            return "updateUserForm";
        }
        try {
            UserDto userToUpdate = userService.findUserById(id);
            userToUpdate.setFirstName(userDto.getFirstName());
            userToUpdate.setLastName(userDto.getLastName());
            userToUpdate.setEmail(userDto.getEmail());
            userToUpdate.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userToUpdate.setUserRole(userDto.getUserRole());
            userToUpdate.setId(userDto.getId());
            if (userDto.getEmail().equals("admin@admin")) {
                throw new Exception("User admin@admin is superadmin. You can't update it.");
            }
            if (userToUpdate.getEmail().equals(userDto.getEmail())) {
                userService.delete(userDto);
            }
            userService.saveOrUpdate(userToUpdate);
        } catch (UserAlreadyExistsException ex) {
            List<UserRole> userRoles = List.of(UserRole.values());
            model.addAttribute("userRoles", userRoles);
            model.addAttribute("message", ex.getMessage());
            return "updateUserForm";
        } catch (Exception ex) {
            model.addAttribute("message", ex.getMessage());
            List<UserDto> users = userService.findAllUsers();
            model.addAttribute("users", users);
            return "findUsers";
        }
        return "updateUser";
    }

    @GetMapping(path = "/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUserByEmail(@PathVariable("id") UUID id, Model model) {
        UserDto userDto = userService.findUserById(id);
        try {
            if (userDto.getEmail().equals("admin@admin")) {
                throw new Exception();
            }
            userService.delete(userDto);
        } catch (Exception ex) {
            model.addAttribute("message", "User admin@admin is superadmin. You can't delete it.");
            List<UserDto> users = userService.findAllUsers();
            model.addAttribute("users", users);
            return "findUsers";
        }
        return "deleteUser";
    }
}
