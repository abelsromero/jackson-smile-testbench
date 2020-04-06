package org.abelsromero.json;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Data
public class SimplePojo {

    private String text;
    private int value;

    private LocalDate localdate;
    private Duration duration;
    private TimeUnit timeUnit;

}
