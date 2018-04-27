package view.GUI.Stages;

import controller.BookCheck;
import controller.BookCtrl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Book;
import view.GUI.Components.AlertBox;
import view.GUI.Components.ConfirmationBox;
import view.GUI.Components.LabelTextFieldCombination;


public class AddBookStage implements Stages, EventHandler<ActionEvent>{

    private static Stage stage;
    private LabelTextFieldCombination title;
    private LabelTextFieldCombination author;
    private LabelTextFieldCombination isbn;
    private LabelTextFieldCombination publicationYear;

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

        stage.setTitle("Add a book");
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

        title = new LabelTextFieldCombination("Title: ");
        author = new LabelTextFieldCombination("Author: ");
        isbn = new LabelTextFieldCombination("ISBN: ");
        publicationYear = new LabelTextFieldCombination("Publication Year: ");

        //Add book button
        Button addBook = new Button("Add Book");
        addBook.setOnAction(this);

        //Close Button
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            exitStage(false);
        });

        layout.getChildren().addAll(title.draw(),author.draw(),isbn.draw(),publicationYear.draw(),addBook,closeButton);
        layout.setAlignment(Pos.CENTER);

        return layout;
    }

    @Override
    public void handle(ActionEvent event) {
        String title = BookCheck.checkTitle(this.title.getTextFieldText());
        String author = BookCheck.checkAuthor(this.author.getTextFieldText());
        String isbn = BookCheck.checkIsbn(this.isbn.getTextFieldText());
        String publicationYear = BookCheck.checkPublicationYear(this.publicationYear.getTextFieldText());
        boolean errorOccured = false;

        if (title.startsWith("Error")) {
            errorMessage(title);
            errorOccured = true;
        }
        else if (author.startsWith("Error")) {
            errorMessage(author);
            errorOccured = true;
        }
        else if (isbn.startsWith("Error")) {
            errorMessage(isbn);
            errorOccured = true;
        }
        else if (publicationYear.isEmpty()) {
            errorMessage("Error: Publication year is missing!");
            errorOccured = true;
        }
        else if (publicationYear.startsWith("Error")) {
            errorMessage(publicationYear);
            errorOccured = true;
        }
        else if (BookCtrl.getBookByIsbn(isbn) != null) {
            errorMessage("Error: Book with ISBN: " + isbn + " already Exists");
            errorOccured = true;
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
                    "Book " + this.title.getTextFieldText(),
                    "Title: " + title.getTextFieldText() + "\n" +
                            "Author: " + author.getTextFieldText() + "\n" +
                            "ISBN: " + isbn.getTextFieldText() + "\n" +
                            "Publication year: " + publicationYear.getTextFieldText() + "\n\n" +
                    "Are you sure you want to add this book?",
                    "Yes",
                    "No"
            );
            if (box.display()) {
                Book book = new Book(
                        title.getTextFieldText(),
                        author.getTextFieldText(),
                        isbn.getTextFieldText(),
                        Integer.parseInt(publicationYear.getTextFieldText())
                );
                BookCtrl.addBook(book);
                stage.close();
            }
        }
        else {
            box = new ConfirmationBox(
                    "Exit \"Add book\" menu",
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
