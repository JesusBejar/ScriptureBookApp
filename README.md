# Overview
This is a simple scripture reading app made using Java + Spring Boot 3! 
The app fetches Bible books, chapters, and verses from this API - https://bible-api.com/?ref=public_apis&utm_medium=website

It is made using Java and Springboot
My purpose is to provide users with a simple web app that they can use to read their scriptures online!

[Software Demo Video](https://www.loom.com/share/6dabd67d95bb4dbd90cc67d8973da1c7?sid=3b49eee1-a491-401c-bae7-1dd371e55aa2)

# Development Environment

Tools I used:
Youtube
Java language
Springboot framework

# Useful Websites

{Make a list of websites that you found helpful in this project}

- [Java Programming for Beginners - Full course](https://www.youtube.com/watch?v=A74TOX803D0)
- [Building Web Applications in Java with Spring Boot 3](https://www.youtube.com/watch?v=31KTdfRH6nY&t=1921s)
- [Java OOP](https://www.w3schools.com/java/java_oop.asp)


# Future Work

{Make a list of things that you need to fix, improve, and add in the future.}

- Make UI
- Connect to scripture API
- Add better file stucture
- Connect frontend to backend

Here are my classes (for now)
* class Library 
    - ScriptureBook
* class ScriptureBook
    - name
    - numOfBook
    - Books
* class Book 
    - name
    - numOfChapter
    - Chapter
* class Chapter
    - author
    - numOfVerses
    - verseText

And here are my functions:
* function fetchText
* function formatText
* function displayText
* function handleClick

Here are the 4 OOP principles and my description for how my program follows them - 
Abstraction - certain variables, functions, classes are private while others are public
Encapsulation - similar logic is found in the same secure file
Inheritance - i haven't added any inheritance logic....yet!
Polymorphism - i also haven't added any polymorphism logic
