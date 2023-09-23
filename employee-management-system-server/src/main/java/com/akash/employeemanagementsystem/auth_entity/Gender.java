package com.akash.employeemanagementsystem.auth_entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {

    MALE("MALE"),FEMALE("FEMALE");
    @Getter
    private final String sex;

}
