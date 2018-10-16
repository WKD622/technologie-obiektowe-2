package pl.edu.agh.iisg.to.javafx.cw1.model;

import java.math.BigInteger;
import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transaction {

	private static final String EMPTY = "";

	private ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
	private StringProperty payee = new SimpleStringProperty();
	private ObjectProperty<Category> category = new SimpleObjectProperty<>();
	private ObjectProperty<BigInteger> inflow = new SimpleObjectProperty<>();

	private Transaction() {
		this(LocalDate.now(), EMPTY, new Category(EMPTY), BigInteger.ZERO);
	}

	public Transaction(LocalDate date, String payee, Category category, BigInteger inflow) {
		setDate(date);
		setPayee(payee);
		setCategory(category);
		setInflow(inflow);
	}

	public final LocalDate getDate() {
		return this.date.get();
	}

	public final void setDate(LocalDate date) {
		this.date.set(date);
	}

	public ObjectProperty<LocalDate> getDateProperty() {
		return date;
	}

	public final String getPayee() {
		return this.payee.get();
	}

	public final void setPayee(String payee) {
		this.payee.set(payee);
	}

	public StringProperty getPayeeProperty() {
		return payee;
	}

	public final Category getCategory() {
		return this.category.get();
	}

	public final void setCategory(Category category) {
		this.category.set(category);
	}

	public ObjectProperty<Category> getCategoryProperty() {
		return this.category;
	}

	public final BigInteger getInflow() {
		return this.inflow.get();
	}

	public final void setInflow(BigInteger inflow) {
		this.inflow.set(inflow);
	}

	public ObjectProperty<BigInteger> getInflowProperty() {
		return this.inflow;
	}

	public static final Transaction newTransaction() {
		return new Transaction();
	}
}
