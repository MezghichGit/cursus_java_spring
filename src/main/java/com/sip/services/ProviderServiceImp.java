package com.sip.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sip.entities.Article;
import com.sip.entities.Provider;
import com.sip.repositories.ProviderRepository;

import com.sip.exceptions.ProviderNotFoundException;
@Service
public class ProviderServiceImp implements ProviderService{
	
	@Autowired
	private ProviderRepository providerRepository; // interface

	//@Autowired
	//public ProviderService(ProviderRepository providerRepository) {
	//	this.providerRepository = providerRepository;
	//}
	@Override
	public List<Provider> listProviders() {
		List<Provider> lp = (List<Provider>) providerRepository.findAll();
		if (lp.size() == 0)
			lp = null;
		return lp;
	}

	

	@Override
	public Provider saveProvider(Provider provider) {
		return this.providerRepository.save(provider);
	}

	@Override
	public boolean deleteProvider(long id) {
		  boolean deleted = true;
	      Provider provider = providerRepository.findById(id)
	              .orElseThrow(()-> new ProviderNotFoundException("Invalid provider Id:" + id));
	      try {
	        providerRepository.delete(provider);
	      }
	      catch(Exception e)
	      {
	    	 deleted = false; 
	      }
	          return deleted;
	}


	@Override
	public Provider getProviderById(long id) {
		 Provider provider = providerRepository.findById(id)
	              .orElseThrow(()-> new ProviderNotFoundException("Invalid provider Id:" + id));
		 return provider;
	}
	
	//@Query("FROM Article a WHERE a.provider.id = ?1")
	//public List<Article> findProviderArticles(long id){}
}
