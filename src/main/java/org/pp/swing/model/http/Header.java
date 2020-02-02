package org.pp.swing.model.http;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Header {
    private String name;
    private String value;
    private final String sep = ":";

    public Header(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name + sep + value;
    }
}
