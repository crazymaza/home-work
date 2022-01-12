package com.sbrf.reboot.spring.example;

import com.sbrf.reboot.spring.example.annotations.AccountAnnotations;
import com.sbrf.reboot.spring.example.annotations.ClientAnnotations;
import com.sbrf.reboot.spring.example.annotations.TestAnnotationConfig;
import com.sbrf.reboot.spring.example.xmlconfig.AccountXmlConfig;
import com.sbrf.reboot.spring.example.xmlconfig.ClientXmlConfig;
import com.sbrf.reboot.spring.example.xmlconfig.TestClassPathXml;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan(basePackages = "com.sbrf.reboot.spring.example.annotations")
public class Main {
    public static void main(String[] args) {

        getClassPathXMLContext();
        System.out.println();
        getAnnotationConfigContext();

    }

    public static void getClassPathXMLContext() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("context.xml");

        ClientXmlConfig clientXmlConfig = context.getBean("client", ClientXmlConfig.class);
        TestClassPathXml testClassPathXml = context.getBean("test", TestClassPathXml.class);
        AccountXmlConfig accountXmlConfig = context.getBean("account", AccountXmlConfig.class);

        String clientMsg = clientXmlConfig.getMsg();
        String accountMsg = accountXmlConfig.getMsg();
        String testMsg = testClassPathXml.getMsg();

        System.out.println(clientMsg);
        System.out.println(accountMsg);
        System.out.println(testMsg);
    }

    public static void getAnnotationConfigContext() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        TestAnnotationConfig test = context.getBean("test", TestAnnotationConfig.class);
        AccountAnnotations accountAnnotations = context.getBean("account", AccountAnnotations.class);
        ClientAnnotations clientAnnotations = context.getBean("client", ClientAnnotations.class);

        clientAnnotations.setMsg("Sabaton - Christmas Truce");

        String testMsg = test.getMsg();
        String accountMsg = accountAnnotations.getMsg();
        String clientMsg = clientAnnotations.getMsg();

        System.out.println(testMsg);
        System.out.println(accountMsg);
        System.out.println(clientMsg);
    }
}
