package org.springbootapp.proplus_backendapplication.formaters;

import org.springbootapp.proplus_backendapplication.dtos.UserDto;
import org.springbootapp.proplus_backendapplication.model.User;

public class UserFormater {

    public static User formatDto(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getName());
        user.setSecondName(userDto.getSurname());
        user.setBirthday(userDto.getBirthday());
        user.setJoinDate(userDto.getJoinDate());
        user.setProfilePicture(userDto.getProfilePicture());
        return user;
    }
}
