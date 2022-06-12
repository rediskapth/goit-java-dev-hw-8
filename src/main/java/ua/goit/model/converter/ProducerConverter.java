package ua.goit.model.converter;

import org.springframework.stereotype.Service;
import ua.goit.model.dao.ProducerDao;
import ua.goit.model.dto.ProducerDto;

@Service
public class ProducerConverter {

    public ProducerDto convert(ProducerDao dao) {
        ProducerDto dto = new ProducerDto();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        return dto;
    }

    public ProducerDao convert(ProducerDto dto) {
        ProducerDao dao = new ProducerDao();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        return dao;
    }
}
