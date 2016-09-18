package cysewska.com.controllers;

import cysewska.com.models.dto.TextileDTO;
import cysewska.com.models.entities.TextileEntity;
import cysewska.com.models.enums.Colors;
import cysewska.com.repositories.TextilRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ola on 2016-09-10.
 */
@Component
public class TextileViewImp {
    @Autowired
    MainView mainView;
    @Autowired
    TextilRepository textilRepository;

    public TableColumn name, colors, textileQuantity, textileThickness;

    public void createTableName() {

        this.name = new TableColumn("name");
        this.colors = new TableColumn("colors");
        this.textileQuantity = new TableColumn("textileQuantity");
        this.textileThickness = new TableColumn("textileThickness");
    }

    public void setTableName() {
        name.setCellValueFactory(new PropertyValueFactory<TextileDTO, String>("name"));
        colors.setCellValueFactory(new PropertyValueFactory<TextileDTO, Colors>("colors"));
        textileQuantity.setCellValueFactory(new PropertyValueFactory<TextileDTO, Integer>("textileQuantity"));
        textileThickness.setCellValueFactory(new PropertyValueFactory<TextileDTO, Integer>("textileThickness"));
        this.mainView.getTableTextile().getColumns().addAll(name, colors, textileQuantity, textileThickness);
    }

    public void fillTableData() {

        List<TextileEntity> textileEntities = textilRepository.findAll();
        System.out.println(textileEntities);
        List<TextileDTO> textileDTOs = textileEntities.stream().map(textileEntity -> new TextileDTO(textileEntity.getName(), textileEntity.getColors(),
                textileEntity.getTextileQuantity(), textileEntity.getTextileThickness())).collect(Collectors.toList());
        mainView.getTableTextile().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<TextileDTO> data;
        data = FXCollections.observableArrayList(textileDTOs);
        mainView.getTableTextile().setItems(data);

    }
}
