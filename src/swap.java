import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Kristina on 6.01.2017.
 */
public class swap {
    public swap(Node jupp, GridPane grid, ImageView pilt1, ArrayList<ImageView> jupid, ArrayList<ImageView> korras, Stage puzzle) {

        int y = grid.getRowIndex(jupp);
        int x = grid.getColumnIndex(jupp);
        System.out.println(jupp);
        if ((x + 1 == Projekt.valgex && y == Projekt.valgey) || //kui valge on paremal
                (x == Projekt.valgex && y + 1 == Projekt.valgey) || //kui valge on all
                (x - 1 == Projekt.valgex && y == Projekt.valgey) || //kui valge on vasakul
                (x == Projekt.valgex && y - 1 == Projekt.valgey)) {
            //System.out.println("valge on kõrval");

            Projekt.asukoht2 = jupid.indexOf(null);
            Projekt.asukoht1 = jupid.indexOf(pilt1);
            jupid.set(Projekt.asukoht2, pilt1);
            jupid.set(Projekt.asukoht1, null);

            grid.getChildren().remove(jupp);
            grid.add(jupp, Projekt.valgex, Projekt.valgey);
            Rectangle rect = new Rectangle(100, 100);
            rect.setOpacity(0);
            grid.add(rect, x, y);
            Projekt.valgex = x;
            Projekt.valgey = y;
            Projekt.asukoht2 = Projekt.asukoht1; //muudab valge asukohaks pildi eelmise asukoha

            new Kontroll(jupid, korras, puzzle);
        }
    }
}