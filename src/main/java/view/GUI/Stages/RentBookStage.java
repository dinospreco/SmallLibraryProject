package view.GUI.Stages;

import controller.BookCheck;
import controller.BookCtrl;
import controller.MemberCtrl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Book;
import model.Member;
import view.GUI.Components.AlertBox;
import view.GUI.Components.ConfirmationBox;
import view.GUI.Components.LabelTextFieldCombination;

public class RentBookStage implements Stages, EventHandler<ActionEvent> {

    private static Stage stage;
    private LabelTextFieldCombination bookId;
    private LabelTextFieldCombination memberId;
    Book book;
    Member member;

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

        stage.setTitle("Rent Book");
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

        bookId = new LabelTextFieldCombination("Book ID: ");
        memberId = new LabelTextFieldCombination("Member ID: ");

        //Add book button
        Button addBook = new Button("Rent Book");
        addBook.setOnAction(this);

        //Close Button
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            exitStage(false);
        });

        layout.getChildren().addAll(bookId.draw(),memberId.draw(),addBook,closeButton);
        layout.setAlignment(Pos.CENTER);

        return layout;
    }

    @Override
    public void handle(ActionEvent event) {
        boolean errorOccured = false;
        boolean coorectInput = true;

        if (bookId.getTextFieldText().equals("") || bookId.getTextFieldText() == null) {
            errorMessage("Error: Book ID is missing");
            errorOccured = true;
            coorectInput = false;
        }
        else if (memberId.getTextFieldText().equals("") || memberId.getTextFieldText() == null) {
            errorMessage("Error: Member ID is missing");
            errorOccured = true;
            coorectInput = false;
        }
        else if (!bookId.getTextFieldText().matches("^[0-9]\\d*$")) {
            errorMessage("Error: ID must be number");
            errorOccured = true;
            coorectInput = false;
        }
        else if (!memberId.getTextFieldText().matches("^[0-9]\\d*$")) {
            errorMessage("Error: ID must be number");
            errorOccured = true;
            coorectInput = false;
        }
        else {
            this.book = BookCtrl.getBookById(bookId.getTextFieldText());
            this.member = MemberCtrl.getMemberById(memberId.getTextFieldText());
        }

        if (coorectInput) {
            if (this.book == null) {
                errorMessage("Error: This book does not exists");
                errorOccured = true;
            }
            else if (this.book.isRented()) {
                errorMessage("Error: Book is already rented");
                errorOccured = true;
            }
            else if (this.member == null) {
                errorMessage("Error: This member does not exists");
                errorOccured = true;
            }
            else if (MemberCtrl.isMembershipExpied(this.member)) {
                errorMessage("Error: Membership expired");
                errorOccured = true;
            }
        }

        if (!errorOccured) {
            exitStage(true);
        }
    }

    private void errorMessage(String error) {
        if (error.startsWith("Error: ")) {
            AlertBox alertBox = new AlertBox();
            alertBox.display("Error!",error,"Close");
        }
    }

    @Override
    public void exitStage(boolean needsConfirmation) {
        ConfirmationBox box;
        if (needsConfirmation) {
            box = new ConfirmationBox(
                    "Rent book",
                    "Title: " + this.book.getTitle() + "\n" +
                            "Author: " + this.book.getAuthor() + "\n" +
                            "Will be rented to \n" +
                            "Member: " + this.member.getName() + "\n\n" +
                            "Is that correct?",
                    "Yes",
                    "No"
            );
            if (box.display()) {
                BookCtrl.rentBook(this.book,this.member);
                stage.close();
            }
        }
        else {
            box = new ConfirmationBox(
                    "Exit \"Rent book\" menu",
                    "Are you sure you want to close this menu?",
                    "Yes",
                    "No"
            );
            if (box.display()) {
                stage.close();
            }
        }


    }
}
