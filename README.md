# Poseidon v1.4 - 28/09/2020

## Technical:

1. Framework: Spring Boot v2.3.2
2. Java 8
3. Thymeleaf
4. Bootstrap v.4.3.1
5. MySQL 8.0 
6. H2 Database for Integration tests.

## Database
bdd: poseidon with credentials stored in system environment variables:
 ${DB_USER} = "poseidon_user" and  ${DB_PWD} = "Tadm-123"
 
SQL script named shema.sql available in poseidon/doc folder.
The script insert 2 users: admin & user both using "Tadm-123" password.

## Content:
- This v1.4 version add RegExpPatternTest (for UserDTO password),
resolve Thymeleaf display bugs on date fields and solve delete bug in BidListService.


Previously:
- The first version only contains the CRUD functionalities of the BidList class.
- Version v0.2 adds integration test and fix Checktyle issues in BidList classes.
- Version 0.3 adds the CRUD functionalities of the CurvePoint class.
- Version 0.4 adds the CRUD functionalities of the Rating class and improve
fields validation for the 3 entities.
- Version 0.5 adds the CRUD functionalities of the RuleName class.
- The v0.6 version adds the CRUD functionalities of the Trade class, fixes 
Checkstyle issues, improves unit tests and provides Integration tests.
- The v0.7 version adds the CRUD functionalities of the User class with its
 Integration tests.
- The v1.0 version provides a security login layer with jwt authentication.
- The v1.1 version provides SWAGGER2 Documentation.
- The v1.2 version customizes SWAGGER2 Documentation and provides a LoginControllerTest.
- The v1.3 version hide database credentials in system environment variables 
and update SQL script (in Poseidon/doc folder).
