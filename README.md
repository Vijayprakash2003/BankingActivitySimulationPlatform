# 🏦 Banking Activity Simulation Platform

A simple banking system built in **Java** with **MySQL** database integration.  
This project simulates basic banking operations such as account creation, deposit, withdrawal, and fund transfer.  
All transactions are logged in both **MySQL** and a **CSV file**.

---

## 🚀 Features
- Auto-generated **Account Number** (numeric only)
- **Transaction ID** generated for every operation
- **Deposit**, **Withdraw**, and **Transfer** between accounts
- **Input validation** (no numbers in names, no negative amounts)
- **Exception handling** for invalid inputs
- **Real-time updates** in MySQL and transaction CSV log

---

## 🗂️ Database Tables

### 🧾 `accounts`
| Column | Type | Description |
|--------|------|-------------|
| account_number | VARCHAR(20) | Unique account ID |
| holder_name | VARCHAR(50) | Account holder name |
| balance | DECIMAL(15,2) | Current balance |
| created_at | DATETIME | Account creation date |

### 🧾 `transactions`
| Column | Type | Description |
|--------|------|-------------|
| transaction_id | VARCHAR(20) | Unique transaction ID |
| type | VARCHAR(20) | CREATE / DEPOSIT / WITHDRAW / TRANSFER |
| from_account | VARCHAR(20) | Sender account number |
| to_account | VARCHAR(20) | Receiver account number |
| amount | DECIMAL(15,2) | Transaction amount |
| txn_time | DATETIME | Time of transaction |

---

## 💻 Sample Output
```bash
===== Welcome to the Mini Banking System =====
Enter account holder name: Praneeth
Enter initial balance: 2500
✅ Account created successfully!
Account Number: 3488819
🧾 Transaction ID: TXNA41BD23C
💡 Use this Account Number for future deposits, withdrawals, or transfers.
```

---

## 🛠️ Technologies Used
- Java (JDK 17)
- MySQL
- JDBC
- Eclipse / VS Code
- Git & GitHub

---

## 👨‍💻 Author
**Vijay Prakash**  
B.Tech Student | Java Developer


