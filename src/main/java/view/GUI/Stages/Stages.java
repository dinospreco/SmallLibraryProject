package view.GUI.Stages;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public interface Stages {

    static final int WIDTH = 300;
    static final int HEIGHT = 400;
    static final int MIN_WIDTH = 300;
    static final int MIN_HEIGHT = 400;

    public Stage getStage();
    public Scene buildScene();
    public VBox buildLayout();
    public void exitStage(boolean n);
}
