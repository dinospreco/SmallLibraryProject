package view.GUI.Stages;

//TODO List all books
//TODO Lista all members

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Member;

public class MainStage implements Stages{

    @Override
    public Stage getStage() {
        Stage mainStage = new Stage();
        mainStage.setTitle("Simple Library App");

        mainStage.setMinHeight(MIN_HEIGHT);
        mainStage.setMinWidth(MIN_WIDTH);

        mainStage.setScene(buildScene());
        return mainStage;
    }

    @Override
    public Scene buildScene() {
        return new Scene(buildLayout(), WIDTH, HEIGHT);
    }

    @Override
    public VBox buildLayout() {
        VBox layout = new VBox(20);


        Button addBookButton = new Button("Add Book");
        Button rentBookButton = new Button("Rent Book");
        Button returnbookButton = new Button("Return Book");
        Button addMemberButton = new Button("Add Member");
        Button extendMembershipButton = new Button("Extend Membership");
        Button listAllBooksButton = new Button("List all books");
        Button listAllMembersButton = new Button("List all Members");

        layout.getChildren().addAll(addBookButton,rentBookButton,returnbookButton,addMemberButton,extendMembershipButton,listAllBooksButton,listAllMembersButton);
        layout.setAlignment(Pos.CENTER);

        addBookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddBookStage stage = new AddBookStage();
                stage.getStage().show();
            }
        });

        addMemberButton.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddMemberStage stage = new AddMemberStage();
                stage.getStage().show();
            }
        });

        rentBookButton.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RentBookStage stage = new RentBookStage();
                stage.getStage().show();
            }
        });

        returnbookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RetrunBookStage stage = new RetrunBookStage();
                stage.getStage().show();
            }
        });

        extendMembershipButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ExtendMembership stage = new ExtendMembership();
                stage.getStage().show();
            }
        });

        listAllBooksButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ListAllBooksStage stage = new ListAllBooksStage();
                stage.getStage().show();
            }
        });

        listAllMembersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ListAllMembersStage stage = new ListAllMembersStage();
                stage.getStage().show();
            }
        });
        return layout;
    }

    @Override
    public void exitStage(boolean needsConfirmation) {

    }
}
