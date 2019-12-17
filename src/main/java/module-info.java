module net.JeuxDeMob {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	requires java.desktop;

    opens net.JeuxDeMob to javafx.fxml;
    exports net.JeuxDeMob;
}