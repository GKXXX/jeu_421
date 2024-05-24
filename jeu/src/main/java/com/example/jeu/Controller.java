package com.example.jeu;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Jeu421 jeu = new Jeu421();
    private List<Joueur> joueurs = new ArrayList<>();
    private int joueurActuelIndex = 0;

    @FXML
    private TextField playerNameField;

    @FXML
    private ComboBox<String> playerComboBox;

    @FXML
    private Label currentPlayerLabel;

    @FXML
    private Label resultLabel;

    @FXML
    private Label pointsLabel;

    @FXML
    protected void lancerDes() {
        if (joueurs.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText(null);
            alert.setContentText("Ajoutez des joueurs pour commencer la partie.");
            alert.showAndWait();
            return;
        }

        jeu.lancerDes();
        int[] resultats = jeu.getResultat();
        resultLabel.setText("Résultat des dés : " + resultats[0] + " " + resultats[1] + " " + resultats[2]);

        Joueur joueurActuel = joueurs.get(joueurActuelIndex);
        if (jeu.estVictoire()) {
            joueurActuel.ajouterPoint();
            pointsLabel.setText("Points : " + joueurActuel.getPoints());
            afficherVictoire();
        }

        passerAuJoueurSuivant();
    }

    @FXML
    protected void rejouer() {
        for (Joueur joueur : joueurs) {
            joueur.resetPoints();
        }
        pointsLabel.setText("Points : 0");
        resultLabel.setText("Résultat des dés");
        joueurActuelIndex = 0;
        if (!joueurs.isEmpty()) {
            currentPlayerLabel.setText("Joueur actuel : " + joueurs.get(joueurActuelIndex).getNom());
        } else {
            currentPlayerLabel.setText("Joueur actuel : ");
        }
    }

    @FXML
    protected void ajouterJoueur() {
        String nom = playerNameField.getText().trim();
        if (!nom.isEmpty()) {
            joueurs.add(new Joueur(nom));
            playerComboBox.getItems().add(nom);
            if (joueurs.size() == 1) {
                currentPlayerLabel.setText("Joueur actuel : " + nom);
            }
            playerNameField.clear();
        }
    }

    @FXML
    protected void enleverJoueur() {
        String nom = playerComboBox.getValue();
        if (nom != null) {
            int index = trouverIndexParNom(nom);
            if (index != -1) {
                joueurs.remove(index);
                playerComboBox.getItems().remove(nom);
                if (joueurs.isEmpty()) {
                    currentPlayerLabel.setText("Joueur actuel : ");
                    pointsLabel.setText("Points : 0");
                } else {
                    joueurActuelIndex = joueurActuelIndex % joueurs.size();
                    currentPlayerLabel.setText("Joueur actuel : " + joueurs.get(joueurActuelIndex).getNom());
                    pointsLabel.setText("Points : " + joueurs.get(joueurActuelIndex).getPoints());
                }
            }
        }
    }

    private int trouverIndexParNom(String nom) {
        for (int i = 0; i < joueurs.size(); i++) {
            if (joueurs.get(i).getNom().equals(nom)) {
                return i;
            }
        }
        return -1;
    }

    private void passerAuJoueurSuivant() {
        joueurActuelIndex = (joueurActuelIndex + 1) % joueurs.size();
        Joueur joueurActuel = joueurs.get(joueurActuelIndex);
        currentPlayerLabel.setText("Joueur actuel : " + joueurActuel.getNom());
        pointsLabel.setText("Points : " + joueurActuel.getPoints());
    }

    private void afficherVictoire() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Victoire !");
        alert.setHeaderText(null);
        alert.setContentText("Vous avez obtenu le résultat 4 2 1 ! Vous avez gagné un point !");
        alert.showAndWait();
    }
}
