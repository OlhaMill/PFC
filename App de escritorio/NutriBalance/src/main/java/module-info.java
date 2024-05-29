module org.om.nutribalance {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires annotations;
    requires ical4j.core;
    requires org.json;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.calendarfx.view;

    opens org.om.nutribalance to javafx.fxml;
    opens org.om.nutribalance.model.entities to javafx.base, javafx.fxml, com.fasterxml.jackson.databind;
    opens org.om.nutribalance.controllers to javafx.fxml;
    exports org.om.nutribalance;
    exports org.om.nutribalance.util;
    opens org.om.nutribalance.util to javafx.fxml;
}