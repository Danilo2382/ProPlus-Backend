package org.springbootapp.proplus_backendapplication.converters;

import org.springbootapp.proplus_backendapplication.dtos.UserDto;
import org.springbootapp.proplus_backendapplication.model.User;

public class UserConverter {

    public static UserDto convertUser(User user) {
        return new UserDto(user.getUsername(), user.getEmail(), user.getPassword(), user.getFirstName(),
                user.getSecondName(), user.getBirthday(), user.getJoinDate(), user.getProfilePicture());
    }
}
