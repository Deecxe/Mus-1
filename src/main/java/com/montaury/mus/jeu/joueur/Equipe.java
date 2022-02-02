package com.montaury.mus.jeu.joueur;

import java.util.ArrayList;
import java.util.List;

public class Equipe {

    /*-----------------------------
    ---------- ATTRIBUTS ----------
    -----------------------------*/
    private String nom;

    private List<Joueur> listJoueur = new ArrayList<>();

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

    public void setListJoueur(List<Joueur> maList)
    {
        this.listJoueur = maList;
    }

    public List<Joueur> getListJoueur()
    {
        return this.listJoueur;
    }

    /*-----------------------------
    -------- CONSTRUCTEUR ---------
    -----------------------------*/

    public Equipe(String nom,List<Joueur> maList)
    {
        setNom(nom);
        setListJoueur(maList);
    }

    /*-----------------------------
    ---------- USUELLES -----------
    -----------------------------*/

    public String toString()
    {
        String resultat;
        resultat = getNom() + " : ";
        for(int i = 0; i < getListJoueur().size(); i++)
        {
            resultat += " " + getListJoueur().get(i).nom();
        }
        return resultat;
    }

}
