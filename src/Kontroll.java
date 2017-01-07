import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Kristina on 6.01.2017.
 */
public class Kontroll {
    public Kontroll(ArrayList jupid, ArrayList korras, Stage puzzle) {

        if (jupid.equals(korras)) {
            Image image1 = new Image("Pilt/Pingviinid.jpg");
            ImageView pic = new ImageView(); //et ta näitaks pilti
            pic.setImage(image1); //et ta näitaks pingviinide pilti
            VBox vbox2 = new VBox();
            vbox2.getChildren().add(pic);
            Scene korraspilt = new Scene(vbox2, 300, 300);
            puzzle.setScene(korraspilt);
        }
    }
}
