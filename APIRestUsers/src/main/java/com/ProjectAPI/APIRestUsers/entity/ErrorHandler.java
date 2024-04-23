package com.ProjectAPI.APIRestUsers.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.NamedEntityGraph;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorHandler {
    private String errorMessage;
    private int errorCode;
}
