package org.springbootapp.proplus_backendapplication.repositories;

import org.springbootapp.proplus_backendapplication.model.Project;
import org.springbootapp.proplus_backendapplication.model.ProjectMember;
import org.springbootapp.proplus_backendapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    Optional<List<ProjectMember>> findAllByUserIduser(User user);

    Optional<ProjectMember> findByUserIduserAndProjectIdproject(User user, Project project);
}
