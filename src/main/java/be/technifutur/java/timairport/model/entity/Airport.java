package be.technifutur.java.timairport.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String country;

    private String city;

    private String address;

    @Column(name = "plane_parking",nullable = false)
    private int planeParking;

    @ManyToMany
    @JoinTable(
            name = "airport_planes_allowed",
            joinColumns = @JoinColumn(name = "airport_id"),
            inverseJoinColumns = @JoinColumn(name = "type_plane_id")
    )
    private List<TypePlane> planeTypesAllowed;



}
