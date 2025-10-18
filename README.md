Library Management System (Console + JDBC)
Overview
This is a console-based Library Management System developed in Java with JDBC connection to a MySQL database.

Features
Add Book: Add new books with validation for title and author using regex.
Show All Books: Display all books in the library.
Show Book by ID: Search and display details of a book by its ID.
Borrow Book: Borrow a book only if it is available.
Return Book: Return a book only if it was borrowed.
Input Validation:
Title and author names are validated using regex.
Prevent borrowing if the book is unavailable or ID does not exist.
Prevent returning if the book ID is invalid or already available.
Colored Console Output: Uses regex patterns to display messages in different colors for better readability.

Notes

This system is a console application(done in eclipse) and does not have a GUI.
All operations are performed directly on the MySQL workbench using JDBC.
Messages are displayed in colored outputs for better readability.

