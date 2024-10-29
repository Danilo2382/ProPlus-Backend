package org.springbootapp.proplus_backendapplication.dtos;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.springbootapp.proplus_backendapplication.views.UserDtoView;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.springbootapp.proplus_backendapplication.model.User}
 */
@Value
public class UserDto implements Serializable {
    @JsonView(UserDtoView.BasicAttributes.class)
    @NotNull
    @Size(max = 45)
    String username;
    @JsonView(UserDtoView.BasicAttributes.class)
    @NotNull
    @Size(max = 45)
    String email;
    @JsonView(UserDtoView.BasicAttributes.class)
    @NotNull
    @Size(max = 500)
    String password;
    @JsonView(UserDtoView.BasicAttributes.class)
    @NotNull
    @Size(max = 45)
    String name;
    @JsonView(UserDtoView.BasicAttributes.class)
    @NotNull
    @Size(max = 45)
    String surname;
    @JsonView(UserDtoView.BasicAttributes.class)
    @NotNull
    LocalDateTime birthday;
    @JsonView(UserDtoView.AllAttributes.class)
    @NotNull
    LocalDateTime joinDate;
    @JsonView(UserDtoView.BasicAttributes.class)
    String profilePicture;
}