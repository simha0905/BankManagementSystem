# BankManagementSystem
Bank Management System – Java (Using DSA Concepts)

A simple Bank Management System implemented in Java, designed using Object-Oriented Programming (OOP) and Data Structures & Algorithms (DSA) concepts.
This console-based project performs essential banking operations such as creating accounts, depositing funds, withdrawing money, and managing stored account data.

Features

1. Create Bank Account
Stores customer details (name, account number, balance)
Uses file handling to save accounts in bank_accounts.txt
2. Deposit Money
Adds amount to user account
Validates account number and input
3. Withdraw Money
Ensures balance availability
Prevents invalid/negative withdrawals
4. Display All Accounts
Reads all account data from text file
Displays customer information neatly

DSA + OOP Concepts Used
Concept	Usage

Classes & Objects	Models accounts and banking operations
Arrays / Lists	Manages multiple accounts in memory
Searching	Finds accounts by account number
Exception Handling	Prevents invalid banking operations
File I/O	Persistent data storage

Project Folder Structure

BankManagementSystem/
│
├── BankAccount.java
├── BankManagementSystem.java
├── Main.java
├── bank_accounts.txt
│
└── Compiled Files (.class)
