
package com.portfolio.config;

import java.util.UUID;

public class UuidConverter {
    public String convert(Object event) {
        return UUID.randomUUID().toString();
    }
}
