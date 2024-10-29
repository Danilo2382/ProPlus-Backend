package org.springbootapp.proplus_backendapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Size(max = 45)
    @NotNull
    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Size(max = 500)
    @NotNull
    @Column(name = "password", nullable = false, length = 500)
    private String password;

    @Size(max = 45)
    @NotNull
    @Column(name = "firstName", nullable = false, length = 45)
    private String firstName;

    @Size(max = 45)
    @NotNull
    @Column(name = "secondName", nullable = false, length = 45)
    private String secondName;

    @NotNull
    @Column(name = "birthday", nullable = false)
    private LocalDateTime birthday;

    @NotNull
    @Column(name = "joinDate", nullable = false)
    private LocalDateTime joinDate;

    @Column(name = "profilePicture")
    private String profilePicture;

    @OneToMany(mappedBy = "userIduser")
    private Set<ProjectMember> projectmembers = new LinkedHashSet<>();

}