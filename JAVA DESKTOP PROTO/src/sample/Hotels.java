package sample;

import javafx.scene.control.Button;

public class Hotels
{
    private String name;
    private String localization;
    private String price;
    private Button button;

    public Hotels(String name, String localization, String price) {
        this.name = name;
        this.localization = localization;
        this.price = price;
        this.button = new Button("reserve");
    }

    public String getName() {
        return name;
    }

    public String getLocalization() {
        return localization;
    }
    public String getPrice() {
        return price;
    }
    public Button getButton() {
        return button; }
}
