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
        int asukoht1;
        int asukoht2;
        int y = grid.getRowIndex(jupp);
        int x = grid.getColumnIndex(jupp);
        System.out.println(jupp);
        if ((x + 1 == Projekt.valgex && y == Projekt.valgey) || //kui valge on paremal
                (x == Projekt.valgex && y + 1 == Projekt.valgey) || //kui valge on all
                (x - 1 == Projekt.valgex && y == Projekt.valgey) || //kui valge on vasakul
                (x == Projekt.valgex && y - 1 == Projekt.valgey)) {
            //System.out.println("valge on kõrval");

            asukoht2 = jupid.indexOf(null); //tühja jupi indeks ArrayListis
            asukoht1 = jupid.indexOf(pilt1); //klikitud jupi indeks ArrayListis
            jupid.set(asukoht2, pilt1); //paneb tühja indeksi asemele jupi indeksi
            jupid.set(asukoht1, null); //teeb tühjaks jupi endise asukoha

            grid.getChildren().remove(jupp); //kustutab jupi ära sealt, kus klikiti
            grid.add(jupp, Projekt.valgex, Projekt.valgey); //paneb jupi valge positsioonile
            Rectangle rect = new Rectangle(100, 100);
            rect.setOpacity(0);
            grid.add(rect, x, y); //paneb läbipaistva ruudu valge kohale
            Projekt.valgex = x;
            Projekt.valgey = y;

            new Kontroll(jupid, korras, puzzle);
        }
    }
}