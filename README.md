Mega City Cabs is a comprehensive cab booking and management system designed to streamline the operations of a popular cab service in Colombo City. The application automates customer bookings, fare calculations, and driver/car management, ensuring a seamless experience for both customers and administrators. With features like secure authentication, dynamic fare calculation based on distance, and support for multiple payment methods, the system is built to handle thousands of monthly transactions efficiently.

Explanation of Workflow Chart
Login: Users (customers, drivers, or administrators) log in to the system using secure credentials.
Register New Customer: New customers are registered with their details (name, address, NIC, etc.).
Add Booking: Customers create a booking by providing order details, including destination and contact information.
Calculate Fare: The system calculates the fare dynamically based on distance, applicable discounts, and taxes.
Assign Driver and Car: The system assigns an available driver and car to the booking.
Update Booking Status: The booking status is updated as it progresses through stages (PENDING, ACCEPTED, DISPATCHED, COMPLETE).
Process Payment: Payments are processed externally using the customer's chosen payment method (e.g., card, wallet).
View/Cancel Booking: Customers can view booking details or cancel a booking (with a cancellation fee if dispatched).
Logout/Exit: Users log out securely or exit the system.
This workflow ensures a smooth and efficient process for managing cab bookings and related operations.


Prerequisites for Mega City Cabs Project:

1. Development Environment:

Operating System: Windows, macOS, or Linux (compatible with Java, Tomcat, and MySQL).
Integrated Development Environment (IDE):
Eclipse, IntelliJ IDEA, or NetBeans (recommended for Java development).
2. Java Development Kit (JDK):

Java JDK 11: Ensure JDK 11 is installed and configured correctly. This is crucial for compiling and running the Java-based application.
Verify installation by running java -version and javac -version in your command line.
3. Application Server:

Apache Tomcat Server:
A compatible version of Apache Tomcat (e.g., Tomcat 9 or 10) should be installed and configured. This will serve as the application server for the Java web application.
Ensure that the Tomcat server is running and accessible.
4. Database Management System:

MySQL Server:
MySQL Server should be installed and running.
Create a dedicated database for the Mega City Cabs application.
Ensure you have the necessary MySQL Connector/J driver to connect Java to the MySQL database.
Know the database username and password.
5. Dependencies and Libraries:

MySQL Connector/J: This Java library is required for connecting to the MySQL database.
Servlet API: Required for developing Java servlets (part of the Java EE platform).
JSP API: Required for developing Java Server Pages (part of the Java EE platform).
Any other libraries needed for json processing, or other required functionality. These can be added via maven or gradle.
6. Network Configuration:

Ensure that the necessary ports (e.g., 8080 for Tomcat, 3306 for MySQL) are open and accessible.
7. Web Browser:

A modern web browser (e.g., Chrome, Firefox, Edge) for testing the web application.



Steps to Verify Prerequisites:

Java JDK:
Open a command prompt or terminal.
Run java -version and javac -version. Verify that the output shows Java 11.
Tomcat Server:
Start the Tomcat server.
Open a web browser and navigate to http://localhost:8080 (or the appropriate port). You should see the Tomcat welcome page.
MySQL Server:
Open a MySQL client (e.g., MySQL Workbench or the command-line client).
Connect to the MySQL server using your credentials.
Create a database for the project.
Database Connection:
Ensure that the mysql connector/j jar file is added to the tomcat lib folder, or added to the project using a build tool like maven or gradle.
Test the database connection from your development environment to the mysql server.
