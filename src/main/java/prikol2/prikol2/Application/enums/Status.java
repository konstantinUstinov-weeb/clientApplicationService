package prikol2.prikol2.Application.enums;

import lombok.Getter;

@Getter
public enum Status {NEW("NEW"), PROCESSING("PROCESSING"), COMPLETED("COMPLETED");

    private final String value;

    Status(String value) {
        this.value = value;
    }
}
