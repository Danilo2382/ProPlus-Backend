package org.springbootapp.proplus_backendapplication.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LoginRequest {

    private final String username;
    private final String password;
}
