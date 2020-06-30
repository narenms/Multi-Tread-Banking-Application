import java.util.Random;

public class Bank implements Runnable {
	
//	Money m;
//	public static final int MAX_CUSTOMERS = 10;
//	public static final int MAX_BANKS = 10;
//	public static final int INITIAL_BALANCE = 0;
//	private Customer[] customers;
//	private Customer[] banks;
//	private Customer[] customers1;
	
	String name;
	int balance;
	Thread bnk;
	Money money;
	Customer customer;
	int requestAmount;
	int countRef;
	int count;
	boolean flag = true;
	
	/**
	 * Bank Constructor.
	 * @param name Bank Name
	 * @param balance Bank balance
	 * @param money Main Thread, used to communicate and deliver messages.
	 * @param size Number of Customers
	 */
	Bank(String name, int balance, Money money, int size){
		this.name = name;
		this.balance = balance;
		this.money=money;
		this.countRef = size;
//		System.out.println(this.name+" bank balance "+this.balance);
	}
	
	/**
	 * First method which runs as soon as the Bank thread is created.
	 */
	public void run(){
		while(true && !flag) {
			
			try {
//				Thread.sleep(new Random().nextInt(1000)+1);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
//			try {
//				{
////					System.out.println("thread is sleeping");
////					this.bnk.sleep(new Random().nextInt(1000)+1);
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//			}//Sorry for that
			
			
		}//end of while.
		
	}//end of run.
	
	/**
	 * Creates a new Thread for each Bank and starts the thread.
	 */
	public void start() {
		this.bnk = new Thread(this);
		bnk.start();
	}
	
	/**
	 * To stop the execution and terminate the threads
	 */
	public void stop() {
		flag = false;
	}

	/**
	 * Method which controls main functionality like approving or denying loan.
	 * @param amount
	 * @param c
	 * @return
	 */
	public String requestLoan(int amount, Customer c) /*throws InterruptedException*/ {
//		{
////			System.out.println("thread is sleeping");
//			Thread.sleep(500);
//		}
		
		this.requestAmount = amount;
		this.customer = c;
		
			if(this.balance > 0) {
				if(requestAmount > this.balance) {
					money.bankResponse(this.name, requestAmount, customer.name, "denies");
					return "denies";
				}
				else {
					this.balance -= requestAmount;
					money.bankResponse(this.name, requestAmount, customer.name, "approves");
					return "approves";
				}
			}
			else {
				money.bankResponse(this.name, requestAmount, customer.name, "denies");
				return "denies";
			}
			
		}
		
//		if(this.balance > 0) {
//			if(requestAmount > this.balance) {
//				return "denies";
//			}
//			else {
////				this.balance -= amount;
//				return "approves";
//			}
//		}
//		else {
//			return "denies";
//		}
		
//	}
	
//	public Bank(int[] bankBalance, int[] customerBalance) {
//		// TODO Auto-generated constructor stub
//		int bb = bankBalance.length;
//		int cb = customerBalance.length;
//		customers = new Customer[cb];
//		banks = new Customer[bb];
//		customers1 = new Customer[cb];
//		
////		for(int i=0; i<bb; i++) {
////			banks[i] = new Customer(bankBalance[i]);
////		}
//		
////		for(int i=0; i<cb; i++) {
////			customers[i] = new Customer(customerBalance[i]);
////		}
////		for(int i=0; i<bb; i++) {
////			customers[i] = new Customer(bankBalance[i]);
////		}
////		
////		for(int i=0; i<cb; i++) {
////			customers1[i] = new Customer(INITIAL_BALANCE);
////		}
//	}//End of Construct
	
//	public void transfer(int fromC, int fromB, int amount) {
//		
//		System.out.println(m.customerName[fromC] + " Asking for loan from " + m.bankName[fromB]);
//		
//		if(amount <= customers[fromC].getBalance() && amount <= customers[fromB].getBalance()) {
//			customers[fromC].withdraw(amount);
//			customers[fromB].withdraw(amount);
//			customers1[fromC].deposit(amount);
//			
//			String message = "%s transfered %d from %s to %s.\n";
//            String threadName = Thread.currentThread().getName();
//            System.out.printf(message, threadName, amount, fromC, fromC);
//		}
//		else {
//			System.out.println("Denied");
//		}
//		
//	}//End of function transfer.
	
//	public int getTotalBalance() {
//		int total = 0;
//		for()
//	}
	
	
	
	
}
