package com.project.subwate_backend.presentation.friend.dto.enums;

public enum ShareLocationStatus {

    PENDING("요청 대기중"),
    ACCEPTED("요청 수락됨"),
    REJECTED("요청 거절됨"),
    CANCELLED("요청 취소됨");

    private final String description;

    ShareLocationStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
