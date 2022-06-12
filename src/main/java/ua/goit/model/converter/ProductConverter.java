package ua.goit.model.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.model.dao.ProductDao;
import ua.goit.model.dto.ProductDto;

@Service
public class ProductConverter {

    private final ProducerConverter producerConverter;

    @Autowired
    public ProductConverter(ProducerConverter producerConverter) {
        this.producerConverter = producerConverter;
    }

    public ProductDto convert(ProductDao dao) {
        ProductDto dto = new ProductDto();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        dto.setPrice(dao.getPrice());
        dto.setProducer(producerConverter.convert(dao.getProducer()));
        return dto;
    }

    public ProductDao convert(ProductDto dto) {
        ProductDao dao = new ProductDao();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        dao.setPrice(dto.getPrice());
        dao.setProducer(producerConverter.convert(dto.getProducer()));
        return dao;
    }
}
