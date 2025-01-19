package com.sip.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.sip.entities.Article;
import com.sip.entities.Provider;

public interface ProviderService {
	
	public List<Provider> listProviders();
	
	public Provider saveProvider(Provider provider);
	
	public boolean deleteProvider(long id);
	
	public Provider getProviderById(long id);

}
