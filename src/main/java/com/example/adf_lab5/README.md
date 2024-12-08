@Author: Luke Kenny
@StudentId: R00212866


@Description: Lab 5 + 6 App Dev Frameworks

This Spring Boot application provides a RESTful API for managing a pet database. 
Users can create, read, update, delete, and filter pet records. 
It uses H2 Database for storage and supports various functionalities 
such as retrieving pet statistics and filtering pets by specific criteria.

Technologies Used
Spring Boot (Java Framework)
Spring Data JPA (Database access)
H2 Database (In-memory database)
Lombok (Reduce boilerplate code)

Pet Management
Method	    Endpoint	    Description
POST	    /api/pets	    Add a new pet to the database.
GET	        /api/pets	    Retrieve all pets.
GET	        /api/pets/{id}	Retrieve a pet by its unique ID.
PUT	        /api/pets/{id}	Update the details of an existing pet.
DELETE	    /api/pets/{id}	Remove a pet from the database using its ID.

Additional Functionalities
Method	    Endpoint	                Description
DELETE	    /api/pets/name/{name}	    Remove all pets with a specific name (case-insensitive).
GET	        /api/pets/type/{animalType}	Retrieve pets filtered by their animal type.
GET	        /api/pets/breed/{breed}	    Retrieve pets filtered by breed, ordered by age.
GET	        /api/pets/info	            Retrieve only the name, animal type, and breed of pets.
GET	        /api/pets/statistics	    Get pet statistics (average age, oldest age, total count).



