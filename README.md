# 🏦 Banking Activity Simulation Platform

A simple **Java + MySQL + JDBC** based banking simulation system that demonstrates account creation, deposit, withdrawal, fund transfer, and transaction recording with admin and user authentication.

---

## ⚙️ Technologies Used
- **Java (JDK 17)** – Core programming logic  
- **JDBC (Java Database Connectivity)** – Database interaction  
- **MySQL** – Database for storing account and transaction data  
- **Object-Oriented Programming (OOP)** – Encapsulation and modularity  
- **DAO (Data Access Object)** – Layered architecture for clean database handling  
- **Exception Handling** – Safe and stable runtime operations  
- **CSV Logging (optional)** – Simple file-based record storage  

---

## 🌟 Features
✅ Admin and User Login System  
✅ Account Creation with Password  
✅ Deposit and Withdraw Money  
✅ Transfer Funds between Accounts  
✅ Display All Accounts (Admin Only)  
✅ Transaction Recording in Database  
✅ Secure JDBC Connectivity with MySQL  
✅ User-Friendly Console Interface  

---

## 🗄️ Database Tables

### 🧍 Accounts Table
| Column Name      | Data Type     | Description                       |
|------------------|---------------|-----------------------------------|
| account_number   | VARCHAR(20)   | Unique account number (Primary Key) |
| holder_name      | VARCHAR(100)  | Account holder’s name             |
| balance          | DECIMAL(15,2) | Current account balance           |
| password         | VARCHAR(100)  | Account password                  |

---

### 💸 Transactions Table
| Column Name     | Data Type     | Description                              |
|-----------------|---------------|------------------------------------------|
| transaction_id  | VARCHAR(20)   | Unique ID for each transaction (Primary Key) |
| type            | VARCHAR(20)   | Type of transaction (Deposit/Withdraw/Transfer) |
| from_account    | VARCHAR(20)   | Sender account number                    |
| to_account      | VARCHAR(20)   | Receiver account number (for transfers)  |
| amount          | DECIMAL(15,2) | Transaction amount                       |
| timestamp       | DATETIME      | Time of transaction                      |

---

### 👨‍💼 Admin Table
| Column Name | Data Type     | Description             |
|--------------|---------------|-------------------------|
| username     | VARCHAR(50)   | Admin username (Primary Key) |
| password     | VARCHAR(100)  | Admin password          |

---

## 🧾 Sample Console Output
```
===== Welcome to the Mini Banking System =====
✅ MySQL JDBC Driver loaded successfully!

Admin Login

User Login

Exit
Enter your choice: 1
Enter Admin Username: admin
Enter Admin Password: admin123
✅ Admin login successful!

===== ADMIN MENU =====

Create Account

List All Accounts

Exit
Enter your choice: 1
Enter account holder name: Vinod
Enter initial balance: 3000
Set a password for this account: vinod123
✅ Account created successfully!

Account Number: 10245
Holder Name: Vinod
Initial Balance: ₹3000
Password: vinod123
💡 Use this Account Number for future logins.

===== USER LOGIN =====
Enter Account Number: 10245
Enter Password: vinod123
✅ Login successful!

View Account Details

Deposit

Withdraw

Transfer

Logout
Enter your choice: 2
Enter amount to deposit: 500
✅ ₹500 deposited successfully. Current balance: ₹3500

Transaction recorded successfully in transactions table.
```

---

## 👨‍💻 Author
**Vijay Prakash**  
📧 Email: vijaysiddireddi@gmail.com  
🚀 Project: *Banking Activity Simulation Platform (Java + MySQL + JDBC)*  
💻 Made with ❤️ using Java & MySQL
