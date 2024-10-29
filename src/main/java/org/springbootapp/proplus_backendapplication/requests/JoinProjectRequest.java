package org.springbootapp.proplus_backendapplication.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class JoinProjectRequest {

    private final String code;
    private final String password;
}
