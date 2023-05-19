module app.zaidiboussebata {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens app.zaidiboussebata to javafx.fxml;
    exports app.zaidiboussebata;
}