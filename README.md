# Java Pro Questionarium

It is educational project

Tasks
1. Write the right project structure
2. Practice the learned material on Java SE
3. Component testing
4. Create a console application that can:
	- display random questions on topics
	- add questions to the database
	- delete questions
5. Add functionality that will display the question in a random order, the difficulty lies in the fact that the choice of the question must take place in the database, and not in the application. You need to come up with an SQL query that will return a random record from the Question table.
6. Cover the application with tests
7. Move the project to Hibernate

First, the configuration for connecting to the database was written. Two tables Question and Attempt are created. The first is for storing questions, and the second is for storing statistics on how many times an existing topic was called and how many times a non-existent one.

Appropriate entities have been created to display relational data in an object-oriented way. DAOs were used to manipulate data from the database.

The program is covered with tests, as mocks for repositories were created new classes.

The latest commits added support for Hibernate.

At startup, a choice of operations appears: adding a question, deleting, showing statistics... Since there are several options for choosing, it was decided to use the factory method pattern - depending on the selected one, one or another scenario is executed.


## Workflow

1. Download the project from GitHub
2. Create "postgres" database (you can change configurations in ConnectionSingleton or HibConfig classes) in PostgreSQL DBMS
3. Change login and password in the configuration class
4. Execute the script.sql file from the "resources" directory to the current database
5. Run the project


## Technologies

1. Java SE 19
2. Maven
3. PostgreSQL
4. Hibernate, HQL
5. JDBC
6. Lombok
7. JUnit 4
8. IOC
9. GIT
10. Patterns
	- Singleton
	- Factory Method



