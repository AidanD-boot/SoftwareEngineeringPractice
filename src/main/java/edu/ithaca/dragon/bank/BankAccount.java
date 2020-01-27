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
}
