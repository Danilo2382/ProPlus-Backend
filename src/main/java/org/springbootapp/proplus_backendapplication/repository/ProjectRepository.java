package org.springbootapp.proplus_backendapplication.repository;

import org.springbootapp.proplus_backendapplication.dto.response.ProjectAdminRoleDto;
import org.springbootapp.proplus_backendapplication.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByCode(String code);

    Optional<Project> findByProjectMembers_User_UsernameAndId(String username, Long id);

    @Query("SELECT NEW org.springbootapp.proplus_backendapplication.dto.response.ProjectAdminRoleDto(" +
            "p.id, p.name, p.status, uAdmin.profilePicture, uAdmin.username, pm.role) " +
            "FROM Project p " +
            "JOIN ProjectMember pm ON pm.project.id = p.id " +
            "JOIN User u ON pm.user.id = u.id " +
            "JOIN ProjectMember pmAdmin ON pmAdmin.project.id = p.id AND pmAdmin.role = 2 " +
            "JOIN User uAdmin ON pmAdmin.user.id = uAdmin.id " +
            "WHERE u.username = :username")
    Set<ProjectAdminRoleDto> findAllWithAdminAndRoleByUsername(String username);
}
