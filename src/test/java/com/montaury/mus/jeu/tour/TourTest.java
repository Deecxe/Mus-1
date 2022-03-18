package com.montaury.mus.jeu.tour;

import com.montaury.mus.jeu.Equipe;
import com.montaury.mus.jeu.Manche;
import com.montaury.mus.jeu.Opposants;
import com.montaury.mus.jeu.carte.Carte;
import com.montaury.mus.jeu.carte.Defausse;
import com.montaury.mus.jeu.evenements.Evenements;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Gehiago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Idoki;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Imido;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Kanta;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Mintza;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Paso;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Tira;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.carte.Fixtures.paquetAvec;
import static com.montaury.mus.jeu.carte.Fixtures.paquetEntierCroissant;
import static com.montaury.mus.jeu.joueur.Fixtures.unJoueurFaisantChoix;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TourTest {

  @BeforeEach
  void setUp() {
    evenementsDeJeu = mock(Evenements.class);
    tour = new Tour(evenementsDeJeu, paquetEntierCroissant(), new Defausse());
  }

  @Test
  void devrait_donner_tous_les_points_au_joueur_esku_si_le_joueur_zaku_fait_tira() {
    var joueur1 = unJoueurFaisantChoix(new Mintza(), new Imido(), new Imido(), new Imido(), new Imido());
    var joueur2 = unJoueurFaisantChoix(new Tira());

    var equipe1 = new Equipe("Equipe1",joueur1, Joueur.ordinateur("Bot"));
    var equipe2 = new Equipe("Equipe2",joueur2,Joueur.ordinateur("Bot"));

    var opposants = new Opposants(equipe1, equipe2);
    var score = new Manche.Score(opposants);

    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).isEmpty();
    assertThat(score.scoreParEquipe()).containsEntry(joueur1.getEquipe(), 8);
    assertThat(score.scoreParEquipe()).containsEntry(joueur2.getEquipe(), 0);
  }

  @Test
  void devrait_repartir_les_points_si_tout_est_paso() {
    var joueur1 = unJoueurFaisantChoix(new Mintza(), new Paso(), new Paso(), new Paso(), new Paso());
    var joueur2 = unJoueurFaisantChoix(new Paso());

    var equipe1 = new Equipe("Equipe1",joueur1, Joueur.ordinateur("Bot"));
    var equipe2 = new Equipe("Equipe2",joueur2,Joueur.ordinateur("Bot"));

    var opposants = new Opposants(equipe1, equipe2);
    var score = new Manche.Score(opposants);

    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).isEmpty();
    assertThat(score.scoreParEquipe()).containsEntry(joueur1.getEquipe(), 1);
    assertThat(score.scoreParEquipe()).containsEntry(joueur2.getEquipe(), 5);
  }

  @Test
  void devrait_faire_gagner_le_joueur_zaku_si_hordago_au_grand() {
    var joueur1 = unJoueurFaisantChoix(new Mintza(), new Hordago());
    var joueur2 = unJoueurFaisantChoix(new Kanta());

    var equipe1 = new Equipe("Equipe1",joueur1, Joueur.ordinateur("Bot"));
    var equipe2 = new Equipe("Equipe2",joueur2,Joueur.ordinateur("Bot"));

    var opposants = new Opposants(equipe1, equipe2);
    var score = new Manche.Score(opposants);

    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).contains(joueur2.getEquipe());
    assertThat(score.scoreParEquipe()).containsEntry(joueur1.getEquipe(), 0);
    assertThat(score.scoreParEquipe()).containsEntry(joueur2.getEquipe(), 40);
  }

  @Test
  void devrait_partager_les_points_si_tout_est_idoki() {
    var joueur1 = unJoueurFaisantChoix(new Mintza(), new Imido(), new Imido(), new Imido(), new Imido());
    var joueur2 = unJoueurFaisantChoix(new Idoki());

    var equipe1 = new Equipe("Equipe1",joueur1, Joueur.ordinateur("Bot"));
    var equipe2 = new Equipe("Equipe2",joueur2,Joueur.ordinateur("Bot"));

    var opposants = new Opposants(equipe1, equipe2);
    var score = new Manche.Score(opposants);

    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).isEmpty();
    assertThat(score.scoreParEquipe()).containsEntry(joueur1.getEquipe(), 2);
    assertThat(score.scoreParEquipe()).containsEntry(joueur2.getEquipe(), 10);
  }

  @Test
  void devrait_partager_les_points_si_tout_est_gehiago_puis_idoki() {
    var joueur1 = unJoueurFaisantChoix(new Mintza(), new Imido(), new Idoki(), new Imido(), new Idoki(), new Imido(), new Idoki(), new Imido(), new Idoki());
    var joueur2 = unJoueurFaisantChoix(new Gehiago(2));

    var equipe1 = new Equipe("Equipe1",joueur1, Joueur.ordinateur("Bot"));
    var equipe2 = new Equipe("Equipe2",joueur2,Joueur.ordinateur("Bot"));

    var opposants = new Opposants(equipe1, equipe2);
    var score = new Manche.Score(opposants);

    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).isEmpty();
    assertThat(score.scoreParEquipe()).containsEntry(joueur1.getEquipe(), 4);
    assertThat(score.scoreParEquipe()).containsEntry(joueur2.getEquipe(), 16);
  }

  @Test
  void devrait_privilegier_le_joueur_esku_si_les_mains_sont_identiques() {
    var joueur1 = unJoueurFaisantChoix(new Mintza(), new Imido(), new Imido(), new Imido(), new Imido());
    var joueur2 = unJoueurFaisantChoix(new Idoki());

    var equipe1 = new Equipe("Equipe1",joueur1, Joueur.ordinateur("Bot"));
    var equipe2 = new Equipe("Equipe2",joueur2,Joueur.ordinateur("Bot"));

    var opposants = new Opposants(equipe1, equipe2);
    var score = new Manche.Score(opposants);

    Tour tour = new Tour(evenementsDeJeu, paquetAvec(Carte.AS_BATON, Carte.DEUX_BATON, Carte.TROIS_BATON, Carte.QUATRE_BATON, Carte.AS_COUPE, Carte.DEUX_COUPE, Carte.TROIS_COUPE, Carte.QUATRE_COUPE), new Defausse());

    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).isEmpty();
    assertThat(score.scoreParEquipe()).containsEntry(joueur1.getEquipe(), 7);
    assertThat(score.scoreParEquipe()).containsEntry(joueur2.getEquipe(), 0);
  }

  @Test
  void devrait_attribuer_les_bonus_au_joueur_ayant_la_meilleure_main_pour_chaque_phase() {
    var joueur1 = unJoueurFaisantChoix(new Mintza(), new Paso(), new Paso());
    var joueur2 = unJoueurFaisantChoix(new Paso(), new Paso());

    var equipe1 = new Equipe("Equipe1",joueur1, Joueur.ordinateur("Bot"));
    var equipe2 = new Equipe("Equipe2",joueur2,Joueur.ordinateur("Bot"));

    var opposants = new Opposants(equipe1, equipe2);
    var score = new Manche.Score(opposants);

    Tour tour = new Tour(evenementsDeJeu, paquetAvec(Carte.ROI_BATON, Carte.ROI_COUPE, Carte.VALET_BATON, Carte.AS_EPEE, Carte.DEUX_COUPE, Carte.TROIS_COUPE, Carte.QUATRE_COUPE, Carte.CINQ_COUPE), new Defausse());

    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).isEmpty();
    assertThat(score.scoreParEquipe()).containsEntry(joueur1.getEquipe(), 6);
    assertThat(score.scoreParEquipe()).containsEntry(joueur2.getEquipe(), 0);
  }

  @Test
  void devrait_donner_tous_les_points_a_l_equipe_esku_si_l_equipe_zaku_fait_tira() {
    var joueur1 = unJoueurFaisantChoix(new Mintza(), new Imido(), new Imido(), new Imido(), new Imido());
    var joueur2 = unJoueurFaisantChoix(new Tira());
    var joueur3 = unJoueurFaisantChoix(new Imido());
    var joueur4 = unJoueurFaisantChoix(new Tira());

    var equipe1 = new Equipe("Equipe1",joueur1, Joueur.ordinateur("Bot"));
    var equipe2 = new Equipe("Equipe2",joueur2,Joueur.ordinateur("Bot"));

    var opposants = new Opposants(equipe1, equipe2);
    var score = new Manche.Score(opposants);

    tour.jouer(opposants, score);

    assertThat(score.vainqueur()).isEmpty();
    assertThat(score.scoreParEquipe()).containsEntry(equipe1, 8);
    assertThat(score.scoreParEquipe()).containsEntry(equipe2, 0);
  }

  private Evenements evenementsDeJeu;
  private Tour tour;
}