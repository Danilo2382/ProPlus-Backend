package org.springbootapp.proplus_backendapplication.repository;

import org.springbootapp.proplus_backendapplication.dto.response.ProjectMemberUsernameDto;
import org.springbootapp.proplus_backendapplication.model.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    Optional<ProjectMember> findByUser_UsernameAndProject_Id(String username, Long projectId);

    Boolean existsByUser_UsernameAndProject_Id(String username, Long projectId);

    @Query("SELECT pm FROM ProjectMember pm WHERE pm.project.id = :projectId AND pm.role = 2")
    ProjectMember findAdminByProjectId(Long projectId);

    @Query("SELECT NEW org.springbootapp.proplus_backendapplication.dto.response.ProjectMemberUsernameDto(pm, pm.user.username)" +
            "FROM ProjectMember pm WHERE pm.project.id = :projectId")
    Set<ProjectMemberUsernameDto> findAllWithUsernameByProject_Id(Long projectId);
}
