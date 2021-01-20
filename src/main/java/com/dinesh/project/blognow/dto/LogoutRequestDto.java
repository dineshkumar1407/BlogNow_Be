package com.dinesh.project.blognow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogoutRequestDto {
    @NotBlank
    private String refreshToken;
}
