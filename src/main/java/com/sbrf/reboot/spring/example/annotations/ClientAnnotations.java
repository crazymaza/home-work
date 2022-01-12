package com.sbrf.reboot.spring.example.annotations;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component("client")
public class ClientAnnotations {
    private String msg = "Client";
}
