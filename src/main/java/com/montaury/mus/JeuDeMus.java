package com.montaury.mus;

import com.montaury.mus.console.AffichageEvenements;
import com.montaury.mus.jeu.Opposants;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.Equipe;
import com.montaury.mus.jeu.Partie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JeuDeMus {
  public static void main(String[] args) {

   System.out.print("Entrez votre nom:  ");
   var nomJoueur = new Scanner(System.in).next();
   var joueurHumain = Joueur.humain(nomJoueur);

   var equipe1 = new Equipe("Equipe1",joueurHumain,Joueur.ordinateur("Bot1"));
   var equipe2 = new Equipe("Equipe2",Joueur.ordinateur("Bot2"),Joueur.ordinateur("Bot3"));

   var partie = new Partie(new AffichageEvenements(joueurHumain));
   var resultat = partie.jouer(new Opposants(equipe1,equipe2));

   System.out.println("Le vainqueur de la partie est " + resultat.vainqueur().getNom());


  }
}
