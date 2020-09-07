package com.example.component;

import com.example.dto.DataTask;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class SaveForm extends Dialog {
    TextField textField = new TextField();

    Button btnOK = new Button("OK");
    Button btnEsc = new Button("Отмена");


    public SaveForm(DataTask dataTask) {

        textField.setLabel("Название сохранения");

        btnOK.addClickListener(buttonClickEvent -> {
            Performer.save(textField.getValue(), dataTask);
            this.close();
        });

        btnEsc.addClickListener(buttonClickEvent -> {
            this.close();
        });

        HorizontalLayout horizontalLayout = new HorizontalLayout(textField, btnOK, btnEsc);
        horizontalLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.END);
        horizontalLayout.setHeight("100%");
        horizontalLayout.setWidth("400px");
        add(horizontalLayout);
    }
}
