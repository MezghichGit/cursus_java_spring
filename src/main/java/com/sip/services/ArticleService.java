package com.sip.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sip.entities.Article;

public interface ArticleService {
	public List<Article> listArticles();
	
	public Article saveArticle(Article article, long providerId,MultipartFile[] files);
	
	public boolean deleteArticle(long id);
	
	public Article getArticleById(long id);
	
	public Article updateArticle(Article article);

}
