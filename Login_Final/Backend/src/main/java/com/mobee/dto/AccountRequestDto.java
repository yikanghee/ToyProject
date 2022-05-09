package com.mobee.dto;

import lombok.Getter;

@Getter
public class AccountRequestDto {

    private String username;

    private String password;

    private String passwordConfirm;
}
