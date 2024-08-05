package com.licretey.demo01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @Description:
 * @Date: 2024/8/5 14:01
 */
public class FxMain extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label("Hello World");
        BorderPane pane = new BorderPane(label);

        Scene scene = new Scene(pane, 300, 200);

        stage.setScene(scene);
        stage.setTitle("JavaFxDemo01");
        stage.show();
    }
}
