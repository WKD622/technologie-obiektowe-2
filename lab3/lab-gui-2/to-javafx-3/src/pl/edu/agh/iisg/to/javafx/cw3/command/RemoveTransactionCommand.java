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
		// TODO Auto-generated method stub

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return "New transaction: " + transactionToRemove.toString();
	}
}
