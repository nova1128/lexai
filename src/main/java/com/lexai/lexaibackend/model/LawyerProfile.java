package com.lexai.lexaibackend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Data
@Table(name="LawyerProfile")

public class LawyerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String specialization;

    private Integer experience;

    private Double rating;

    private Boolean available;

    @Enumerated(EnumType.STRING)
    private User.Role role = User.Role.LAWYER;
}
