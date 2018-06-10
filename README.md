# Spring boot TDD
Spring boot TDD project to a banking application

Application currently handles 2 operations :

- deposit : deposit an amount on account
- history : get account list of operation

## Database settings

Project comes with a H2 database 

Go to http://localhost:<port>/springboot/h2-console/ to connect to database if needed

# Unit tests
Unit tests have been implemented for controller AccountController

# Exercices - TODO

- withdraw : implement withdraw of an amount
- transfer : implement transfer of an amout from an account to another account

- Implement unit tests for AccountController methods (withdraw, transfer and history)
- Implement unit tests for AccountService operations (withdraw, transfer and history)
- Implement unit tests for OperationRepository and OperationRepository methods
