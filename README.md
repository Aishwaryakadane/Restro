## Overview

The Restaurant Management System is a Spring Boot-based application that provides a set of CRUD (Create, Read, Update, Delete) operations for managing users, including Admin, Normal User, and Visitor, along with food items in a restaurant. The system enforces role-based access control, ensuring that each type of user has the appropriate privileges.

## Technologies Used

- **Framework:** Spring Boot
- **Language:** Java 21
- **Database:** MySQL
- **Build Tool:** Maven
- **Email Handling:** JavaMail API

## Data Flow

### Controller

| Controller             | Functions Used                                                      |
|------------------------|---------------------------------------------------------------------|
| `AdminController`     | - `addAdmin`: Add a new admin user.<br/> - `addFoodItem`: Add a new food item to the menu. |
| `UserController`       | - `signUpUser`: Register a new user.<br/> - `signInUser`: Authenticate and sign in a user.<br/> - `addOrder`: Place a food order.<br/> - `getAllFoodItems`: Retrieve a list of all food items. |
| `VisitorController`    | - `getAllFoodItems`: Retrieve a list of all food items for visitors. |

### Services

| Service             | Functions Used                                                      |
|---------------------|---------------------------------------------------------------------|
| `AdminService`     | - `addAdmin`: Add a new admin user.<br/> - `ifAdminExistOrNot`: Check if an admin exists. |
| `AuthenticationService` | - `saveAuthToken`: Save an authentication token.<br/> - `authenticate`: Authenticate user based on an authentication token. |
| `FoodService`      | - `addFoodItem`: Add a new food item to the menu.<br/> - `isFoodInTheMenu`: Check if a food item exists in the menu.<br/> - `getAllFoodItems`: Retrieve a list of all food items. |
| `OrderService`     | - `addOrder`: Place a food order. |
| `UserService`      | - `signUpUser`: Register a new user.<br/> - `signInUser`: Authenticate and sign in a user.<br/> - `findFirstByUserEmail`: Retrieve a user by email. |

### Repository

| Repository             | Purpose                                    |
|------------------------|--------------------------------------------|
| `IAdminRepo`           | Access and manage admin users in the database. |
| `IAuthenticationRepo`  | Access and manage authentication tokens in the database. |
| `IFoodRepo`            | Access and manage food items in the database. |
| `IOrderRepo`           | Access and manage orders in the database. |
| `IUserRepo`            | Access and manage users in the database. |

## Database Design and Database Table

The database contains tables for users (including admins, normal users, and visitors), food items, orders, and authentication tokens.

### Database Design

### User Table

- **id (BIGINT - Primary Key)**: This field serves as the primary key, ensuring each user has a unique identifier.
- **username (VARCHAR(255))**: Stores the username of the user.
- **password (VARCHAR(255))**: Represents the hashed and salted password of the user.
- **email (VARCHAR(255))**: Contains the email address of the user.
- **role (VARCHAR(50))**: Denotes the role of the user, which can be "Admin," "Normal User," or "Visitor."
- **is_signed_up (BOOLEAN)**: Indicates whether the user is signed up (true) or not (false).
- **is_signed_in (BOOLEAN)**: Indicates whether the user is currently signed in (true) or not (false).
- **created_at (TIMESTAMP)**: Records the timestamp of when the user account was created.

### Food Item Table

- **id (BIGINT - Primary Key)**: A unique identifier for each food item.
- **name (VARCHAR(255))**: The name of the food item.
- **description (TEXT)**: Provides a detailed description of the food item.
- **price (DECIMAL(10,2))**: Stores the price of the food item with two decimal places.
- **type (VARCHAR(50))**: Represents the type of food item (e.g., "Appetizer," "Main Course").

### Order Table

