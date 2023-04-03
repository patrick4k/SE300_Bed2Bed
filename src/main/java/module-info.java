module com.se.bed2bed.se300_bed2bed {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires amadeus.java;

    opens com.se.se300_bed2bed to javafx.fxml;
    exports com.se.se300_bed2bed;

    opens com.se.se300_bed2bed.scenes to javafx.fxml;
    exports com.se.se300_bed2bed.scenes;
}