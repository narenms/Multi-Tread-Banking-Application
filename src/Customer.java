import java.util.Random;
import java.util.*;

public class Customer implements Runnable{
	
	/**
	 * Customer Class.
	 * New Thread for each customer.
	 * Attributes:
	 * Name, Balance, reference (just keep the reference of total loan required by the customer), and list of banks to keep track
	 * which bank threads to call.
	 */
	
	String name;
	int balance;
	int reference;
	Thread cust;
	Customer customer;
	Bank bank;
	Money money;
	List<Bank> availBanks;
	int countRef;
	int count;
	boolean flag = true;
	
	/**
	 * Customer Constructor. 
	 * @param name: Customer Name.
	 * @param balance: Customer Loan amount which is used to keep track of loan required.
	 * @param reference: Total Loan amount required, just to keep track of the objective.
	 * @param availBanks: List of banks whose threads can be called.
	 * @param money: Main thread, used to communicate and deliver messages.
	 * @param size: Number of customers.
	 */
	public Customer(String name, int balance, int reference, ArrayList<Bank> availBanks, Money money, int size) {
		this.name = name;
		this.balance = balance;
		this.reference = reference;
		this.availBanks = availBanks;
		this.money = money;
		this.countRef = size;
	}
	
	/**
	 * First method to run when the customer thread is created.
	 */
	public void run(){
		
		this.requestLoan();
		while(this.balance > 0 && !flag) {
			try {
//				Thread.sleep(new Random().nextInt(100)+1);
			} catch (Exception e) {
				// TODO: handle exception
			}
//			System.out.println("customer yet stopped");
//			if(count == countRef) {
//				System.out.println("customer stopped");
//			}
		}
		
//		System.out.println("customer stopped");
		
	}
	
	/**
	 * Creates new threads for each customer and starts the thread.
	 */
	public void start() {
		this.cust = new Thread(this);
		cust.start();
	}
	
	/**
	 * To stop the execution and terminate the threads
	 */
	public void stop() {
		flag = false;
	}
	
	/**
	 * Method to request loan from the bank.
	 * Controls main functionality of the process like requesting for loan.
	 */
	public void requestLoan() /*throws InterruptedException*/ {
//		
		try {
			cust.sleep(new Random().nextInt(100)+1);
		} catch (Exception e) {
			// TODO: handle exception
		}
//		Thread.sleep(new Random().nextInt(100)+1);
		int amount;
		if(this.balance > 50) {
			amount = new Random().nextInt(50)+1;
		}
		else {
			amount = this.balance;
		}
		
		bank = availBanks.get(new Random().nextInt(availBanks.size()));
		
		money.requestLoan(this.name, amount, bank.name);
		
		String result = bank.requestLoan(amount, this);
//		System.out.println("i am getting result as "+result);
		
		if(result.equals("approves")) {
			this.balance -= amount;
//			System.out.println("result from bank in customer "+result+" available banks "+this.availBanks.size());
		} else if(result.equals("denies")) {
			this.availBanks.remove(bank);
//			System.out.println("result from bank in customer "+result+ "available banks "+this.availBanks.size());
		}
		if(availBanks.size()==0 || this.balance==0) {
			if(this.balance > 0) {
//				String final1 = "was only able to borrow so much";
				// count++;
				money.loanStatus("no", this.name, this.reference - this.balance);
				flag = true;
			}
			else {
//				String final1 = "reached objective";
				// count++;
				money.loanStatus("yes", this.name, this.reference);
				flag = true;
			}
		}
		else {
			if(this.balance > 0) {
				requestLoan();
			}
		}
	}//end of method requestLoan.
	
//	public void deposit(int amount) {
//		this.balance += amount;
//	}
	
	public int getBalance(){
		return this.balance;
	}
	
	
	
}
