package Accounts;

abstract class Account {

     double balance;

    Account(double balance) {
      this.balance = balance;
    }

    void putInCheckingAccount(double amount) {
      this.balance += amount;
    }

    void getFromCheckingAccount(double amount) {
      this.balance -= amount;
    }

    double getBalance() {
      return balance;
    }
  }

