📚 Book Management System in Java (MVC)

A command-line application developed in Java using the Model-View-Controller (MVC) architecture. This program allows users to manage a collection of books stored in a MySQL database. Users can add, view, update, and delete books. The code is organized for clarity, testability, and easy maintenance.

📌 Features

🔸Add a new book

🔸Update existing book information by ISBN

🔸Delete a book by ISBN

🔸Command-line user interface

🔸Structured using the MVC design pattern

🔸Basic error handling and input validation

🚀 Tools

🔹Language: Java

🔹IDE: IntelliJ IDEA

🔹Database: MySQL

🔹Testing Frameworks: JUnit 5 and Mockito

🔹Architecture: Model-View-Controller (MVC)

🧠 How to Use

1. Clone the Repository

        git clone https://github.com/roz-mari/Library.git

2. Run the Application using IntelliJ IDEA:

   1. Open the project (File > Open)
   2. Right-click on Main.java → Run

🧪 Run Unit Tests

To run tests with IntelliJ:

🔸Right-click on the test folder → Run Tests

🔹Or open BookControllerTest.java and run it directly

The tests cover the core logic of the controller using mocked repository behavior.

📁 Project Structure

    src/

    ├── org.library.controller/ # Handles user input and coordination

    ├── org.library.model/ # Book class

    ├── org.library.repository/ # Database operations (MySQL)

    ├── org.library.view/ # Console-based interface

    └── org.library/Main.java # Entry point

👩‍💻 Contributors

⭐[@roz-mari](https://github.com/roz-mari)

⭐[@May1704](https://github.com/May1704)

⭐[@niaofnarnia](https://github.com/niaofnarnia)

⭐[@sab-gif](https://github.com/sab-gif)