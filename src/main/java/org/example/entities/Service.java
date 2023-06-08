package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "service")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Service {
    @Id
    @SequenceGenerator(name = "services_seq", sequenceName =
            "services_sequence", allocationSize = 1)
    @GeneratedValue(generator = "services_seq", strategy =
            GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Integer price;

    @OneToMany(mappedBy = "service")
    private List<Application> applications;

    @Override
    public String toString() {
        return name;
    }
}
