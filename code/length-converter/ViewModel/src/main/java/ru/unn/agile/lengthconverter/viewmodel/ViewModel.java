package ru.unn.agile.lengthconverter.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.lengthconverter.model.LengthConverte;
import ru.unn.agile.lengthconverter.model.LengthUnit;

public class ViewModel {

    private final ObjectProperty<ObservableList<LengthUnit>> units =
            new SimpleObjectProperty<>(
                    FXCollections.observableArrayList(LengthUnit.values()));
    private final StringProperty convertFrom = new SimpleStringProperty();
    private final StringProperty convertTo = new SimpleStringProperty();
    private final ObjectProperty <LengthUnit> unitFrom = new SimpleObjectProperty<LengthUnit>();
    private final ObjectProperty <LengthUnit> unitTo = new SimpleObjectProperty<LengthUnit>();
    private final StringProperty status = new SimpleStringProperty();

    public ViewModel() {
        init();
    }

    private void init() {
        convertFrom.set("");
        convertTo.set("");
        unitFrom.set(LengthUnit.MILLIMETERS);
        unitTo.set(LengthUnit.METERS);
        status.set(Status.READY.toString());

    }

    public ObjectProperty<ObservableList<LengthUnit>> unitsProperty() {
        return units;
    }

    public final ObservableList<LengthUnit> getUnits() {
        return units.get();
    }

    public StringProperty convertFromProperty() {
        return convertFrom;
    }

    public StringProperty convertToProperty() {
        return convertTo;
    }


    public LengthUnit getUnitTo() {
        return unitTo.get();
    }

    public final String getConvertTo() {
        return convertTo.get();
    }

    public final String getStatus() {
        return status.get();
    }

    public void checkInputValues() {
        if (convertFrom.get().isEmpty()) {
            status.set(Status.WAITING.toString());
        }
        try {
            if (!convertFrom.get().isEmpty()) {
                Double.parseDouble(convertFrom.get());
                status.set(Status.READY.toString());
            }
        } catch (NumberFormatException e) {
            status.set(Status.BAD_FORMAT.toString());
        }
    }
    public void convert() {
        checkInputValues();
        if (status.get() != Status.READY.toString()) {
            return;
        }

        try {
            double valueToConvert = Double.parseDouble(convertFrom.get());
            double result = LengthConverte.convert(unitFrom.get(),valueToConvert, unitTo.get());

            convertTo.set(String.valueOf(result));
            status.set(Status.SUCCESS.toString());
        } catch (Exception e) {
            status.set(Status.ERROR.toString());
        }
    }
}
