package org.springbootapp.proplus_backendapplication.responses;

import lombok.*;
import org.springbootapp.proplus_backendapplication.dtos.UserDto;

@Data
@AllArgsConstructor
public class UserInfoResponse {

    private UserDto userDto;
    private String message;
}
