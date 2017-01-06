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
    public ArrayList<ImageView> kontroll = new ArrayList<>();

    Stage puzzle = new Stage();
    ImageView pilt1;
    int asukoht1;
    int asukoht2;

    int valgex = 2;
    int valgey = 2;
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
            swap(jupp);
        });





       ImageView viimane = jupid.remove(8); //kustutab viimase jupi
        Collections.shuffle(jupid);
        jupid.add(null);  //paneb tühja asemele
        kontroll = jupid;
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

    public void swap(Node jupp) {
        int y = grid.getRowIndex(jupp);
        int x = grid.getColumnIndex(jupp);
        System.out.println(jupp);
        if ((x + 1 == valgex && y == valgey) || //kui valge on paremal
                (x == valgex && y + 1 == valgey) || //kui valge on all
                    (x - 1 == valgex && y == valgey) || //kui valge on vasakul
                        (x == valgex && y - 1 == valgey)){
            //System.out.println("valge on kõrval");

            asukoht2 = kontroll.indexOf(null);
            asukoht1 = kontroll.indexOf(pilt1);
            kontroll.set(asukoht2, pilt1);
            kontroll.set(asukoht1, null);

            grid.getChildren().remove(jupp);
            grid.add(jupp, valgex, valgey);
            Rectangle rect = new Rectangle(100, 100);
            rect.setOpacity(0);
            grid.add(rect, x, y);
            valgex = x;
            valgey = y;
            asukoht2 = asukoht1; //muudab valge asukohaks pildi eelmise asukoha

            if (kontroll.equals(korras)){

                Image image1 = new Image("Pilt/Pingviinid.jpg" );
                ImageView pic = new ImageView();
                pic.setImage(image1);
                VBox vbox2 = new VBox();
                vbox2.getChildren().add(pic);
                Scene korraspilt = new Scene(vbox2, 300, 300);
                puzzle.setScene(korraspilt);
            }
        }

    }
}
