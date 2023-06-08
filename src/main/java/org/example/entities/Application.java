package org.example.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "application")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Application {
    @Id
    @SequenceGenerator(name = "apps_seq", sequenceName =
            "apps_sequence", allocationSize = 1)
    @GeneratedValue(generator = "apps_seq", strategy =
            GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "date")
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    public User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    @JsonIgnore
    public Service service;

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
