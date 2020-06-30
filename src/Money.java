import java.util.*;
import java.io.*;

public class Money implements Runnable {
	
	String[] bankName;
	String[] customerName;
	int[] bankBalance;
	int[] customerBalance;
	Bank banks[];
	Customer customers[];
	static ArrayList<String> barr = new ArrayList<String>();
	static ArrayList<String> carr = new ArrayList<String>();
	int count = 0;
	boolean flag = true;
	
//	private Bank bank;
//	private int fromAccount;
	
//	public Money(Bank bank, int fromAccount) {
//		this.bank = bank;
//		this.fromAccount = fromAccount;
//	}
	/**
	 * Main Class Money Constructor.
	 * Reads the file, build the necessary data for the program to run.
	 */
	public Money() {
		
		try {
			readFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		banks = new Bank[barr.size()];
		customers = new Customer[carr.size()];
		for(int i=0; i<Money.barr.size(); i++) {
			String s[];
			s = Money.barr.get(i).split(" ");
//			h.put(s[0], Integer.parseInt(s[1]));
//			this.bankName[0] = s[0];
//			this.bankBalance[0] = Integer.parseInt(s[1]);
			this.banks[i] = new Bank(s[0], Integer.parseInt(s[1].trim()), this, carr.size());
		}
		
		for(int i=0; i<Money.carr.size(); i++) {
			String s[];
			s = Money.carr.get(i).split(" ");
//			h.put(s[0], Integer.parseInt(s[1]));
//			this.customerName[0] = s[0];
//			this.customerBalance[0] = Integer.parseInt(s[1]);
			this.customers[i] = new Customer(s[0], Integer.parseInt(s[1].trim()), Integer.parseInt(s[1].trim()), new ArrayList<Bank>(Arrays.asList(this.banks)), this, carr.size());
		}
		
		
	}
	
	/**
	 * First method which runs as soon as a Money thread is created.
	 */
	public void run() {
		
		Random rand = new Random();
		
//		System.out.println("---------------------------------------------------------------------" + count);
//		if(count == carr.size()) {
//			for(int i=0; i<banks.length; i++) {
//				System.out.println(banks[i].name + " has " + banks[i].balance + " dollar(s) remaining.");
//			}
//		}
		
		while(true && !flag) {
//			int toAccount = rand.nextInt(bankBalance.length+customerBalance.length);
//			int amount = rand.nextInt(50);
			
			//				Thread.sleep(rand.nextInt(1000));
			
			try {
//				System.out.println("HI");
//				this.stop();
//				System.out.println("---------------------------------------------------------------------" + count);
//				if(count == carr.size()) {
//					for(int i=0; i<banks.length; i++) {
//						System.out.println(banks[i].name + " has " + banks[i].balance + " dollar(s) remaining.");
//					}
//				}
			} catch (Exception e) {
				// TODO: handle exception
			}
//			if(fromAccount<customerBalance.length && toAccount>customerBalance.length)
//				bank.transfer(fromAccount, toAccount, amount);
//			System.out.println("---------------------------------------------------------------------" + count);
//			if(count == carr.size()) {
//				for(int i=0; i<banks.length; i++) {
//					System.out.println(banks[i].name + " has " + banks[i].balance + " dollar(s) remaining.");
//				}
//			}
//			System.out.println("over");
		}//end of while.
	}//end of run.
	
	/**
	 * Method which prints the message of customer requesting loan.
	 * @param custName
	 * @param amount
	 * @param bankName
	 */
	public void requestLoan(String custName, int amount, String bankName) {
		System.out.println(custName + " requests a loan of " + amount + " dollar(s) from " + bankName);
	}
	
	/**
	 * Method which prints the customers final status of the loan.
	 * @param status
	 * @param custName
	 * @param amount
	 */
	public void loanStatus(String status, String custName, int amount) {
		if(status == "yes") {
			count++;
//			System.out.println("---------------------------------------------------------------------" + count);
			System.out.println(custName + " has reached the objective of " + amount + " dollar(s). Woo Hoo!");
		}
		else if (status == "no") {
			count++;
//			System.out.println("---------------------------------------------------------------------" + count);
			System.out.println(custName + " was only able to borrow " + amount + " dollar(s). Boo Hoo!");
		}
		if(count == carr.size()) {
			stop();
		}
	}
	
	/**
	 * Method which prints the banks responses/reply to customers messages.
	 * @param bankName
	 * @param amount
	 * @param custName
	 * @param status
	 */
	public void bankResponse(String bankName, int amount, String custName, String status) {
		if(status == "approves") {
//			count++;
//			System.out.println("---------------------------------------------------------------------" + count);
			System.out.println(bankName + " approves a loan of " + amount + " dollars from " + custName);
		}
		else if(status == "denies") {
//			count++;
//			System.out.println("---------------------------------------------------------------------" + count);
			System.out.println(bankName + " denies a loan of " + amount + " dollar(s) from " + custName);
		}
	}
	
	/**
	 * Method which reads the .txt file and parse the file to extract the data.
	 * @throws FileNotFoundException
	 */
	public static void readFile() throws FileNotFoundException {
		BufferedReader bankbr = new BufferedReader(new FileReader("banks.txt"));
		BufferedReader customerbr = new BufferedReader(new FileReader("customers.txt"));
		String s1,s2;
		try {
			while((s1 = bankbr.readLine()) != null) {
				Money.barr.add(s1.replace("{", "").replace("}.", "").replace(",", " "));
			}
			
			while((s2 = customerbr.readLine()) != null) {
				Money.carr.add(s2.replace("{", "").replace("}.", "").replace(",", " "));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Main threads start method over-ridden to create new customer and bank threads. 
	 */
	public void start() {
				
		System.out.println("** Customers and loan objectives **");
		for(String value:carr) {
			String[] s = value.split(" ");
			System.out.println(s[0] + ": " + Integer.parseInt(s[1]));
		}
		
		System.out.println("\n** Banks and financial resources **");
		for(String value:barr) {
			String[] s = value.split(" ");
			System.out.println(s[0] + ": " + Integer.parseInt(s[1]));
		}
		
		System.out.println("\n");
		
		for(int i=0; i<this.customers.length; i++) {
				this.customers[i].start();
		}
		for(int i=0; i<this.banks.length; i++) {
			this.banks[i].start();
		}
		
	}
	
	/**
	 * Method to stop the execution and terminate all the threads.
	 */
	public void stop() {
		for(int i=0; i<this.banks.length; i++) {
			System.out.println(this.banks[i].name + " has " + this.banks[i].balance + " dollar(s) remaining.");
		}
		flag = false;
		for(int i=0; i<this.customers.length; i++) {
			this.customers[i].stop();
	}
	for(int i=0; i<this.banks.length; i++) {
		this.banks[i].stop();
	}
	}
	
	public static void main(String[] args) /*throws FileNotFoundException*/ {
		
				
		Money money = new Money();
		money.start();
		
		
		
//		bankName = new String[barr.size()];
//		bankBalance = new int[barr.size()];
//		
//		customerName = new String[carr.size()];
//		customerBalance = new int[carr.size()];
		
		
		
		
		
//		Bank bank = new Bank(bankBalance, customerBalance);
//		
//		for (int i = 0; i < customerBalance.length+bankBalance.length; i++) {
//            Thread t = new Thread(new Money(bank, i));
//            t.start();
//        }
		
//		for(int i=0; i<bankBalance.length; i++) {
//			Thread t = new Thread(new Money(bank, i));
//			t.start();
//		}
		
		
		
		
	}//End of Main
	
}//End of class Money.java
