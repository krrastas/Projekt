/**
 * Created by Kristina on 18.11.2016.
 */
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;

public class Projekt extends Application {
    public ArrayList<ImageView> jupid = new ArrayList();
    public ArrayList<ImageView> korras = new ArrayList<>();

    Stage puzzle = new Stage();
    ImageView pilt1;
    public static int asukoht1;
    public static int asukoht2;

    public static int valgex = 2;
    public static int valgey = 2;
    GridPane grid = new GridPane();
    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox vbox = new VBox();
        Scene login = new Scene(vbox, 300,300);
        puzzle.setScene(login);
        puzzle.show();
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
                korras.add(piltobjekt);


            }
        }
        korras.remove(8);
        korras.add(null);

        grid.setOnMouseClicked(event -> {  //klikkides vahetab nuppe
            Node jupp = (Node)(event.getTarget());
            pilt1 = (ImageView)(event.getTarget());
            new swap(jupp, grid, pilt1, jupid, korras, puzzle);
        });


       ImageView viimane = jupid.remove(8); //kustutab viimase jupi
        Collections.shuffle(jupid);
        jupid.add(null);  //paneb tühja asemele
        int z =0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ImageView pilt = jupid.get(z);
                if (pilt == null) {
                    grid.add(new Rectangle(100, 100), j, i);
                }
                else {
                    grid.add(pilt, j, i);
                }
                z = z+1;
            }

        }
        System.out.println(jupid);
       grid.getChildren().get(8).setOpacity(0);
        //System.out.println(jupid);
        //root.getChildren().add(imageView);
        Scene piltStseen = new Scene(grid, 300, 300);

            submitButton.setOnAction(event -> {
                puzzle.setScene(piltStseen);

        });

    }
}
