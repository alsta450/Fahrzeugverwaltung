# Fahrzeugverwaltung
The goal of this project was a program that provides a client to manage e.g. a car rental. <br/>
It was created for the course [Programmiersprachen und -konzepte](https://ufind.univie.ac.at/de/course.html?lv=051030&semester=2020W) (University of Vienna). 

### About the project

The project was designed according to the specifications of the course.<br/>
Several classes offer the necessary functionality to create, save, edit and delete vehicles in a file. <br/>
All this can be handled by several inputs to the command line interface (client).

### Classes 
`Pkw`, `Lkw` and `Bike` extend the abstract class `Fahrzeug`. <br/>
The interface `FahrzeugDAO` provides methods to (de-)serialize Fahrzeuge to/from persistent files and is implemented in `SerializedFarhzeugDAO`. <br/>
`FahrzeugManagement` provides the methods for the client to make specific changes or get specific Fahrzeuge from the persistent file. <br/>
For example `show()` shows all Fahrzeuge saved in the file, or `oldest` returns the oldest Fahrzeug from that file. <br/>
`FahrzeugClient` provides the interface for the input. A few commands are listed here to get an idea of it's functionality
* `java FahrzeugClient <File> count` counts the number of Fahrzeuge in the file.
* `java FahrzeugClient <File> delete <id>` deletes the Fahrzeug with the matching id from the file.

### Insights and Learnings
* Serialization 
* Polyphormism and the use of instanceof-operator
* Dependency injection & inversion of control
* Streams
* Collections
