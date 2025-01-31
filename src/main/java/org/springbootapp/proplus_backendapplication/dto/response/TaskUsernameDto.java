package org.springbootapp.proplus_backendapplication.dto.response;

import org.springbootapp.proplus_backendapplication.model.Task;

public record TaskUsernameDto(Task task, String username) {}
