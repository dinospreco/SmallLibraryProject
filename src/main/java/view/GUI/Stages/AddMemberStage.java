package view.GUI.Stages;

import controller.BookCtrl;
import controller.MemberCheck;
import controller.MemberCtrl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Book;
import model.Member;
import view.GUI.Components.AlertBox;
import view.GUI.Components.ConfirmationBox;
import view.GUI.Components.LabelTextFieldCombination;

import java.sql.Date;
import java.util.Calendar;

public class AddMemberStage implements Stages, EventHandler<ActionEvent> {

    private static Stage stage;
    private LabelTextFieldCombination name;
    private LabelTextFieldCombination idDocNumber;

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

        stage.setTitle("Add a member");
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

        name = new LabelTextFieldCombination("Name: ");
        idDocNumber = new LabelTextFieldCombination("ID Doc Number: ");

        //Add member button
        Button addMember = new Button("Add Member");
        addMember.setOnAction(this);

        //Close Button
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            exitStage(false);
        });

        layout.getChildren().addAll(name.draw(),idDocNumber.draw(),addMember,closeButton);
        layout.setAlignment(Pos.CENTER);

        return layout;
    }

    @Override
    public void exitStage(boolean n) {
        ConfirmationBox box;
        if (n) {
            box = new ConfirmationBox("Add member",
                    "Name: " + this.name.getTextFieldText() + "\n" +
                    "ID Doc Number: " + idDocNumber.getTextFieldText() + "\n\n" +
                            "Are you sure you want to add this member?",
                    "Yes",
                    "No"
            );
            if (box.display()) {
                Member member = new Member(
                        name.getTextFieldText(),
                        idDocNumber.getTextFieldText()
                );
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.YEAR,1);
                member.setMembershipExpirationDate(new Date(cal.getTime().getTime()));
                MemberCtrl.addMember(member);
                memberAddedConfirmation("Member added");
                stage.close();
            }
        }
        else {
            box = new ConfirmationBox(
                    "Exit \"Add Member\" menu",
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
        String name = MemberCheck.checkName(this.name.getTextFieldText());
        String idDocNumber = MemberCheck.checkIdDocNumber(this.idDocNumber.getTextFieldText());
        boolean errorOccured = false;

        if (name.startsWith("Error:")) {
            errorMessage(name);
            errorOccured = true;
        }
        else if (idDocNumber.startsWith("Error:")) {
            errorMessage(idDocNumber);
            errorOccured = true;
        }
        else if (MemberCtrl.getMemberByIdDocNumber(idDocNumber) != null){
            errorMessage("Error: Member with ID Doc Number: " + idDocNumber + " already Exists");
            errorOccured = true;
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
