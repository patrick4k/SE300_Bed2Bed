module com.se.bed2bed.se300_bed2bed {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.se.se300_bed2bed to javafx.fxml;
    exports com.se.se300_bed2bed;
}