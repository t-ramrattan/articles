# Articles Full Stack Application (SpringBoot, React, MongoDB)
This is an application that couples the backend and frontend into a single project. The frontend is a React single page 
application and the backend is a SpringBoot application. MongoDB Atlas is used as the data source.   

## Build & Run
###Backend & Frontend via Command Line

> Navigate to the backend directory.


Within a terminal navigate into the backend directory of the project.   
```
$ cd <path to project>/backend 
```
> Build the project using maven.
  
Executing the mvn package command will compile the backend and build the frontend. Both the backend and frontend code
will be packaged into a jar file. The built frontend files will be placed within the static resource directory of the jar.

```
$ mvn clean package
```
To verify that the application was built successfully navigate to the target directory. Then expand the classes directory
and verify that a static directory exists. In the static directory you should see a index.html file along with other files. 

 > Run the application from the terminal.  
   
The application can run from the terminal by running the jar file located in the target directory of the backend directory
with the required vm arguments.
 
```
$ java -Dspring.data.mongodb.username=<username> -Dspring.data.mongodb.password=<password> -Dspring.data.mongodb.host=<cluster> -Dspring.data.mongodb.database=<database> -jar <path to jar>
```

### Backend with an IDE
Within your favorite IDE build and run the backend with the following vm args.
```

-Dspring.data.mongodb.username=<username>
-Dspring.data.mongodb.password=<password>
-Dspring.data.mongodb.host=<cluster>
-Dspring.data.mongodb.database=<database>

```

### Frontend via NPM
> Install node modules  

The frontend code is located in the frontend directory. With a terminal navigate to the frontend directory and install 
the node modules.

```
$ npm i
```

> Start the development node server

```
$ npm start
```

The development server will start on port 3000. The proxy field within the package.json file has been set to
localhost:8080. All API requests will be proxied to the backend. CORS has not been enabled on the backend and
the requests URLs are relative paths within the frontend code, .i.e /api/articles. 
