module net.JeuxDeMob {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;

    opens net.JeuxDeMob to javafx.fxml;
    exports net.JeuxDeMob;
}