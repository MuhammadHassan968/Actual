//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;
//
//public class Mainly extends Application {
//    @Override
//    public void start(Stage stage) {
//        Label label = new Label("Hello, JavaFX!"); // Simple text
//        Scene scene = new Scene(label, 300, 200); // Window size
//        stage.setScene(scene);
//        stage.setTitle("My First JavaFX App");
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args); // Starts JavaFX
//    }
//}


// TO DO LIST
/*
1. change icon
2. x,y coordinate
3.
 */

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
//import javafx.sc
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.Scene;


public class Practice extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage stage) throws Exception {
//      Stage stage1 = new Stage();
        Group root = new Group();
//        Scene scene = new Scene (root, Color.DARKBLUE);
        Scene scene = new Scene(root, 800,600,Color.LIGHTBLUE);   // length width can also be given through scene
        Image icon = new Image("Fitness App Logo.jpg");

        stage.setTitle("Fitness Panda");
//        stage.setWidth(800);
//        stage.setHeight(600);
        stage.centerOnScreen();
        stage.setResizable(false);
/*
        stage.setX(800);    ==> for manually setting things on the screen
        stage.setY(600);

        stage.setFullScreen(true);    // full screen features
        stage.setFullScreenExitHint("YOU CAN'T ESCAPE unless you press \"L\"");
        stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("L"));
*/
        Text text = new Text();
        text.setText("Welcome to" +
                "\nFitness Panda" +
                "\nBe fit, don't be a panda");
        text.setX(100);
        text.setY(100);
        text.setFont(Font.font("Times New Roman" , 20));
        text.setFill(Color.NAVY);

        Line line = new Line();
        line.setStartX(400);
        line.setStartY(400);
        line.setEndX(500);
        line.setEndY(500);
        line.setStroke(Color.RED);
        line.setStrokeWidth(15);
        line.setOpacity(0.5);   // 50%
        line.setRotate(45);

        Rectangle rect = new Rectangle();
        rect.setX(300);
        rect.setY(300);
        rect.setFill(Color.PINK);
        rect.setStroke(Color.ORANGE);
        rect.setHeight(50);
        rect.setWidth(50);
        rect.setStrokeWidth(5);
        rect.setOpacity(0.5);
        rect.setRotate(45);

        Polygon triangle = new Polygon();
        triangle.setLayoutX(400);
        triangle.setLayoutY(400);
        triangle.setFill(Color.YELLOW);
        triangle.setStroke(Color.PURPLE);
        triangle.setStrokeWidth(12);
        triangle.getPoints().setAll(
                100.0, 50.0,  // Vertex 1 (x, y)
                200.0, 150.0, // Vertex 2 (x, y)
                50.0, 150.0   // Vertex 3 (x, y)
        );

        Circle circle = new Circle();
        circle.setCenterX(250);
        circle.setCenterY(250);
        circle.setRadius(50);
        circle.setFill(Color.YELLOW);
        circle.setStroke(Color.ORANGE);
        circle.setStrokeWidth(10);


        Image image = new Image("MyImage.jpeg");
        ImageView imageView = new  ImageView(image);
        imageView.setX(350);
        imageView.setY(350);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);


        root.getChildren().add(text);
        root.getChildren().add(imageView);
        root.getChildren().add(line);
        root.getChildren().add(rect);
        root.getChildren().add(triangle);
        root.getChildren().add(circle);


        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
}


// for practice
