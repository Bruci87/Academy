module org.example.academy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;

    opens org.example.academy to javafx.fxml;
    opens visao to javafx.fxml;
    exports org.example.academy;
    exports visao to javafx.graphics;
}
