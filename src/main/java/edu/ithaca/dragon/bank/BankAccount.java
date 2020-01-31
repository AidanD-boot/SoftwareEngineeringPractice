package edu.ithaca.dragon.bank;


public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount)  {
        if (amount > 0 && amount <= balance){
            balance-=amount;
        }
        else if (amount < 0){
            throw new IllegalArgumentException("Error: Amount must be greater than zero");
        }
        else if (amount > balance){
            throw new IllegalArgumentException("Error: Amount is greater than balance");
        }
        else{
            throw new IllegalArgumentException("Error: Amount is not valid");
        }
    }

    /**
     * Checks validity of amounts being entered.
     * (True if amount is positive and has two or less decimal places, otherwise it is false)
     * @param amount in double
     * @return boolean true or false
     */
    public static boolean isAmountValid(double amount){
        String[] splitter = Double.toString(amount).split("\\.");
        splitter[0].length();   // Before Decimal Count
        int decimalLength = splitter[1].length();
        if(amount > 0 && decimalLength < 3){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1){
            return false;
        }
        String beforeArabola = new String();
        String afterArabola = new String();
        for (int i = 0; i < email.indexOf('@'); i++){
            beforeArabola += email.charAt(i);
        }
        for (int i = email.indexOf('@'); i < email.length(); i++){
            afterArabola += email.charAt(i);
        }
        if (email.length() < 1){
            return false;
        }
        else if (email.indexOf('-') == (email.indexOf('@') - 1)){
            return false;
        }
        else if (beforeArabola.indexOf('.') != beforeArabola.lastIndexOf('.')){
            return false;
        }
        else if (afterArabola.indexOf('.') != afterArabola.lastIndexOf('.')){
            return false;
        }
        else if (beforeArabola.indexOf('.') == 0){
            return false;
        }
        else if (email.indexOf('#') != -1){
            return false;
        }
        else if (afterArabola.length() - afterArabola.indexOf('.') < 3){
            return false;
        }
        else if (afterArabola.indexOf('.') == -1){
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * subtracts from current account and moves it to another account
     * @param amount
     * @param account
     */
    public void transfer(double amount, BankAccount account){
        if (isAmountValid(amount)){
            this.withdraw(amount);
            account.deposit(amount);
        }
        else{
            throw new IllegalArgumentException("Transfer amount: " + amount + " is invalid, cannot withdraw money");
        }
    }

    /**
     * adds amount to balance if amountIsValid == true
     * @param amount being deposited
     */
    public void deposit(double amount){
        if (isAmountValid(amount)){
            balance+=amount;
        }
        else{
            throw new IllegalArgumentException("Deposit amount: " + amount + " is invalid, cannot withdraw money");
        }
    }
}
