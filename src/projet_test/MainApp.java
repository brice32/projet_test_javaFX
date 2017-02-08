package projet_test;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projet_test.model.Utilisateur;



public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Utilisateur> utilisateurData = FXCollections.observableArrayList();

    public MainApp(){
    	utilisateurData.add(new Utilisateur("WANG","Yuchen"));
    	utilisateurData.add(new Utilisateur("WEN","Hao"));
    	utilisateurData.add(new Utilisateur("TEST01","Test01"));
    }

    public ObservableList<Utilisateur> getUtilisateurData(){
    	return utilisateurData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showUtilisateurOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showUtilisateurOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/UtilisateurOverview.fxml"));
            AnchorPane ProjetOverview = (AnchorPane)loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(ProjetOverview);

            //give the controller access to the mian app
            UtilisateurOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showUtilisateurEditDialog(Utilisateur utilisateur){
    	  try {
    	        // Load the fxml file and create a new stage for the popup dialog.
    	        FXMLLoader loader = new FXMLLoader();
    	        loader.setLocation(MainApp.class.getResource("view/UtilisateurEditDialog.fxml"));
    	        AnchorPane page = (AnchorPane) loader.load();

    	        // Create the dialog Stage.
    	        Stage dialogStage = new Stage();
    	        dialogStage.setTitle("Edit Utilisateur");
    	        dialogStage.initModality(Modality.WINDOW_MODAL);
    	        dialogStage.initOwner(primaryStage);
    	        Scene scene = new Scene(page);
    	        dialogStage.setScene(scene);

    	        // Set the person into the controller.
    	        UtilisateurEditDialogController controller = loader.getController();
    	        controller.setDialogStage(dialogStage);
    	        controller.setUtilisateur(utilisateur);

    	        // Show the dialog and wait until the user closes it
    	        dialogStage.showAndWait();

    	        return controller.isOkClicked();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	        return false;
    	    }
    }
	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

    public static void main(String[] args) {
        launch(args);
    }
}