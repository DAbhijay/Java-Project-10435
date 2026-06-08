# Hotel Room Reservation System

> **OOP Project Report** | Object Oriented Programming with Java
> **Course Code:** RU-100-01-00012 | **Session:** 2025-26
> **Institution:** School of Computer Science and Engineering, Rungta International Skills University, Bhilai, CG
> **Guided by:** Mr. Shivansh Mehta, Java Trainer

---

## Abstract

The Hotel Room Reservation System is a console-based Java application that simulates real-world hotel booking operations using Object-Oriented Programming (OOP) principles. The system represents individual rooms via a `Room` class, organizes them across floors using a 2D array inside a `Hotel` class, and applies a `Service` interface for billing operations. Users can book available rooms, cancel bookings, and calculate bills including base charges, tax, and a service fee.

---

## Table of Contents

- [Introduction](#introduction)
- [Key Features](#key-features)
- [Objectives](#objectives)
- [Scope](#scope)
- [System Requirements](#system-requirements)
- [Methodology](#methodology)
- [Architecture](#architecture)
- [Future Enhancements](#future-enhancements)
- [Conclusion](#conclusion)
- [References](#references)

---

## Introduction

In the modern hospitality industry, room reservation systems play a crucial role in managing guest bookings efficiently. This project addresses the challenge of manual record-keeping through a clean, object-oriented software design implemented in Java.

The application is organized around three main components:

- `Room` - encapsulates room data
- `Hotel` - manages a 2D array of rooms across multiple floors
- `Service` - interface defining the billing contract

---

## Key Features

- Multi-floor room management using a 2D array (`Room[][]`)
- Room types: **Single**, **Double**, and **Deluxe** with different pricing
- Real-time availability tracking per room
- Room booking with duplicate booking prevention
- Booking cancellation to free up rooms
- Bill calculation including base rate, 10% tax, and a fixed Rs. 200 service fee
- Display of all rooms with current availability status

---

## Objectives

- Manage and organize hotel room records across multiple floors
- Track room availability and prevent double bookings
- Reduce manual work through automated booking and billing logic
- Apply core OOP concepts: classes, encapsulation, interfaces, and arrays
- Provide a clean, extendable foundation for future enhancements

---

## Scope

### Current Scope

- Supports up to N floors with M rooms per floor (configurable in constructor)
- Handles three room types: Single, Double, and Deluxe
- Booking and cancellation of individual rooms
- Bill generation for any booked room with tax and service charges
- Displays complete room status across all floors

### Out of Scope (Current Version)

- No graphical user interface (GUI) - console only
- No persistent data storage (data lost on program exit)
- No customer or guest profile management
- No multi-user or network access support

### Future Scope

- Integration with a relational database (MySQL / SQLite)
- JavaFX or Swing-based GUI
- Customer login and registration system
- Online reservation via web services or REST API
- Report generation and analytics

---

## System Requirements

### Hardware

| Component | Minimum Requirement             |
|-----------|---------------------------------|
| Processor | Intel Core i3 or equivalent     |
| RAM       | 4 GB (8 GB recommended)         |
| Storage   | 500 MB free disk space          |
| Display   | 1024 x 768 resolution or higher |

### Software

| Software         | Details                                       |
|------------------|-----------------------------------------------|
| Operating System | Windows 10 / macOS / Linux                    |
| Java JDK         | JDK 8 or higher (JDK 17 LTS recommended)      |
| IDE              | VS Code, Eclipse, IntelliJ IDEA, or Edit Plus |
| Terminal / CMD   | For compiling and running Java files          |

---

## Methodology

The project follows a **bottom-up development approach** - simpler components are built and tested first before integration into the larger system.

### Step 1 - Define the Contract (`Service` Interface)

A `Service` interface declares the `calculateBill()` method, establishing the billing contract. Using an interface keeps the system open for extension without modifying existing code.

### Step 2 - Design the `Room` Class

The `Room` class represents a single hotel room with private fields for room number, type, price per day, and a boolean availability flag. Public getters/setters enforce encapsulation, and a `displayStatus()` method prints formatted room details.

### Step 3 - Build the `Hotel` Class with 2D Array

The `Hotel` class manages a `Room[][]` where the first index is the floor and the second is the room number. The `initializeRooms()` method populates the array with cyclic room types (Single to Double to Deluxe). `Hotel` implements the `Service` interface.

### Step 4 - Implement Booking Logic

`bookRoom(floor, roomIndex)` validates inputs, checks availability, and marks the room as occupied - or notifies the user if already booked.

### Step 5 - Implement Cancellation Logic

`cancelBooking(floor, roomIndex)` resets the room's availability flag to `true` after validating bounds.

### Step 6 - Implement Billing

`calculateBill(days, rate)` computes:

```text
baseBill = days x rate
tax      = baseBill x 10%
total    = baseBill + tax + Rs. 200 (service fee)
```

### Step 7 - Display and Testing

`displayAllRooms()` iterates the 2D array and calls `displayStatus()` on each room. The `Main` class exercises the full booking lifecycle: initial display, booking, duplicate attempt, cancellation, bill calculation, and final display.

---

## Architecture

### Program Flow

```text
START
|
+-- Create Hotel Object (floors, rooms)
+-- Initialize 2D Room Array
+-- Display All Rooms
|
+-- BOOK ROOM
|   +-- Validate Floor & Room Index
|   +-- Is Room Available?
|       +-- YES -> Mark as Occupied -> Confirm Booking
|       +-- NO  -> Print: Already Occupied
|
+-- CANCEL BOOKING
|   +-- Validate Floor & Room Index
|   +-- Set isAvailable = true -> Confirm Cancellation
|
+-- CALCULATE BILL
|   +-- baseBill = days x rate
|   +-- tax = baseBill x 10%
|   +-- total = baseBill + tax + Rs. 200
|   +-- Print Bill Summary
|
+-- Display Final Room Status
+-- END
```

### Class Diagram

### Service (interface)

```text
Methods:
+ calculateBill(int days, double rate) : double
```

### Room

```text
Fields:
- roomNumber  : int
- roomType    : String
- pricePerDay : double
- isAvailable : boolean

Methods:
+ getRoomNumber()       : int
+ getRoomType()         : String
+ getPricePerDay()      : double
+ isAvailable()         : boolean
+ setAvailable(boolean)
+ displayStatus()
```

### Hotel (implements Service)

```text
Fields:
- hotelName : String
- rooms     : Room[][]

Methods:
+ initializeRooms()
+ bookRoom(int, int)
+ cancelBooking(int, int)
+ calculateBill(int, double)
+ displayAllRooms()
+ getRoom(int, int)
```

### Key Classes

**`Room.java`** - Entity class using private fields (encapsulation). Constructor initializes all fields with availability defaulting to `true`. Getters expose read access; `setAvailable()` allows controlled state changes.

**`Main.java`** - Driver/entry point. Creates a `Hotel` object, then sequentially tests: initial room display, booking, duplicate booking attempt, cancellation, bill calculation, and updated room display.

---

## Future Enhancements

- **Database Integration** - Connect to MySQL/SQLite for persistent room and booking data
- **GUI** - Replace the console with a Java Swing or JavaFX interface
- **Customer Management** - Add a `Customer` class for guest profiles and booking history
- **Search and Filter** - Search available rooms by type, floor, or price range
- **Date-Based Booking** - Check-in/check-out dates with automatic room release
- **Invoice Generation** - Export bill summaries to PDF or text
- **Authentication** - Admin and receptionist login roles with access controls
- **Multi-Hotel Support** - Manage a chain of hotels with separate inventories

---

## Conclusion

The Hotel Room Reservation System demonstrates the practical application of OOP concepts in Java through a clean, modular architecture:

- **Encapsulation** - private fields and public accessors in `Room`
- **Abstraction** - billing contract via the `Service` interface
- **Composition** - `Room` objects embedded within `Hotel`
- **2D Array Handling** - grid of rooms across multiple floors

The system correctly handles bookings, prevents duplicates, supports cancellations, and generates detailed bills. The bottom-up development approach reinforced the value of incremental testing and modular design, setting the stage for future enhancements.

---

## References

1. H. Schildt, *Java: The Complete Reference*, 11th ed. New York: McGraw-Hill, 2018.
2. Oracle, "Java Documentation." \[Online\]. Available: <https://docs.oracle.com/en/java/>
3. Oracle, "Java SE 17 API Specification." \[Online\]. Available: <https://docs.oracle.com/en/java/javase/17/docs/api/>
4. E. Gamma, R. Helm, R. Johnson, and J. Vlissides, *Design Patterns: Elements of Reusable Object-Oriented Software*. Boston: Addison-Wesley, 1994.
5. GeeksforGeeks, "Object Oriented Programming in Java." \[Online\]. Available: <https://www.geeksforgeeks.org/object-oriented-programming-oops-concept-in-java/>
6. R. Lafore, *Object-Oriented Programming in Java*, 2nd ed. Indianapolis: Sams Publishing, 2002.
