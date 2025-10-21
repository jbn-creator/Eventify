# 🎭 Eventify

**Eventify** is a Java-based application designed to efficiently manage and streamline operations for a performance hall.  
The system handles event listings, ticket management, and payments for both administrators and customers — all within a simple and intuitive user interface.

---

## 🚀 Overview

**Eventify** provides a complete solution for managing a venue that hosts various types of events, including music and performance shows.  
It is built using **Object-Oriented Programming (OOP)** principles such as encapsulation, inheritance, abstraction, and polymorphism, ensuring scalability, maintainability, and modularity.

The system supports two main roles:
- **Admin:** Can add and view all event details.
- **Customer:** Can browse, filter, and purchase tickets for available events.

---

## 🧩 Features

### 👩‍💼 Admin
- View all events with full details, sorted by ticket price.
- Add new events to the event stock (while preventing duplicate IDs).

### 👤 Customer
- View all available events (excluding performance fee), sorted by price.
- Add tickets to a shopping basket.
- View or remove items from the basket.
- Complete purchases using either:
  - **PayPal** (requires PayPal email)
  - **Credit Card** (requires 6-digit card number and 3-digit security code)
- Generate a digital receipt showing the payment method, date, amount, and billing address.
- Search or filter events by:
  - Event ID
  - Language (for performances)

---

## 🧠 Object-Oriented Design

The system is fully object-oriented and organized into modular classes, each with clear responsibilities.  

**Key Design Concepts:**
- **Abstraction:** Common structures like events and users are defined through abstract classes and interfaces.
- **Encapsulation:** Private fields with public getters/setters ensure data integrity.
- **Inheritance:** Specialized event and user types extend base classes.
- **Polymorphism:** Common behaviors (like displaying or paying for events) are handled through shared interfaces.

**Core Classes:**
- `Event` (abstract)
  - `MusicEvent`
  - `PerformanceEvent`
- `User` (abstract)
  - `Admin`
  - `Customer`
- `Basket`
- `Payment` (interface)
  - `PayPalPayment`
  - `CreditCardPayment`
- `MainApp` (entry point)

---

## 💻 Technologies Used
- **Java SE 17+**
- **Eclipse IDE**
- **Text Files (.txt)** for persistent data storage
- **Swing / CLI** (depending on implementation) for the user interface

---

## 📂 Project Structure

Eventify/
│
├── src/
│ ├── model/
│ │ ├── Event.java
│ │ ├── MusicEvent.java
│ │ ├── PerformanceEvent.java
│ │ ├── User.java
│ │ ├── Admin.java
│ │ ├── Customer.java
│ │ ├── Basket.java
│ │ ├── Payment.java
│ │ ├── PayPalPayment.java
│ │ └── CreditCardPayment.java
│ │
│ └── main/
│ └── MainApp.java
│
├── data/
│ ├── UserAccounts.txt
│ └── Stock.txt
│
├── eventify.jar
└── README.md

## 📁 Example Data

### User Accounts (`UserAccounts.txt`)
101, user1, Sarasvati, 12, LE11 3TU, Loughborough, admin
102, user2, Felix, 14, E20 3BS, London, customer
103, user3, Hikaru, 100, BN1 3XP, Brighton, customer
104, user4, Salma, 57, PA3 2SW, Glasgow, customer

### Event Stock (`Stock.txt`)
53201, Performance, Theatre, Shakespeare’s Macbeth, All, 400, 5000, 50, English
21908, Music, DJ Set, EDM Madness, Adults, 1000, 7000, 30, 2
56723, Performance, Stand-up Comedy, Laugh Out Loud, Adults, 250, 1500, 25, Japanese
37412, Music, Live Concert, Rock Legends Live, All, 500, 5000, 50, 5
12039, Music, Live Concert, Jazz Night, All, 300, 3000, 40, 3
68251, Performance, Theatre, A Midsummer Night’s Dream, All, 450, 6000, 60, Arabic
57381, Music, DJ Set, Underground Sounds, Adults, 600, 4000, 20, 3
14935, Performance, Magic, Enchanted Wonders, All, 150, 2500, 35, English
92674, Music, Live Concert, Summer Beats Festival, All, 1000, 15000, 25, 10
47125, Performance, Theatre, The Great Escape, All, 350, 4500, 55, Spanish


## ⚙️ Installation and Execution

### Requirements
- Java 17 or later
- (Optional) Eclipse IDE for development

### Run Instructions
1. Clone or download the repository.
2. Open the project in Eclipse (or any Java IDE).
3. Ensure `UserAccounts.txt` and `Stock.txt` are located in the project root or `data/` folder.
4. Run the main file:
   ```bash
   java -jar eventify.jar
or directly from your IDE.

💳 Example Receipts
PayPal Payment:

£100 paid via PayPal using example@email.com on 21/10/2025,
and the billing address is 12 LE11 3TU, Loughborough.
Credit Card Payment:

£100 paid by Credit Card using 123456 on 21/10/2025,
and the billing address is 12 LE11 3TU, Loughborough.
🧾 Error Handling
Prevents adding duplicate event IDs.

Detects and reports sold-out events.

Validates payment input formats.

Handles file reading/writing errors gracefully.

🧠 OOP Principles Summary
Principle	Implementation
Abstraction	Payment interface defines structure for all payment types.
Encapsulation	Private fields with controlled access through getters/setters.
Inheritance	Event subclasses inherit shared functionality from Event.
Polymorphism	Unified handling of different event types through overridden methods.
Composition	Customer class contains a Basket instance.