- **id (BIGINT - Primary Key)**: A unique identifier for each order.
- **user_id (BIGINT - Foreign Key)**: A foreign key referencing the user who placed the order.
- **food_item_id (BIGINT - Foreign Key)**: A foreign key referencing the ordered food item.
- **status (VARCHAR(50))**: Indicates the status of the order, such as "Placed" or "Delivered."
- **order_date (TIMESTAMP)**: Records the timestamp when the order was placed.

### Detailed Database Design

### User Table

| Column Name    | Data Type    | Description                                           |
| -------------- | ------------ | ----------------------------------------------------- |
| id             | BIGINT (PK)  | Unique identifier for each user.                      |
| username       | VARCHAR(255) | Username of the user.                                 |
| password       | VARCHAR(255) | Password of the user (hashed and salted).             |
| email          | VARCHAR(255) | Email address of the user.                            |
| role           | VARCHAR(50)  | Role of the user (Admin, Normal User, Visitor).       |
| is_signed_up   | BOOLEAN      | Indicates whether the user is signed up or not.       |
| is_signed_in   | BOOLEAN      | Indicates whether the user is signed in or not.       |
| created_at     | TIMESTAMP    | Timestamp of user creation.                           |

### Food Item Table

| Column Name    | Data Type    | Description                                           |
| -------------- | ------------ | ----------------------------------------------------- |
| id             | BIGINT (PK)  | Unique identifier for each food item.                 |
| name           | VARCHAR(255) | Name of the food item.                                |
| description    | TEXT         | Description of the food item.                         |
| price          | DECIMAL(10,2)| Price of the food item.                               |
| type           | VARCHAR(50)  | Type of food item (e.g., Appetizer, Main Course).     |

### Order Table

| Column Name    | Data Type    | Description                                           |
| -------------- | ------------ | ----------------------------------------------------- |
| id             | BIGINT (PK)  | Unique identifier for each order.                     |
| user_id        | BIGINT (FK)  | Foreign key referencing the user who placed the order.|
| food_item_id   | BIGINT (FK)  | Foreign key referencing the ordered food item.        |
| status         | VARCHAR(50)  | Status of the order (e.g., Placed, Delivered).         |
| order_date     | TIMESTAMP    | Timestamp of order placement. 


## Data Structures Used in the Project


### Entity Classes

1. **User Entity**: This class represents the structure of user data in the database. It includes fields such as `id`, `username`, `password`, `email`, `role`, `is_signed_up`, `is_signed_in`, and `created_at`. These fields are mapped to corresponding columns in the User Table.

2. **Food Item Entity**: This class defines the structure for food item data in the database. It includes fields like `id`, `name`, `description`, `price`, and `type`. These fields are mapped to corresponding columns in the Food Item Table.

3. **Order Entity**: This class represents the structure of order data in the database. It includes fields such as `id`, `user`, `foodItem`, `status`, and `orderDate`. The `user` and `foodItem` fields are mapped as foreign keys to the User Table and Food Item Table, respectively.

### Data Transfer Object (DTO) Classes

1. **SignUpRequest**: This DTO class is used to encapsulate the data needed to sign up a new user. It includes fields for `username`, `password`, and `email`.

2. **SignInRequest**: This DTO class encapsulates the data required to sign in a user. It includes fields for `username` and `password`.

3. **FoodItemRequest**: This DTO class is used for adding a new food item to the system. It includes fields for `name`, `description`, `price`, and `type`.

4. **OrderRequest**: This DTO class represents the data needed to place a food order. It includes fields for `userId` and `foodItemId`.

5. **UserResponse**: This DTO class is used to send user-related information as a response. It includes fields for `id`, `username`, `email`, and `role`.

6. **FoodItemResponse**: This DTO class is used to send food item-related information as a response. It includes fields for `id`, `name`, `description`, `price`, and `type`.

7. **OrderResponse**: This DTO class is used to send order-related information as a response. It includes fields for `id`, `user` (containing user information), `foodItem` (containing food item information), `status`, and `orderDate`.

These data structures, both entity classes and DTO classes, are essential components of the project, defining the structure of data and requests/responses used in the system.
