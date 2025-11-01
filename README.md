# 🏦 Banking Activity Simulation Platform  

A simple banking system built in **Java** with **MySQL database integration**.  
This project simulates basic banking operations such as **account creation, deposit, withdrawal, and fund transfer**.  
All transactions are logged in both **MySQL** and a **CSV file** for verification and persistence.  

---

## 🚀 Features  

- 🔢 **Auto-generated Account Number** (numeric only)  
- 🧾 **Unique Transaction ID** generated for every operation  
- 💰 **Deposit, Withdraw, and Transfer** between accounts  
- ✅ **Input validation** (no numbers in names, no negative amounts)  
- ⚠️ **Exception handling** for invalid inputs  
- 🧮 **Real-time updates** in MySQL and transaction CSV log  

---

## 🗂️ Database Tables  

### 🧾 **accounts**

| Column Name    | Type         | Description               |
|----------------|--------------|---------------------------|
| account_number | VARCHAR(20)  | Unique account ID         |
| holder_name    | VARCHAR(50)  | Account holder name       |
| balance        | DECIMAL(15,2)| Current balance           |
| created_at     | DATETIME     | Account creation date     |

### 🧾 **transactions**

| Column Name    | Type         | Description                           |
|----------------|--------------|---------------------------------------|
| transaction_id | VARCHAR(20)  | Unique transaction ID                 |
| type           | VARCHAR(20)  | CREATE / DEPOSIT / WITHDRAW / TRANSFER|
| from_account   | VARCHAR(20)  | Sender account number                 |
| to_account     | VARCHAR(20)  | Receiver account number               |
| amount         | DECIMAL(15,2)| Transaction amount                    |
| txn_time       | DATETIME     | Time of transaction                   |

---

## 🏁 Milestone 1 – Mini Banking System Demo  

### 💻 Program Execution Sample Output  
✅ MySQL JDBC Driver loaded in AccountManager.
MySQL JDBC Driver loaded successfully!

===== Welcome to the Mini Banking System =====

===== BANK MENU =====
1. Create Account
2. Deposit
3. Withdraw
4. Transfer
5. List Accounts
6. Exit

Enter your choice: 1
Enter account holder name: Vinod
Enter initial balance: 3000
Inserted 1 row(s) into database.
🧾 Transaction ID: TXN4601A194
✅ Account created successfully!
Account Number: 9136922
Holder Name: Vinod
Initial Balance: ₹3000
💡 Use this Account Number (9136922) for future transactions.

===== BANK MENU =====
Enter your choice: 2
Enter account number: 9136922
Enter amount to deposit: 500
🧾 Transaction ID: TXNBE899800
💰 Deposited ₹500 to 9136922. New balance: ₹3500.00

===== BANK MENU =====
Enter your choice: 3
Enter account number: 9136922
Enter amount to withdraw: 400
🧾 Transaction ID: TXN04A84A3F
💸 Withdrew ₹400 from 9136922. New balance: ₹3100.00

===== BANK MENU =====
Enter your choice: 5
Account Number: 1001 | Name: Vijay | Balance: ₹3000.00
Account Number: 9136922 | Name: Vinod | Balance: ₹3100.00
Account Number: 9194495 | Name: Mandeep | Balance: ₹2900.00
Account Number: 9614784 | Name: Nandhu | Balance: ₹3200.00
Account Number: B2012 | Name: Prakash | Balance: ₹6000.00

===== BANK MENU =====
Enter your choice: 6
🏦 Exiting... Check 'transactions.csv' and database for logs.

---
🛠️ Technologies Used
Technology	Description
☕ Java (JDK 17)	Core programming language
🗃️ MySQL	Database management system
🔗 JDBC	Java Database Connectivity
💻 Eclipse / VS Code	IDE used for development
🌐 Git & GitHub	Version control and repository hosting

---
👨‍💻 Author

Vijay Prakash
🎓 B.Tech Student | 💻 Java Developer

