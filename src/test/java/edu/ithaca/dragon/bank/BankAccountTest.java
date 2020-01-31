package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance());

        BankAccount bankAccount1 = new BankAccount("b@c.mail", 0);
        assertEquals(0, bankAccount1.getBalance());

        BankAccount bankAccount2 = new BankAccount("c@d.org", 2000);
        assertEquals(2000, bankAccount2.getBalance());

        BankAccount bankAccount3 = new BankAccount("d@e.com", 27);
        assertEquals(27, bankAccount3.getBalance());
    }

    @Test
    void withdrawTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance()); //Equivalence for having leftover money in account

        bankAccount.withdraw(100);
        assertEquals(0,bankAccount.getBalance()); //Equivalence for withdrawing to zero

        BankAccount bankAccount1 = new BankAccount("b@c.com", 1000);

        bankAccount1.withdraw(1000);
        assertEquals(0,bankAccount1.getBalance()); //Equivalence for withdrawing entire account

        BankAccount bankAccount2 = new BankAccount("c@d.mail", 0);

        bankAccount2.withdraw(500);
        assertEquals(0,bankAccount2.getBalance()); //Equivalence for trying to withdraw without money in account
    }

    @Test
    void isAmountValidTest(){
        assertTrue(BankAccount.isAmountValid(5));
        assertTrue(BankAccount.isAmountValid(5.75));
        assertTrue(BankAccount.isAmountValid(5.5));
        assertTrue(BankAccount.isAmountValid(.01));
        assertTrue(BankAccount.isAmountValid(1000.76));

        assertFalse(BankAccount.isAmountValid(0));
        assertFalse(BankAccount.isAmountValid(-.01));
        assertFalse(BankAccount.isAmountValid(.001));
        assertFalse(BankAccount.isAmountValid(50.789));
        assertFalse(BankAccount.isAmountValid(.00062));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com")); //Equivalence for correct basic email
        assertTrue(BankAccount.isEmailValid("a-b@.com")); //Equivalence for correct "-" in email
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com")); //Equivalence for correct "." in email
        assertTrue(BankAccount.isEmailValid("abc@mail.org")); //Equivalence for correct extension in email
        assertTrue(BankAccount.isEmailValid(("abcd@mail-archive.com"))); //Equivalence for correct "-" in domain
        assertTrue(BankAccount.isEmailValid("abcd@mail.cc")); //Equivalence for correct extension in email
        assertTrue(BankAccount.isEmailValid("abc_de@mail.com")); //Equivalence for correct "_" in email

        assertFalse( BankAccount.isEmailValid("")); //Equivalence for blank entry
        assertFalse(BankAccount.isEmailValid("ab-@b.edu")); //Equivalence for incorrect "-" in email
        assertFalse(BankAccount.isEmailValid("ab--@b.edu")); //Equivalence for incorrect "-" in email
        assertFalse(BankAccount.isEmailValid(".abcd@mail.com")); //Equivalence for incorrect "." in email
        assertFalse(BankAccount.isEmailValid("abc..d@de.com")); //Equivalence for incorrect "." in email
        assertFalse(BankAccount.isEmailValid("abcd#de@mail.com")); //Equivalence for incorrect "#" in email
        assertFalse(BankAccount.isEmailValid("abc@mail..com")); //Equivalence for incorrect ".." in email
        assertFalse(BankAccount.isEmailValid("abcde@mail")); //Equivalence for no extension in email
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}