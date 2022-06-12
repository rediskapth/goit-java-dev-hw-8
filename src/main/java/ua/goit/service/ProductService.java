package ua.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.exception.ProductNotFoundException;
import ua.goit.model.converter.ProductConverter;
import ua.goit.model.dto.ProductDto;
import ua.goit.repository.ProductRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    public List<ProductDto> findAllProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(productConverter::convert)
                .collect(Collectors.toList());
    }

    public ProductDto findProductById(UUID id) {
        return productConverter.convert(productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product with id %s not exists", id)));
    }

    public void saveOrUpdate(ProductDto productDto) {
        productRepository.save(productConverter.convert(productDto));
    }

    public void delete(ProductDto productDto) {
        productRepository.delete(productConverter.convert(productDto));
    }
}
