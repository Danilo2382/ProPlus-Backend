package org.springbootapp.proplus_backendapplication.repository;

import org.springbootapp.proplus_backendapplication.dto.response.TaskUsernameDto;
import org.springbootapp.proplus_backendapplication.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findTaskById(Long id);

    Optional<Task> findByProjectMember_User_UsernameAndId(String projectMemberUserUsername, Long id);

    Set<Task> findAllByProjectMember_User_Username(String username);

    @Query("SELECT NEW org.springbootapp.proplus_backendapplication.dto.response.TaskUsernameDto(t, pm.user.username)" +
            "FROM Task t JOIN t.projectMember pm WHERE pm.project.id = :projectId")
    Set<TaskUsernameDto> findAllWithUsernameByProject_Id(Long projectId);
}
