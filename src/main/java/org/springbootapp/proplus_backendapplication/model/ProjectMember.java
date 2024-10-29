package org.springbootapp.proplus_backendapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "projectmember")
public class ProjectMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProjectMember", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "role", nullable = false)
    private Integer role;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "User_idUser", nullable = false)
    private User userIduser;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Project_idProject", nullable = false)
    private Project projectIdproject;

    @OneToMany(mappedBy = "projectmemberIdprojectmember")
    private Set<Comment> comments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "projectmemberIdprojectmember")
    private Set<Task> tasks = new LinkedHashSet<>();

}