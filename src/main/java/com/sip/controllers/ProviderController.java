package com.sip.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sip.entities.Article;
import com.sip.entities.Provider;
import com.sip.exceptions.ProviderNotFoundException;
import com.sip.repositories.ProviderRepository;
import com.sip.services.ProviderService;
import com.sip.services.ProviderServiceImp;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/providers/")

public class ProviderController {

	ProviderService providerService; // le faible couplage
	
	@Autowired
	ProviderRepository providerRepository;

	// @Autowired // iOC = inversion de control (injection de dépendance)

	public ProviderController(ProviderService providerService) { // paramètre de type interface
		this.providerService = providerService;
	}

	@GetMapping("list")
	// @ResponseBody
	public String list(Model model) {
		List<Provider> providers = this.providerService.listProviders();
		model.addAttribute("providers", providers);
		
	
		return "provider/listProviders";
	}

	@GetMapping("delete/{id}")
	public String deleteProvider(@PathVariable("id") long id, Model model) {

		if (this.providerService.deleteProvider(id)) {
			return "redirect:../list";
		} else {
			model.addAttribute("msg", "Problème de suppression de provider");
			return "500.html";
		}
	}

	@GetMapping("add")
	public String showAddProviderForm(Model model) {
		Provider provider = new Provider();// object dont la valeur des attributs par defaut
		model.addAttribute("provider", provider);
		return "provider/addProvider";
	}

	@PostMapping("add")
	public String addProvider(@Valid Provider provider, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "provider/addProvider";
		}
		this.providerService.saveProvider(provider);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showProviderFormToUpdate(@PathVariable("id") long id, Model model) {
		String viewPage = "provider/updateProvider";
		try {
			Provider provider = this.providerService.getProviderById(id);
			model.addAttribute("provider", provider);
		} catch (ProviderNotFoundException ex) {
			model.addAttribute("msg", ex.getMessage());
			return "500.html";
		}

		return viewPage;
	}

	@PostMapping("update")
	public String updateProvider(@Valid Provider provider, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "provider/updateProvider";
		}
		this.providerService.saveProvider(provider);
		return "redirect:list";

	}
	
	@GetMapping("show/{id}")
	public String showProvider(@PathVariable("id") long id, Model model) {
		Provider provider = this.providerService.getProviderById(id);
				List<Article> articles = providerRepository.findArticlesByProvider(id);
		for (Article a : articles)
			System.out.println("Article = " + a.getLabel());
		
		model.addAttribute("articles", articles);
		model.addAttribute("provider", provider);
		return "provider/showProvider";
	}


}
