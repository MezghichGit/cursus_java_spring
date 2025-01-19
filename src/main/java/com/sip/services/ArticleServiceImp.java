package com.sip.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sip.entities.Article;
import com.sip.entities.Provider;
import com.sip.exceptions.ArticleNotFoundException;
import com.sip.repositories.ArticleRepository;
import com.sip.repositories.ProviderRepository;
@Service
public class ArticleServiceImp implements ArticleService{
	public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	private final ArticleRepository articleRepository;
	private final ProviderRepository providerRepository;
	
	public ArticleServiceImp(ArticleRepository articleRepository, ProviderRepository providerRepository) {
		super();
		this.articleRepository = articleRepository;
		this.providerRepository = providerRepository;
	}

	@Override
	public List<Article> listArticles() {
		List<Article> la = (List<Article>) articleRepository.findAll();
		if (la.size()== 0)
			la = null;
		return la;
	}

	@Override
	public Article saveArticle(Article article,long providerId, MultipartFile[] files) {
		
		Provider provider =  providerRepository.findById(providerId)
                .orElseThrow(()-> new IllegalArgumentException("Invalid provider Id:" + providerId));
    	article.setProvider(provider);
    	
    	// début upload
    	StringBuilder fileName = new StringBuilder();
    	MultipartFile file = files[0];
    	Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
    	fileName.append(file.getOriginalFilename());
		  try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// fin upload
		 article.setPicture(fileName.toString());
		 articleRepository.save(article);
		return article;
	}

	@Override
	public boolean deleteArticle(long id) {
		  boolean deleted = true;
	      Article article = articleRepository.findById(id)
	              .orElseThrow(()-> new ArticleNotFoundException("Invalid article Id:" + id));
	      try {
	    	  articleRepository.delete(article);
	      }
	      catch(Exception e)
	      {
	    	 deleted = false; 
	      }
	          return deleted;
	}

	@Override
	public Article getArticleById(long id) {
		Article article = articleRepository.findById(id)
	            .orElseThrow(()->new IllegalArgumentException("Invalid article Id:" + id));
	   
		return article;
	}

	@Override
	public Article updateArticle(Article article) {
		// TODO Auto-generated method stub
		return articleRepository.save(article);
	}

}
