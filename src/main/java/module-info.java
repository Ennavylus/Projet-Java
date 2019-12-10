module net.JeuxDeMob {
    requires javafx.controls;
    requires javafx.fxml;

    opens net.JeuxDeMob to javafx.fxml;
    exports net.JeuxDeMob;
}