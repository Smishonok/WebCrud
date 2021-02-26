package org.valentinenikolaev.webcrud.utils.beans;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "org.valentinenikolaev.webcrud.controllers")
@Import(FiltersBeans.class)
public class ControllersBeans {

}
