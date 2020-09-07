package com.example;

import com.example.component.LoadForm;
import com.example.component.Performer;
import com.example.component.SaveForm;
import com.example.dto.DataTask;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

/**
 * The main view contains a button and a click listener.
 */
@Route
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends VerticalLayout {
    ComboBox<String> comboBox = new ComboBox<>();

    Button btnCalc = new Button("Посчитать");
    Button btnSave = new Button("Сохранить");
    Button btnLoad = new Button("Загрузить");

    TextField textOut = new TextField();
    TextField textField1 = new TextField();
    TextField textField2 = new TextField();

    HorizontalLayout h = new HorizontalLayout();

    public MainView() {
        comboBox.setItems("Задача 1", "Задача 2");
        comboBox.setLabel("Задача");
        comboBox.setValue("Задача 1");
        comboBox.setWidth("135px");

        btnCalc.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnLoad.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        textOut.setLabel("Результата");
        textOut.setWidth("500px");
        textOut.setReadOnly(true);

        textField1.setLabel("Условие 1");
        textField1.setWidth("500px");
        textField2.setLabel("Условие 2");
        textField2.setWidth("500px");


        comboBox.addValueChangeListener(event -> {
            if (comboBox.getValue().equals("Задача 1")) {
                textField1.setPattern("");
                textField1.setPreventInvalidInput(false);
                textOut.setValue("");
                textField1.setLabel("Условие 1");
                textField1.setValue("");
                textField2.setVisible(true);
                textField2.setValue("");
            } else if (comboBox.getValue().equals("Задача 2")) {
                textOut.setValue("");
                textField1.setPattern("\\d*");
                textField1.setPreventInvalidInput(true);
                textField1.setLabel("Условие");
                textField1.setValue("");
                textField2.setVisible(false);
                textField2.setValue("");
            }
        });

        btnCalc.addClickListener(event -> {
            if (comboBox.getValue().equals("Задача 1")) {
                textOut.setValue(Performer.task(
                        textField1.getValue(),
                        textField2.getValue()));
            } else if (comboBox.getValue().equals("Задача 2")) {

                if (textField1.getValue().equals("")) {
                    textField1.setValue("0");
                }
                textOut.setValue(Performer.task(
                        textField1.getValue()));
            }
        });

        btnSave.addClickListener(event -> {
            SaveForm saveForm = new SaveForm(new DataTask(
                    comboBox.getValue(),
                    textField1.getValue(),
                    textField2.getValue()));
            saveForm.open();
        });

        btnLoad.addClickListener(event -> {
            LoadForm loadForm = new LoadForm(this);
            loadForm.open();
        });

        h.setDefaultVerticalComponentAlignment(Alignment.END);
        h.add(comboBox, btnCalc, btnLoad, btnSave);
        add(h, textOut, textField1, textField2);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    public void update(DataTask dataTask) {
        String type = dataTask.getType();
        comboBox.setValue(type);
        textField1.setValue(dataTask.getInputData1());
        if (type.equals("Задача 1")) {
            textField2.setValue(dataTask.getInputData2());
        }
    }
}
