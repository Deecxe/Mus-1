package com.montaury.mus.jeu;

import com.montaury.mus.jeu.joueur.Joueur;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Opposants {
    private Equipe equipe1;
    private Equipe equipe2;
    private List<Joueur> ordre;

    public Opposants(Equipe equipe1, Equipe equipe2) {
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.ordre  = new ArrayList<>();

        ordre.add(equipe1.getListJoueur().get(0));
        ordre.add(equipe1.getListJoueur().get(1));
        ordre.add(equipe2.getListJoueur().get(0));
        ordre.add(equipe2.getListJoueur().get(1 ));
    }

    public void tourner() {
        var tmp = equipe1;
        equipe1 = equipe2;
        equipe2 = tmp;


        equipe1.getListJoueur().get(0).setEquipe(equipe1);
        equipe1.getListJoueur().get(1).setEquipe(equipe1);
        equipe2.getListJoueur().get(0).setEquipe(equipe2);
        equipe2.getListJoueur().get(1).setEquipe(equipe2);

        Joueur JoueurZaku = equipe2.getListJoueur().get(1);
        Joueur JoueurEsku = equipe1.getListJoueur().get(0);
        Joueur AllieZaku = equipe2.getListJoueur().get(0);
        Joueur AllieEsku = equipe1.getListJoueur().get(1);

        ordre.set(0,JoueurZaku);
        ordre.set(1,JoueurEsku);
        ordre.set(2,AllieZaku);
        ordre.set(3,AllieEsku);
    }

    public Equipe equipe1() {
        return this.equipe1;
    }

    public Equipe equipe2() {
        return this.equipe2;
    }

    public List<Joueur> getOrdre() {
        return ordre;
    }

    public List<Equipe> dansLOrdre() {
        return List.of(equipe1, equipe2);
    }
}