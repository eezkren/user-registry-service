# user-registry-service
Service to manage the user registry

## run kafka : docker-compose up -d

We want to develop a new service to manage the user registry in our company.
The user provides this information in the registry process (All the fields are mandatory):
Name
Surname
Email
Phone
Country based on https://en.m.wikipedia.org/wiki/ISO_3166-1_alpha-2#Officially_assigned_code_elements

This registry is received via HTTP using an API defined for the registry, and this API accepts
this information in json format in the request body or provided from a html form with the values
defined as request parameters. In the future we can accept more variants of this API but the
registry process will be the same.
We need to provide to the clients that uses our API a good feedback system to know the details
about any kind of error in the registry.
In this version we only going to develop the registry process, we don’t need any use case to
retrieve user information.
We can't allow users with incorrect email format.
We reject a registry if user exists in a database.
In a first version, we want to registry only users from spain, we will reject a registry from another
country.
Each country will have his own phone validation, now we have only spanish validation but in the
future we can have more accepted countries and distincts validations for each one.
For this first version, the validation of the Spanish phone is:
Start with a prefix +34, and the length number is 9 characters, for example +34678666999.
Total length will be 9 +3, number and prefix.
We work with a third party that provides us a validation of the email and returns if the user email
is in a black list of the government. We can't registry users that are in this black list.
In this first version we don't have the API of our provider, but we know that the next users are
defined in this black list:
user986@gmail.com
evil@gmail.com
chuck_norris@gmail.com

In the future we will develop the solution to integrate with our provider via HTTP.
We are going to store this user information in a database memory, in the future we can change
it to another type of database.
In our platform we have another services that needs information about the new users, we need
to notify this services that we have a new registry with the information provided by the user.

In order to be flexible with future changes, we need that the infrastructure of registry use case
can be modified without require a long time of development.
This parts of the infrastructure are the third party validation, the database storage and the
communication with the other services.
Also, we want to have a quick development when we need to add more countries (accept new
country and create new phone format for it)
We want that the code of this services is always ready for production, so we need a good
coverage of testing and avoid the manual testing when we develop changes and we deploy it.

About the technical details, we want to have a service based on hexagonal architecture, DDD
and developers must use TDD and consider SOLID principles to offer a better solution to this
feature using Spring Boot 2.* and java 8 or superior version. You can use any library that you
consider useful to solve the challenge.
The main goal of this challenge is not doing it fast, we want that the developer applies this
concepts in his approach. if he/she has never applied hexagonal architecture, DDD or TDD
he/she can investigate about it and try to develop a solution for this challenge.