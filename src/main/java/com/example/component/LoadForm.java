package com.example.component;

import com.example.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.io.File;

public class LoadForm extends Dialog {
    Button btnOK = new Button("OK");
    Button btnDel = new Button("Удалить");
    Button btnEsc = new Button("Отмена");

    Grid<String> grid = new Grid<String>();
    MainView mainView;

    public LoadForm(MainView mainView){
        this.mainView = mainView;

        btnOK.setWidth("135px");
        btnDel.setWidth("135px");
        btnEsc.setWidth("135px");

        grid.setItems(Performer.getListFiles());
        grid.addColumn(String::valueOf).setHeader("Сохранения");

        btnOK.addClickListener(buttonClickEvent -> {
            String filename = grid.asSingleSelect().getValue();
            if(filename != null) {
                mainView.update(Performer.load(filename));
                this.close();
            }
        });

        btnDel.addClickListener(buttonClickEvent -> {
            String filename = grid.asSingleSelect().getValue();
            if (filename != null) {
                File file = new File("saves\\"+filename);
                file.delete();
                grid.setItems(Performer.getListFiles());
            }
        });

        btnEsc.addClickListener(buttonClickEvent -> {
            this.close();
        });

        HorizontalLayout h = new HorizontalLayout(btnOK, btnDel, btnEsc);
        h.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        h.setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);
        h.setWidth("500px");

        VerticalLayout v = new VerticalLayout(grid, h);
        v.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        v.setHeight("300px");
        v.setWidth("500px");
        add(v);
    }
}
