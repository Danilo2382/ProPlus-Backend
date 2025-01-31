package org.springbootapp.proplus_backendapplication.repository;

import org.springbootapp.proplus_backendapplication.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByCode(String code);

    Optional<Project> findByProjectMembers_User_UsernameAndId(String username, Long id);

    Set<Project> findAllByProjectMembers_User_Username(String username);

}
