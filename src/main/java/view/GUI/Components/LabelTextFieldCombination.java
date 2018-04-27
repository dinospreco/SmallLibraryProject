package view.GUI.Components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class LabelTextFieldCombination {

    private String labelText;
    private String textFieldExampleText;
    private TextField textField;

    private int textFieldWidth = 150;
    private int textFieldHeight = 20;

    private Pos fieldBoxAligment = Pos.CENTER_LEFT;
    private Pos labelBoxAligment = Pos.CENTER_RIGHT;

    public LabelTextFieldCombination() {
    }

    public LabelTextFieldCombination(String labelText) {
        this.labelText = labelText;
    }

    public LabelTextFieldCombination(String labelText, String textFieldExampleText) {
        this.labelText = labelText;
        this.textFieldExampleText = textFieldExampleText;
    }

    public HBox draw() {
        HBox draw = new HBox();
        draw.setAlignment(Pos.CENTER);

        HBox labelBox = new HBox();
        labelBox.setAlignment(labelBoxAligment);
        Label label = new Label(labelText);
        labelBox.getChildren().add(label);

        HBox fieldBox = new HBox();
        fieldBox.setAlignment(fieldBoxAligment);
        if (textFieldExampleText == "" || textFieldExampleText == null) {
            this.textField = new TextField();
        }
        else{
            this.textField = new TextField(textFieldExampleText);
        }
        this.textField.setMinSize(textFieldWidth,textFieldHeight);
        fieldBox.getChildren().add(textField);
        fieldBox.setMargin(this.textField, new Insets(0,0,0,5));

        draw.getChildren().addAll(labelBox,fieldBox);
        return draw;
    }

    public String getTextFieldText() {
        return this.textField.getText();
    }

    public void clearText() {
        this.textField.clear();
    }

}
