module edu.westga.cs1302.P1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
	requires jdk.internal.le;

    opens edu.westga.cs1302.P1.views to javafx.fxml;
    exports edu.westga.cs1302.P1;
}
