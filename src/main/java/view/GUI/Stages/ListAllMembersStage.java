package view.GUI.Stages;

import controller.BookCtrl;
import controller.MemberCtrl;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;
import model.Member;

import java.sql.Date;

public class ListAllMembersStage implements Stages {

    private static Stage stage;

    @Override
    public Stage getStage() {
        stage = new Stage();

        stage.setOnCloseRequest(e -> {
            e.consume();
            exitStage(false);
        });

        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);

        stage.setTitle("Members");
        stage.setScene(buildScene());
        return stage;

    }

    @Override
    public Scene buildScene() {
        return new Scene(buildLayout(), 600, 600);
    }

    @Override
    public VBox buildLayout() {
        VBox layout = new VBox(20);
        TableView<Member> books = new TableView<>();

        TableColumn<Member, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Member, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Member, String> idDocNumberColumn = new TableColumn<>("ID Doc Number");
        idDocNumberColumn.setMinWidth(100);
        idDocNumberColumn.setCellValueFactory(new PropertyValueFactory<>("idDocNumber"));

        TableColumn<Member, Date> membershipExpirationDateColumn = new TableColumn<>("Membership expiration");
        membershipExpirationDateColumn.setMinWidth(100);
        membershipExpirationDateColumn.setCellValueFactory(new PropertyValueFactory<>("membershipExpirationDate"));



        books.setItems(MemberCtrl.getAllMemberssForGui());
        books.getColumns().addAll(idColumn,nameColumn,idDocNumberColumn,membershipExpirationDateColumn);

        //Close Button
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            exitStage(false);
        });

        layout.getChildren().addAll(books,closeButton);
        layout.setAlignment(Pos.CENTER);

        return layout;
    }

    @Override
    public void exitStage(boolean n) {
        stage.close();
    }

}
