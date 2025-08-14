# ✈️ Airline Management System

## 📌 Overview
The **Airline Management System** is a comprehensive Java-based desktop application developed for **Air Axel Airlines**.  
It streamlines **flight bookings**, **customer interactions**, and **administrative operations**, offering a modern, intuitive, and secure platform for both customers and administrators.

---

## 🎯 Purpose
- Provide a **seamless flight booking** experience for customers.
- Enable administrators to **manage flight schedules**, **add/remove flights**, and **update flight statuses**.
- Facilitate **customer support** through feedback and help modules.
- Ensure **secure authentication** for administrative access.

---

## ✨ Key Features

### **Customer Side**
- **Entry Page**: Splash screen introduction.
- **Landing Page**: Main dashboard with intuitive navigation.
- **Flight Booking**: Multi-step ticket booking with input validation.
- **Search Flights**: Filter and view available flights with real-time updates.
- **Feedback System**: Submit complaints, suggestions, or service feedback.
- **Help & Contact**: View contact details, FAQs, and support info.
- **Our Plans**: View different travel class options (Economy, Business, First Class).
- **Ticket Generation**: Download boarding passes in `.png` and `.txt` formats.

### **Admin Panel**
- **Secure Login System** with database authentication.
- **Flight Management**: Add, update, and delete flights.
- **Customer Feedback Management**: View, filter, and manage customer feedback.
- **Database Integration**: Persistent storage using MySQL.

---

## 🏗️ System Architecture

### **Technologies Used**
- **Language:** Java (Swing for GUI)
- **Database:** MySQL
- **Libraries:**
  - `javax.swing` — GUI components
  - `java.awt` — Graphics & event handling
  - `java.sql` — Database connectivity
  - `javax.imageio` — Image handling

### **Core Classes**
| Class Name             | Description |
|------------------------|-------------|
| `Entry.java`           | Splash screen introduction. |
| `Landing.java`         | Main dashboard navigation. |
| `BookTicket.java`      | Handles booking and ticket generation. |
| `Admin_Login.java`     | Admin authentication. |
| `ManageFlight.java`    | Flight CRUD operations. |
| `Menu_page.java`       | Admin dashboard. |
| `ViewFeedback.java`    | Display customer feedback. |
| `Feedback.java`        | Feedback submission form. |
| `Help.java`            | Help & contact info. |
| `About_Us.java`        | Company info. |
| `OurPlans.java`        | Displays travel class options. |
| `SearchFlight.java`    | Flight search and filter. |
| `DatabaseConnection.java` | Handles MySQL connections. |

---

## 🗄️ Database Design

**Database Name:** `airline_db`

### **Tables**
- **`flights`** – Stores flight details.
- **`passengers`** – Stores passenger information.
- **`tickets`** – Stores ticket booking details.
- **`feedback`** – Stores customer feedback and suggestions.
- **`admins`** – Stores admin credentials.

> SQL scripts for database setup are included in `airline_db.sql`.

---

## 🎨 UI & Design
- **Primary Color:** `#EA2F4A` (Red)  
- **Secondary Color:** `#30A6C1` (Blue)  
- **Fonts:** Montserrat (Bold for headings, Regular for content)
- **UX:** Rounded buttons, hover effects, validation messages, alternating table row colors.

---

## ⚙️ Installation & Setup

### **Prerequisites**
- **Java JDK 8+**
- **MySQL Server** (e.g., XAMPP, WAMP, or standalone)
- **IDE** (Eclipse, IntelliJ IDEA, or NetBeans)

### **Steps**
1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/airline-management-system.git
   cd airline-management-system
   ```
2. **Setup the Database**
   - Open MySQL.
   - Create a new database:
     ```sql
     CREATE DATABASE airline_db;
     ```
   - Import the provided `airline_db.sql` file.
3. **Configure Database Connection**
   - Open `DatabaseConnection.java` and update:
     ```java
     String url = "jdbc:mysql://localhost:3306/airline_db";
     String user = "root"; // your MySQL username
     String password = ""; // your MySQL password
     ```
4. **Run the Application**
   - Compile and run from your IDE.
   - Start with `Entry.java`.

---

## 🛠️ Error Handling & Validation
- **Input Validation** for booking forms and login.
- **SQL Exception Handling** with user-friendly error messages.
- **Form Field Checks** to ensure mandatory fields are filled.

---

## 🚀 Future Enhancements
- **Online Payment Integration** (Stripe, PayPal).
- **Email Notifications** for booking confirmations.
- **Real-Time Flight Status Updates** via API.
- **User Accounts** for profile management & booking history.

---

## 📄 License
This project is licensed under the **MIT License** – see the [LICENSE](LICENSE) file for details.

---

## 🤝 Contributing
Pull requests are welcome!  
For major changes, please open an issue first to discuss what you would like to change.

---

## 📞 Contact
**Developer:** Saad Ali  
📧 Email: airaxal@gmail.com  
📍 Address: 11-E, Egerton Road, Lahore  
📞 Phone: +92-42-36301854
