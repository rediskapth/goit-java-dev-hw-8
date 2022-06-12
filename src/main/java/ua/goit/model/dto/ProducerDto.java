package ua.goit.model.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Set;
import java.util.UUID;

public class ProducerDto {
    private UUID id;
    private String name;
    private Set<ProductDto> products;

    public ProducerDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @NotEmpty(message = "Please, enter Producer Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDto> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return id +
                "," + name;
    }
}
