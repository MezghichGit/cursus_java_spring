package com.sip.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/etudiants")
public class EtudiantController {
	
	@RequestMapping("list") // route sur navigateur
	//@ResponseBody
	public String listEtudiants(Model model) //action
	{
     int total = 15;
     String trainer = "Amine";
     model.addAttribute("totalStudents", total);
     model.addAttribute("trainerStudents", trainer);
	 return "etudiants/listEtudiants"; //nom du fichier(template) View
	}
	
	@RequestMapping("add")
	//@ResponseBody
	public String addEtudiant()
	{
	 return "etudiants/addEtudiant";
	}
	
	@RequestMapping("SaveEtudiant")
	@ResponseBody
	public String saveEtudiant(@RequestParam("nomEtudiant")String nom)
	{
	 return "Vous êtes : " +nom;
	}

}
