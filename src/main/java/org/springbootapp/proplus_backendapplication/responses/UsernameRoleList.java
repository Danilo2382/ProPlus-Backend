package org.springbootapp.proplus_backendapplication.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class UsernameRoleList {

    private List<String> usernames;
    private List<Integer> roles;
}
