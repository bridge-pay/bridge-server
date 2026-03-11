package com.bridge.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Column
    private LocalDate birthDate;

    @Column
    private String nationality;

    @Column
    @Enumerated(EnumType.STRING)
    private Language language;

    @Column
    @Enumerated(EnumType.STRING)
    private Disabled disabled;
}
