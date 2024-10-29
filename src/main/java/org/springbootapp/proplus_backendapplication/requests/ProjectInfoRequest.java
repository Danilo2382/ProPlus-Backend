package org.springbootapp.proplus_backendapplication.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ProjectInfoRequest {

    private final Integer id;
    private final String param1;
    private final String param2;
}
