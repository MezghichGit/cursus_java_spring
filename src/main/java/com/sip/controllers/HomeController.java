package com.sip.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@RequestMapping("/hello")
	@ResponseBody
	public String greeting()
	{
		return "<h3 align=center>Hello Spring</h3>";
	}
	
	@RequestMapping("/doc")
	@ResponseBody
	public String doc()
	{
		return "<h3 align=center>Formation Spring Boot</h3>";
	}
	
	@RequestMapping("/formation")
	public String info()
	{
		return "formation"; // chemin du fichier html sous le dossier templates
	}
	
	@RequestMapping("/about")
	public String about(Model model)
	{
		String center="SIP Academy";
		String stagiaires[] = {"Bilel","Asma", "Fatma","Jasser","Abdelmoumen","Wissem","Aziz Akrout","Aziz Ghariani","Zied","Yosra","Rihab"};
		
		model.addAttribute("centerView", center);
		
		model.addAttribute("stagiairesView", stagiaires);
		
		return "profile"; // chemin du fichier html sous le dossier templates
	}
	
	@RequestMapping("/etudiants")
	public String listEtudiants(Model model)
	{
		Etudiant etudiants[] = new Etudiant[3];
		
		etudiants[0] = new Etudiant("MEZGHICH","Mohamed Amine",24,"amine.mezghich@gmail.com");
		etudiants[1] = new Etudiant("Jarrahi","Asma",22,"asma.jarrahi@gmail.com");
		etudiants[2] = new Etudiant("Akrout","Aziz",23,"aziz.akrout@gmail.com");
		
		model.addAttribute("etudiantsView", etudiants);
		
		
		return "etudiants"; // chemin du fichier html sous le dossier templates
	}

}
