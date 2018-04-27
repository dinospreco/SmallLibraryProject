package view.GUI.Stages;

import controller.BookCtrl;
import database.BookDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;
import view.GUI.Components.LabelTextFieldCombination;

public class ListAllBooksStage implements Stages {

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

        stage.setTitle("Books");
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
        TableView<Book> books = new TableView<>();

        TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(200);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setMinWidth(200);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, Integer> pubYearColumn = new TableColumn<>("Publication Year");
        pubYearColumn.setMinWidth(50);
        pubYearColumn.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));

        TableColumn<Book, Boolean> isRentedColumn = new TableColumn<>("Rented?");
        isRentedColumn.setMinWidth(50);
        isRentedColumn.setCellValueFactory(new PropertyValueFactory<>("isRented"));

        TableColumn<Book, Boolean> dateRentedColumn = new TableColumn<>("Date Rented");
        dateRentedColumn.setMinWidth(100);
        dateRentedColumn.setCellValueFactory(new PropertyValueFactory<>("dateRented"));

        TableColumn<Book, Boolean> memebersIdBookIsRentedToColumn = new TableColumn<>("Member ID");
        memebersIdBookIsRentedToColumn.setMinWidth(50);
        memebersIdBookIsRentedToColumn.setCellValueFactory(new PropertyValueFactory<>("memebersIdBookIsRentedTo"));

        books.setItems(BookCtrl.getAllBooksForGui());
        books.getColumns().addAll(idColumn,titleColumn,authorColumn,pubYearColumn,isRentedColumn,dateRentedColumn,memebersIdBookIsRentedToColumn);

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
