Finance Dashboard Backend

Overview

This project is a backend system for a finance dashboard, built using Java, Spring Boot, and MySQL. It supports financial record management, user and role management, dashboard summaries, access control, and exception handling.

The system allows different users to interact with financial data according to their roles:

Viewer: Can only view dashboard data.
Analyst: Can view records and access insights.
Admin: Can create, update, and manage records and users.


Features
1. User Management
Create, update, and delete users (Admin only).
Assign roles (ADMIN, ANALYST, VIEWER) and statuses (ACTIVE, INACTIVE).
Search users by email or role.
Role-based access control ensures only authorized users can perform actions.
2. Financial Records
Create, read, update, and delete financial records.
Each record contains: amount, type (INCOME or EXPENSE), category, date, description, and associated user.
Filtering records by type, category, or date range.
3. Dashboard Summary
Returns total income, total expenses, and net balance.
Aggregates data from all financial records.
Role-based access enforced for viewing dashboard.
4. Access Control
Backend-level role-based access control using checks in service layers.
Unauthorized operations throw a custom AccessDeniedException.
5. Exception Handling
Global exception handling for:
Resource not found (ResourceNotFoundException)
Access denied (AccessDeniedException)
Validation errors
General server errors
6. Validation
Input validation for User and Financial Records fields using annotations:
@NotBlank, @NotNull, @Positive, @Email, @Size


Tech Stack

Language: Java

Framework: Spring Boot

Database: MySQL

Build Tool: Maven

Testing / API Client: Postman



Project Structure

com.Finance.Dashboard

│
├── Controller       # REST API endpoints for User, FinRecords, and Dashboard

├── Service          # Business logic and access control

├── Repository       # JPA repositories for database operations

├── Model            # Entity classes and enums

├── DTO              # DashboardSummary for API responses

├── Exception        # Custom exceptions and global handler

├── DashboardApplication.java  # Main Spring Boot application


Database Schema

User Table: id, name, email, password, role, status

FinRecords Table: id, amount, type, category, date, description, user_id (foreign key)

Relationships:

One User → Many Financial Records (@OneToMany / @ManyToOne)

API Endpoints

Users

GET /api/users – Get all users (Admin only)

GET /api/users/{id} – Get user by ID

POST /api/users – Create a new user

PATCH /api/users/{id} – Update user info

DELETE /api/users/{id} – Delete user

GET /api/users/email?email= – Find user by email

PATCH /api/users/{id}/status?status= – Update user status

Financial Records

GET /records – Get all records

GET /records/{id} – Get record by ID

POST /records – Create new record (Admin only)

PATCH /records/records/update/{id} – Update record

Filters: by type, category, or date range

Dashboard

GET /dashboard/summary – Get total income, total expense, and balance


Assumptions

Role-based permissions are checked in service layers.

Only Admin users can create/update/delete users or records.

Viewer users can only access the dashboard summary.

Basic input validation is enforced using annotations.

Exception handling provides meaningful messages for invalid operations.
