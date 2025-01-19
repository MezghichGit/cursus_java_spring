package com.sip.controllers;

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sip.entities.Article;
import com.sip.entities.Provider;
import com.sip.repositories.ArticleRepository;
import com.sip.repositories.ProviderRepository;
import com.sip.services.ArticleService;
import com.sip.services.ProviderService;

import jakarta.validation.Valid;
@Controller
@RequestMapping("/articles/")
public class ArticleController {
	
	
	
	//private final ArticleRepository articleRepository;
	//private final ProviderRepository providerRepository;
    
	ArticleService articleService;
	ProviderService providerService;
	
    public ArticleController(ArticleService articleService, ProviderService providerService) {
        this.articleService = articleService;
        this.providerService = providerService;
    }
	
	
    
    @GetMapping("list")
    public String listProviders(Model model) {
    	//model.addAttribute("articles", null);
        List<Article> la = articleService.listArticles();
        model.addAttribute("articles", la);
        return "article/listArticles.html";
    }
    
    @GetMapping("add")
    public String showAddArticleForm(Article article, Model model) {
    	
    	model.addAttribute("providers", providerService.listProviders());
    	model.addAttribute("article", new Article());
        return "article/addArticle.html";
    }
    
    @GetMapping("delete/{id}")
    public String deleteProvider(@PathVariable("id") long id, Model model) {
    	if (this.articleService.deleteArticle(id)) {
			return "redirect:../list";
		} else {
			model.addAttribute("msg", "Problème de suppression de provider");
			return "500.html";
		}
    }
    @GetMapping("show/{id}")
    public String showArticleDetails(@PathVariable("id") long id, Model model) {
    	Article article = articleService.getArticleById(id);	
        model.addAttribute("article", article);
        
        return "article/showArticle";
    }
    
    
    
    @PostMapping("add")
    //@ResponseBody
    public String addArticle(@Valid Article article, BindingResult result, 
    		@RequestParam(name = "providerId", required = false) Long p,
    		@RequestParam("files") MultipartFile[] files) {
    	
    	if(result.hasErrors()) {
    		throw new IllegalArgumentException("Paramètres non conformes!");
    	}
    	 this.articleService.saveArticle(article, p, files);
    	 return "redirect:list";
    	
    	//return article.getLabel() + " " +article.getPrice() + " " + p.toString();
    }
    
    
   
    @GetMapping("edit/{id}")
    public String showArticleFormToUpdate(@PathVariable("id") long id, Model model) {
    	
    	Article article = articleService.getArticleById(id);
    	
        model.addAttribute("article", article);
        model.addAttribute("providers", providerService.listProviders());
        model.addAttribute("idProvider", article.getProvider().getId());
        
        return "article/updateArticle";
    }
    
    
    @PostMapping("edit/{id}")
    public String updateArticle(@PathVariable("id") long id, @Valid Article article, BindingResult result,
        Model model, @RequestParam(name = "providerId", required = false) Long p) {
        
    	if (result.hasErrors()) {
        	article.setId(id);
            return "article/updateArticle";
        }
        
        Provider provider = providerService.getProviderById(p);
        
    	article.setProvider(provider);
    	
        articleService.updateArticle(article);
        
        model.addAttribute("articles", articleService.listArticles());
        return "article/listArticles";
    }
    
   


}
