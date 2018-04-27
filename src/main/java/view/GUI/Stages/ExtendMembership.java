package view.GUI.Stages;

import controller.BookCtrl;
import controller.MemberCheck;
import controller.MemberCtrl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Member;
import view.GUI.Components.AlertBox;
import view.GUI.Components.ConfirmationBox;
import view.GUI.Components.LabelTextFieldCombination;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ExtendMembership implements Stages, EventHandler<ActionEvent> {

    private static Stage stage;
    private LabelTextFieldCombination memberId;
    private Member member;

    @Override
    public Stage getStage() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setOnCloseRequest(e -> {
            e.consume();
            exitStage(false);
        });

        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);

        stage.setTitle("Extend Membership");
        stage.setScene(buildScene());
        return stage;
    }

    @Override
    public Scene buildScene() {
        return new Scene(buildLayout(), WIDTH, HEIGHT);
    }

    @Override
    public VBox buildLayout() {
        VBox layout = new VBox(20);

        memberId = new LabelTextFieldCombination("MemberID: ");

        //Add member button
        Button extendMembership = new Button("Extend Membership");
        extendMembership.setOnAction(this);

        //Close Button
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            exitStage(false);
        });

        layout.getChildren().addAll(memberId.draw(),extendMembership,closeButton);
        layout.setAlignment(Pos.CENTER);

        return layout;
    }

    @Override
    public void exitStage(boolean n) {
        ConfirmationBox box;
        if (n) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd. MM. yyyy.");
            Calendar cal = Calendar.getInstance();
            Calendar extended = Calendar.getInstance();
            cal.setTime(this.member.getMembershipExpirationDate());
            extended.setTime(this.member.getMembershipExpirationDate());
            extended.add(Calendar.YEAR,1);
            Date newDate = new Date(extended.getTime().getTime());

            box = new ConfirmationBox("Extend Membership",
                    "Name: " + this.member.getName() + "\n" +
                            "ID Doc Number: " + this.member.getIdDocNumber() + "\n" +
                            "Current expiration date: " + sdf.format(cal.getTime()) + "\n" +
                            "After extending: " + sdf.format(newDate) + "\n\n" +
                    "Extend membership?" + "\n",
                    "Yes",
                    "No"
            );
            if (box.display()) {

                MemberCtrl.extendMembership(this.member);
                memberAddedConfirmation("Membership Extended");
                stage.close();
            }
        }
        else {
            box = new ConfirmationBox(
                    "Exit \"Extend Membership\" menu",
                    "Are you sure you want to close this menu?",
                    "Yes",
                    "No"
            );
            if (box.display()) {
                stage.close();
            }
        }
    }

    @Override
    public void handle(ActionEvent event) {
        boolean coorectInput = true;
        boolean errorOccured = false;

        if (memberId.getTextFieldText().equals("") || memberId.getTextFieldText() == null) {
            errorMessage("Error: ID is missing");
            errorOccured = true;
            coorectInput = false;
        }
        else if (!memberId.getTextFieldText().matches("^[0-9]+")) {
            errorMessage("Error: ID must be number");
            errorOccured = true;
            coorectInput = false;
        }
        else {
            this.member = MemberCtrl.getMemberById(memberId.getTextFieldText());
        }

        if (coorectInput) {
            if (this.member == null) {
                errorMessage("Error: This member does not exists");
                errorOccured = true;
            }
        }

        if (!errorOccured) {
            exitStage(true);
        }
    }

    private void errorMessage(String message) {
        AlertBox alertBox = new AlertBox();
        alertBox.display("Error!",message,"Close");
    }

    private void memberAddedConfirmation(String message) {
        AlertBox alertBox = new AlertBox();
        alertBox.display("Succes",message,"OK");
    }
}