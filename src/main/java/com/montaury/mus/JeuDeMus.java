package com.montaury.mus;

import com.montaury.mus.jeu.Partie;
import com.montaury.mus.console.AffichageEvenements;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.Opposants;
import com.montaury.mus.jeu.joueur.Equipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JeuDeMus {
  public static void main(String[] args) {

    Joueur joueur1 = Joueur.humain("Kevin");
    Joueur joueur2 = Joueur.ordinateur();
    Joueur joueur3 = Joueur.ordinateur();
    Joueur joueur4 = Joueur.ordinateur();
    List<Joueur> maListeEquipe1 = new ArrayList<>();
    maListeEquipe1.add(joueur1);
    maListeEquipe1.add(joueur3);
    List<Joueur> maListeEquipe2 = new ArrayList<>();
    maListeEquipe2.add(joueur2);
    maListeEquipe2.add(joueur4);
    Equipe equipe1 = new Equipe("Equipe1",maListeEquipe1);
    Equipe equipe2 = new Equipe("Equipe2",maListeEquipe2);

    System.out.println(equipe1.toString());
    System.out.println(equipe2.toString());

    /*System.out.print("Entrez votre nom: ");
    var nomJoueur = new Scanner(System.in).next();
    var joueurHumain = Joueur.humain(nomJoueur);

    var partie = new Partie(new AffichageEvenements(joueurHumain));
    var resultat = partie.jouer(new Opposants(joueurHumain, Joueur.ordinateur()));

    System.out.println("Le vainqueur de la partie est " + resultat.vainqueur().nom()); */
  }
}
