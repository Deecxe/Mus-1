package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Equipe {

    /*-----------------------------
    ---------- ATTRIBUTS ----------
    -----------------------------*/
    private String nom;

    private List<Joueur> listJoueur;

    /*-----------------------------
    -------- ENCAPSULATION --------
    -----------------------------*/

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getNom()
    {
        return this.nom;
    }

    public List<Joueur> getListJoueur(){
    return  this.listJoueur;
    }
    /*-----------------------------
    -------- CONSTRUCTEUR ---------
    -----------------------------*/

    public Equipe(String nom,Joueur j1,Joueur j2)
    {
        setNom(nom);
        this.listJoueur = new ArrayList<>();
        this.listJoueur.add(j1);
        this.listJoueur.add(j2);
        j1.setEquipe(this);
        j2.setEquipe(this);
    }
}






