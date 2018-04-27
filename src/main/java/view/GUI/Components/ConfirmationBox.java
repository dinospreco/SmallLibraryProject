package view.GUI.Components;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ConfirmationBox {

    private Stage stage;
    private static final int MIN_HEIGHT = 300;
    private static final int MIN_WIDTH = 300;
    private boolean answer;

    private String title;
    private String message;
    private String yesButtonText;
    private String noButtonText;

    public ConfirmationBox(String title, String message, String yesButtonText, String noButtonText) {
        this.title = title;
        this.message = message;
        this.yesButtonText = yesButtonText;
        this.noButtonText = noButtonText;
    }

    public boolean display(){

        VBox layout = new VBox(20);
        Scene scene = new Scene(layout);

        Label messageLabel = new Label(message);

        Button yesButton = new Button();
        yesButton.setText(yesButtonText);
        yesButton.setOnAction(e -> {
            answer = true;
            closeBox();
        });

        Button noButton = new Button();
        noButton.setText(noButtonText);
        noButton.setOnAction(e -> {
            answer = false;
            closeBox();
        });

        layout.getChildren().addAll(messageLabel,yesButton,noButton);
        layout.setAlignment(Pos.CENTER);

        layout.setBorder( new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)) );

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setOnCloseRequest(e -> {
            e.consume();
            answer = false;
            closeBox();
        });

        stage.initStyle(StageStyle.UTILITY);

        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);

        stage.setTitle(title);
        stage.setScene(scene);

        stage.showAndWait();
        return answer;

    }

    public void closeBox() {
        stage.close();
    }
}
