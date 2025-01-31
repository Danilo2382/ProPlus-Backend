package org.springbootapp.proplus_backendapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class ProjectMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Integer role;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user", nullable = false)
    @JsonIgnore
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project", nullable = false)
    @JsonIgnore
    private Project project;

    @OneToMany(mappedBy = "projectMember")
    @JsonIgnore
    private Set<Comment> comments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "projectMember")
    @JsonIgnore
    private Set<Task> tasks = new LinkedHashSet<>();

}