package com.montaury.mus.jeu;

import com.montaury.mus.jeu.evenements.Evenements;
import com.montaury.mus.jeu.joueur.Joueur;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Gehiago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Hordago;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Paso;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Imido;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Kanta;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Mintza;
import com.montaury.mus.jeu.tour.phases.dialogue.choix.Tira;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.montaury.mus.jeu.joueur.Fixtures.unJoueurFaisantChoix;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class MancheTest {

  @BeforeEach
  void setUp() {
    manche = new Manche(mock(Evenements.class));
  }

  @Test
  void devrait_terminer_la_manche_si_hordago_au_grand() {
    var joueur1 = unJoueurFaisantChoix(new Mintza(), new Hordago());
    var joueur2 = unJoueurFaisantChoix(new Kanta());

    var equipe1 = new Equipe("Equipe1",joueur1, Joueur.ordinateur("Bot"));
    var equipe2 = new Equipe("Equipe2",joueur2,Joueur.ordinateur("Bot"));

    var resultat = manche.jouer(new Opposants(equipe1, equipe2));

    assertThat(resultat.vainqueur()).isNotNull();
    assertThat(resultat.pointsVaincu()).isZero();
  }

  @Test
  void devrait_terminer_la_manche_si_un_joueur_a_40_points() {
    var joueur1 = unJoueurFaisantChoix(new Mintza(), new Imido(), new Gehiago(2));
    var joueur2 = unJoueurFaisantChoix(new Gehiago(40), new Tira());

    var equipe1 = new Equipe("Equipe1",joueur1, Joueur.ordinateur("Bot"));
    var equipe2 = new Equipe("Equipe2",joueur2,Joueur.ordinateur("Bot"));

    var resultat = manche.jouer(new Opposants(equipe1, equipe2));

    assertThat(resultat.vainqueur()).isEqualTo(joueur1.getEquipe());
    assertThat(resultat.pointsVaincu()).isZero();
  }

  @Test
  void devrait_changer_l_ordre_des_opposants_a_la_fin_du_tour() {
    var joueur1 = unJoueurFaisantChoix(new Mintza(), new Hordago());
    var joueur2 = unJoueurFaisantChoix(new Kanta());

    var equipe1 = new Equipe("Equipe1",joueur1, Joueur.ordinateur("Bot"));
    var equipe2 = new Equipe("Equipe2",joueur2,Joueur.ordinateur("Bot"));

    var opposants = new Opposants(equipe1, equipe2);

    manche.jouer(opposants);

    assertThat(opposants.dansLOrdre()).containsExactly(joueur1.getEquipe(), joueur2.getEquipe());
  }

  private Manche manche;
}