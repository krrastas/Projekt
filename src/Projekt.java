/**
 * Created by Kristina on 18.11.2016.
 */
import javafx.application.Application;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sun.swing.MenuItemLayoutHelper;

import java.util.ArrayList;
import java.util.Collections;

public class Projekt extends Application {
    ArrayList<ImageView> jupid = new ArrayList();
    int valgex = 2;
    int valgey = 2;

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
            Node jupp = (Node)(event.getTarget());
            swap(jupp);
        });





        ImageView viimane = jupid.remove(8);

        Collections.shuffle(jupid);
        jupid.add(null);

        int z =0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ImageView pilt = jupid.get(z);
                if (pilt == null) {
                    root.add(new Rectangle(100, 100), j, i);
                }
                else {
                    root.add(pilt, j, i);
                }
                z = z+1;
            }

        }

        root.getChildren().get(8).setOpacity(0);
        //System.out.println(jupid);
        //root.getChildren().add(imageView);
        Scene piltStseen = new Scene(root, 300, 300);

            submitButton.setOnAction(event -> {
                primaryStage.setScene(piltStseen);

        });

    }

    public void swap(Node jupp) {
        int y = root.getRowIndex(jupp);
        int x = root.getColumnIndex(jupp);
        System.out.println(jupp);
        if ((x + 1 == valgex && y == valgey) || //kui valge on paremal
                (x == valgex && y + 1 == valgey) || //kui valge on all
                    (x - 1 == valgex && y == valgey) || //kui valge on vasakul
                        (x == valgex && y - 1 == valgey) //kui valge on üleval){
            System.out.println("valge on kõrval");
            root.getChildren().remove(jupp);
            root.add(jupp, valgex, valgey);
            Rectangle rect = new Rectangle(100, 100);
            rect.setOpacity(0);
            root.add(rect, x, y);
            valgex = x;
            valgey = y;
        }
    }

}
