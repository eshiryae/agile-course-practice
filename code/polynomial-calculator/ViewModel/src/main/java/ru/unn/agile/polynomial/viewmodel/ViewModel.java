package ru.unn.agile.polynomial.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.polynomial.model.Polynomial;

public class ViewModel {
    private StringProperty firstPolynomialStr = new SimpleStringProperty();
    private StringProperty secondPolynomialStr = new SimpleStringProperty();
    private StringProperty resultStr = new SimpleStringProperty();

    public StringProperty firstPolynomialStrProperty() {
        return firstPolynomialStr;
    }

    public String getFirstPolynomialStr() {
        return firstPolynomialStr.get();
    }

    public void setFirstPolynomialStr(final String firstPolynomialStr) {
        this.firstPolynomialStr.set(firstPolynomialStr);
    }

    public StringProperty secondPolynomialStrProperty() {
        return secondPolynomialStr;
    }

    public String getSecondPolynomialStr() {
        return secondPolynomialStr.get();
    }

    public void setSecondPolynomialStr(final String secondPolynomialStr) {
        this.secondPolynomialStr.set(secondPolynomialStr);
    }

    public StringProperty resultStrProperty() {
        return resultStr;
    }

    public String getResultStr() {
        return resultStr.get();
    }

    public void setResultStr(final String resultStr) {
        this.resultStr.set(resultStr);
    }

    public ViewModel() {
        initDefaultFields();
    }

    public Polynomial parsePolynomial(final String polynomialStrSource) {
        PolynomialParser parser = new PolynomialParser(polynomialStrSource);
        return parser.parsePolynomial();
    }

    public void add() {
        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();
        try {
            p1 = parsePolynomial(getFirstPolynomialStr());
        } catch (ViewModelException exc) {
            setResultStr(PolynomialParser.FORMAT_ERROR + "1");
            return;
        }
        try {
            p2 = parsePolynomial(getSecondPolynomialStr());
        } catch (ViewModelException exc) {
            setResultStr(PolynomialParser.FORMAT_ERROR + "2");
            return;
        }
        setResultStr(p1.add(p2).toString());
    }

    public void subtract() {
        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();
        try {
            p1 = parsePolynomial(getFirstPolynomialStr());
        } catch (ViewModelException exc) {
            setResultStr(PolynomialParser.FORMAT_ERROR + "1");
            return;
        }
        try {
            p2 = parsePolynomial(getSecondPolynomialStr());
        } catch (ViewModelException exc) {
            setResultStr(PolynomialParser.FORMAT_ERROR + "2");
            return;
        }
        setResultStr(p1.subtract(p2).toString());
    }

    public void multiply() {
        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();
        try {
            p1 = parsePolynomial(getFirstPolynomialStr());
        } catch (ViewModelException exc) {
            setResultStr(PolynomialParser.FORMAT_ERROR + "1");
            return;
        }
        try {
            p2 = parsePolynomial(getSecondPolynomialStr());
        } catch (ViewModelException exc) {
            setResultStr(PolynomialParser.FORMAT_ERROR + "2");
            return;
        }
        setResultStr(p1.multiply(p2).toString());
    }

    private void initDefaultFields() {
        firstPolynomialStr.set("");
        secondPolynomialStr.set("");
        resultStr.set("");
    }
}
