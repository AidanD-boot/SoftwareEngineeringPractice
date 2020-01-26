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
        assertEquals(100, bankAccount.getBalance());

        bankAccount.withdraw(100);
        assertEquals(0,bankAccount.getBalance());

        BankAccount bankAccount1 = new BankAccount("b@c.com", 1000);

        bankAccount1.withdraw(1000);
        assertEquals(0,bankAccount1.getBalance());

        BankAccount bankAccount2 = new BankAccount("c@d.mail", 0);

        bankAccount2.withdraw(500);
        assertEquals(0,bankAccount2.getBalance());
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertTrue(BankAccount.isEmailValid("a-b@.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc@mail.org"));
        assertTrue(BankAccount.isEmailValid(("abcd@mail-archive.com")));
        assertTrue(BankAccount.isEmailValid("abcd@mail.cc"));
        assertTrue(BankAccount.isEmailValid("abc_de@mail.com"));


        assertFalse( BankAccount.isEmailValid(""));
        assertFalse(BankAccount.isEmailValid("ab-@b.edu"));
        assertFalse(BankAccount.isEmailValid(".abcd@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc..d@de.com"));
        assertFalse(BankAccount.isEmailValid("abcd#de@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc@mail..com"));
        assertFalse(BankAccount.isEmailValid("abcde@mail"));
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