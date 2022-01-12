package com.sbrf.reboot.spring.example.annotations;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component("account")
public class AccountAnnotations {
    private String msg = "We drink and unite";
}
