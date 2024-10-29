package org.springbootapp.proplus_backendapplication.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListResponse {

    private List<?> list;
    private String message;
}
