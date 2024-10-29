package org.springbootapp.proplus_backendapplication.repositories;

import org.springbootapp.proplus_backendapplication.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findTaskById(Integer id);
}
