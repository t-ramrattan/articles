# Articles Full Stack Application (SpringBoot, React, MongoDB)
This is an application that couples the backend and frontend into a single project. The frontend is a React single page 
application and the backend is a SpringBoot application. MongoDB Atlas is used as the data source.

## Local Development
The backend and front end code can be developed independently.
### Backend
The backend code is located in the backend directory. When the application is started it will use port 8080 by default.
The following VM arguments are needed.
```

-Dspring.data.mongodb.username=<username>
-Dspring.data.mongodb.password=<password>-Dspring.data.mongodb.host=<cluster>
-Dspring.data.mongodb.database=<database>

```

### Frontend
The frontend code is located in the frontend directory. When the frontend is served by the node development server all
api calls will be proxied to the backend application.
> yarn start # will start a node server on port 3000

## Build and Run
### Build Application
The frontend and backend can be built by simply running. The frontend application will be built and placed into the
static resource folder of the backend application.
> mvn clean package

### Run Application
> java -Dspring.data.mongodb.username=<username> -Dspring.data.mongodb.password=<password> -Dspring.data.mongodb.host=<cluster> -Dspring.data.mongodb.database=<database> -jar <path to jar>
