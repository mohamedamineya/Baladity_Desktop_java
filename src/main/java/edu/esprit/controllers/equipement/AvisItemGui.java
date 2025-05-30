package edu.esprit.controllers.equipement;

import edu.esprit.entities.Avis;
import edu.esprit.services.ServiceAvis;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.util.Optional;

public class AvisItemGui {
    ServiceAvis serviceAvis = new ServiceAvis();
    @FXML
    private Text commentaireequipementB;
    @FXML
    private Text dateequipementB;
    @FXML
    private Rating noteequipementB;
    @FXML
    private Button supprimerAvisB;
    private Avis avis;

    @FXML
    void supprimerBAvisAction(ActionEvent event) {
        if (avis != null) {
            // Créer une boîte de dialogue de confirmation
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer cet avis ?");
            confirmationAlert.setTitle("Confirmation de suppression");

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = confirmationAlert.showAndWait();

            // Vérifier si l'utilisateur a cliqué sur le bouton OK
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Supprimer la réclamation
                ServiceAvis serviceAvis = new ServiceAvis();
                serviceAvis.supprimer(avis.getId_avis());

                // Afficher une alerte pour informer l'utilisateur que l'equipement a été supprimée avec succès
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("L'avis a été supprimée avec succès.");
                alert.setTitle("avis supprimée");
                alert.show();

                // Rediriger l'utilisateur vers la vue précédente (par exemple, la liste des équipement)
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/equipementGui/AfficherAvisGuiFront.fxml"));
                    supprimerAvisB.getScene().setRoot(root);
                } catch (IOException e) {
                    // Gérer l'exception si la redirection échoue
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setContentText("Une erreur s'est produite lors de la redirection.");
                    errorAlert.setTitle("Erreur de redirection");
                    errorAlert.show();
                }
            }
        } else {
            // Afficher un message d'erreur si la réclamation est null
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Impossible de supprimer l'equipement car aucune equipement n'est sélectionnée.");
            errorAlert.setTitle("Erreur de suppression");
            errorAlert.show();
        }
    }

    public void setData(Avis avis) {
        this.avis = avis;
        commentaireequipementB.setText(avis.getCommentaire_avis());
        noteequipementB.setRating(avis.getNote_avis());
        dateequipementB.setText(String.valueOf(avis.getDate_avis()));
    }
}
