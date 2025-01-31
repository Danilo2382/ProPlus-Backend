package org.springbootapp.proplus_backendapplication.dto.response;

import org.springbootapp.proplus_backendapplication.model.ProjectMember;

public record ProjectMemberUsernameDto(ProjectMember projectMember, String username) {}
