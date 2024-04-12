# E-Commerce Coupon System

## Overview
This project implements a flexible coupon system for an e-commerce website using Java and Spring Boot. The coupon system allows for different discount strategies without requiring major rewrites in the future. Coupons may have multiple rules and discount types, and only one coupon can be applied at a time.

## Features
 1. Coupon Rules: Implement rules such as minimum cart total and minimum item count.
 2. Coupon Discount Types: Implement discount types such as fixed amount off, percentage off, and mixed discounts.
## API Endpoints:
```/cart ``` 
Returns the list of items in the cart along with the total price.

```/coupon/{couponCode}``` Accepts a coupon code, validates it, and returns the adjusted price and discount amount.
Coupon Configuration: Provide coupon codes with their respective rules and discount types.

## Code Organization

The codebase is organized into separate packages for:

*Controllers: Handle API requests.

*Services: Implement business logic.

*Repositories: Interact with the database.

*Models: Define data structures.

*Enumeration: For enums

*Exception: For handling Exception

## Setup
```git clone  ```

```cd ```

```mvn clean install```

```mvn spring-boot:run```

## Usage

*Use API endpoints /cart and /coupon to interact with the system.

*Test different coupon codes to verify their functionality.

## Coupon Configuration
 
## FIXED10:
### Rules:
Cart total price must be greater than $50 before discounts.
Cart must contain at least 1 item.
### Discount: 
$10 off total (fixed amount off).

## PERCENT10:
### Rules:
Cart total price must be greater than $100 before discounts.
Cart must contain at least 2 items.
### Discount:
10% off total (percent off).

## MIXED10:
### Rules:
Cart total price must be greater than $200 before discounts.
Cart must contain at least 3 items.
### Discount:
10% or $10 off total, whichever is greatest.

## REJECTED10:
### Rules:
Cart total price must be greater than $1000 before discounts.
### Discount:
$10 off total or 10% off total, whichever is greatest.


## DOCUMENTATION
http://localhost:8080/swagger-ui/index.html