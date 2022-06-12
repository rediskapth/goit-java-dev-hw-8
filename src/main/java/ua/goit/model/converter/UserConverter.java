package ua.goit.model.converter;

import org.springframework.stereotype.Service;
import ua.goit.model.dao.UserDao;
import ua.goit.model.dto.UserDto;

@Service
public class UserConverter {

    public UserDto convert(UserDao dao) {
        UserDto dto = new UserDto();
        dto.setId(dao.getId());
        dto.setEmail(dao.getEmail());
        dto.setPassword(dao.getPassword());
        dto.setFirstName(dao.getFirstName());
        dto.setLastName(dao.getLastName());
        dto.setUserRole(dao.getUserRole());
        return dto;
    }

    public UserDao convert(UserDto dto) {
        UserDao dao = new UserDao();
        dao.setId(dto.getId());
        dao.setEmail(dto.getEmail());
        dao.setPassword(dto.getPassword());
        dao.setFirstName(dto.getFirstName());
        dao.setLastName(dto.getLastName());
        dao.setUserRole(dto.getUserRole());
        return dao;
    }
}
