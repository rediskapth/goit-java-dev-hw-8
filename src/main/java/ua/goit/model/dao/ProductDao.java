package ua.goit.model.dao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductDao {
    private UUID id;
    private String name;
    private BigDecimal price;
    private ProducerDao producer;

    public ProductDao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producer_id")
    public ProducerDao getProducer() {
        return producer;
    }

    public void setProducer(ProducerDao producer) {
        this.producer = producer;
    }
}
