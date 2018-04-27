package view.GUI.Components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AlertBox{

    private Stage stage;
    private static final int MIN_HEIGHT = 100;
    private static final int MIN_WIDTH = 200;

    public void display(String title, String message, String buttonText){

        StackPane root = new StackPane();
        VBox layout = new VBox();

        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setTextAlignment(TextAlignment.JUSTIFY);

        Button button = new Button();
        button.setText(buttonText);
        button.setOnAction(e -> stage.close());

        layout.getChildren().addAll(messageLabel,button);
        layout.setAlignment(Pos.CENTER);

        root.getChildren().add(layout);

        Scene scene = new Scene(root);

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.initStyle(StageStyle.UTILITY);

        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);

        stage.setTitle(title);
        stage.setScene(scene);

        stage.show();
    }
}
