package com.example.enums;

import com.google.common.collect.Sets;

public enum UserApplicationPermissions {
    COURSE_READ("course-read"),COURSE_WRITE("course-write");
    private final String permission;
    UserApplicationPermissions(String permission){
        this.permission = permission;
    }
    public String getPermission(){
        return this.permission;
    }
}
