package com.backend.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * application.
 */
@SpringBootApplication
public class EmployeeManagementSystemApplication {
 /**
  *
  * @param args Arguments parameter.
  */
 public final void run(final String[] args) {
  SpringApplication.run(EmployeeManagementSystemApplication.class, args);
 }

 /**
  * main method.
  *
  * @param args Arguments parameter.
  */
 public static void main(final String[] args) {

  new EmployeeManagementSystemApplication().run(args);
 }

}
