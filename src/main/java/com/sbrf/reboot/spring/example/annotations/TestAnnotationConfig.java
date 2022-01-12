package com.sbrf.reboot.spring.example.annotations;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component("test")
public class TestAnnotationConfig {
    private String msg = "Today we're all brothers";
}
