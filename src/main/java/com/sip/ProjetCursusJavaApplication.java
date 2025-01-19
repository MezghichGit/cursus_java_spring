package com.sip;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sip.controllers.ArticleController;

@SpringBootApplication
public class ProjetCursusJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetCursusJavaApplication.class, args);
		System.out.println("Hello SIP ACADEMY");
		new File(ArticleController.uploadDirectory).mkdir();
	}

}
