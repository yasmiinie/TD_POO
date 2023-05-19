module app.zaidiboussebata {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.zaidiboussebata to javafx.fxml;
    exports app.zaidiboussebata;
    exports app.zaidiboussebata.Control;
    opens app.zaidiboussebata.Control to javafx.fxml;
}