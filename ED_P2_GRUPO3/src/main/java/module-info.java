module ec.edu.espol.ed_p2_grupo3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.ed_p2_grupo3 to javafx.fxml;
    exports ec.edu.espol.ed_p2_grupo3;
}
