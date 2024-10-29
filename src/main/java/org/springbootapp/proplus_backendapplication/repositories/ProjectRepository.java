package org.springbootapp.proplus_backendapplication.repositories;

import org.springbootapp.proplus_backendapplication.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findById(Integer id);

    Optional<Project> findByCodeAndPassword(String code, String password);

    Optional<Project> findByIdAndPassword(Integer id, String password);

}
