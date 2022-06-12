package ua.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.goit.exception.UserAlreadyExistsException;
import ua.goit.model.converter.UserConverter;
import ua.goit.model.dto.UserDto;
import ua.goit.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public List<UserDto> findAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(userConverter::convert)
                .collect(Collectors.toList());
    }

    public UserDto findUserByEmail(String email) {
        return userConverter.convert(userRepository.findUserByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with username %s not exists", email))));
    }

    public UserDto findUserById(UUID id) {
        return userConverter.convert(userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with id %s not exists", id))));
    }

    public void saveOrUpdate(UserDto userDto) {
        validateToCreateUser(userDto);
        userRepository.save(userConverter.convert(userDto));
    }

    public void delete(UserDto userDto) {
        userRepository.delete(userConverter.convert(userDto));
    }

    public void validateToCreateUser(UserDto userDto) {
        userRepository.findUserByEmail(userDto.getEmail())
                .ifPresent((user) -> {
                    throw new UserAlreadyExistsException("User with username " + user.getEmail() +
                            " already exists");
                });
    }
}
