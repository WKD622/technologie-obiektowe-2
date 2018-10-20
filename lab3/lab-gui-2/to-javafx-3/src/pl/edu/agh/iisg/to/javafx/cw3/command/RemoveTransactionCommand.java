package pl.edu.agh.iisg.to.javafx.cw3.command;

import pl.edu.agh.iisg.to.javafx.cw3.model.Account;
import pl.edu.agh.iisg.to.javafx.cw3.model.Transaction;

public class RemoveTransactionCommand implements Command {

	private Transaction transactionToRemove;
	private Account account;

	public RemoveTransactionCommand(Transaction transactionToRemove, Account account) {
		this.transactionToRemove = transactionToRemove;
		this.account = account;
	}

	@Override
	public void execute() {
		account.removeTransaction(transactionToRemove);

	}

	@Override
	public void undo() {
		account.addTransaction(transactionToRemove);

	}

	@Override
	public void redo() {
		account.removeTransaction(transactionToRemove);
	}

	@Override
	public String getName() {
		return "New transaction: " + transactionToRemove.toString();
	}
}
