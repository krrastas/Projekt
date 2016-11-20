/**
 * Created by Kristina on 18.11.2016.
 */
import javafx.application.Application;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class Projekt extends Application {
    ArrayList<ImageView> jupid = new ArrayList();

    GridPane root = new GridPane();
    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox vbox = new VBox();
        Scene login = new Scene(vbox, 300,300);
        primaryStage.setScene(login);
        primaryStage.show();
        Label pealkiri = new Label("Puzzle mäng");
        Button submitButton = new Button("Alusta");
        vbox.getChildren().addAll(pealkiri, submitButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(5);


        for (int i = 0; i < 3 ; i++) {
            for (int j = 0; j < 3 ; j++) {
                Image image = new Image("Pilt/Pingviinid.jpg" );
                PixelReader reader = image.getPixelReader();
                WritableImage newImage = new WritableImage(reader, j*100, i*100, 100, 100);
                ImageView piltobjekt = new ImageView();
                piltobjekt.setImage(newImage);
                jupid.add(piltobjekt);


            }
        }

        System.out.println(jupid);

        root.setOnMouseClicked(event -> {
            ImageView jupp = (ImageView)(event.getTarget());
            System.out.println(jupp);
        root.getChildren().get(8).setOpacity(0);

                }
        );

        jupid.get(8);

        Collections.shuffle(jupid);

        int z =0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ImageView pilt = jupid.get(z);
                root.add(pilt, j, i);

                z = z+1;
            }

        }

        System.out.println(jupid);
        //root.getChildren().add(imageView);
        Scene piltStseen = new Scene(root, 300, 300);

            submitButton.setOnAction(event -> {
                primaryStage.setScene(piltStseen);

        });

    }

}