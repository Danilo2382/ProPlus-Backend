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
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTask", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Size(max = 200)
    @NotNull
    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @NotNull
    @Column(name = "createDate", nullable = false)
    private LocalDateTime createDate;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "attachment")
    private byte[] attachment;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Project_idProject", nullable = false)
    private Project projectIdproject;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ProjectMember_idProjectMember", nullable = false)
    private ProjectMember projectmemberIdprojectmember;

    @OneToMany(mappedBy = "taskIdtask")
    private Set<Comment> comments = new LinkedHashSet<>();

}