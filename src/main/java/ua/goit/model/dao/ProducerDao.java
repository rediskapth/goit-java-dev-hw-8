package ua.goit.model.dao;


import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "producers")
public class ProducerDao {
    private UUID id;
    private String name;
    private Set<ProductDao> products;

    public ProducerDao() {
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

    @OneToMany(mappedBy = "producer", fetch = FetchType.EAGER)
    public Set<ProductDao> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDao> products) {
        this.products = products;
    }
}
