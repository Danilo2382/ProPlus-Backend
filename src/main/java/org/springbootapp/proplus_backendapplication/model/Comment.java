package org.springbootapp.proplus_backendapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComment", nullable = false)
    private Integer id;

    @Size(max = 200)
    @NotNull
    @Column(name = "text", nullable = false, length = 200)
    private String text;

    @NotNull
    @Column(name = "timeStamp", nullable = false)
    private LocalDateTime timeStamp;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Task_idTask", nullable = false)
    private Task taskIdtask;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ProjectMember_idProjectMember", nullable = false)
    private ProjectMember projectmemberIdprojectmember;

}