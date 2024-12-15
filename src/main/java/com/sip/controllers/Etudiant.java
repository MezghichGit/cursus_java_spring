package com.sip.controllers;

public class Etudiant {
	private String nom;
	private String prenom;
	private int age;
	private String email;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Etudiant(String nom, String prenom, int age, String email) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.email = email;
	}

}
