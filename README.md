# Hack For LA: Calendaring Service

The Calendaring Service is an integration service that serves as a building block for Hack for LA projects that require com services (e.g. the ability to schedule meetings).  
#blah blah --> TODO {Project Name} helps {target users} accomplish {goal of project}. The {app/site/thing you're building}'s main features include {very brief feature descriptions}.

### Project context

Civic projects often exist within a larger context that may include multiple stakeholders, historic relationships, associated research, or other details that are relevant but not *required* for direct contributions. Gathering these details in one place is useful, but the ReadMe isn't that place. Use this section to [link to a Google Doc](#) or other documentation repository where contributors can dig in if they so choose. This is also a good place to link to your Code of Conduct.

### Technology used

- Java
- Springboot
- Docker
- PostgreSQL

## How to build and run the Calendaring Microservice

### Pre-requisites
Ask Google where to get them and how to install. Beware of OS dependencies.
- Java 17+
- MAVEN
- PostgreSQL

Once you have all of the dependecies installed: 

1. clone the repository to your computer
2. In the root directory create a .env file and copy over the contents .env.example
3. Fill in the missing values with your own or message a team member for them

### Using Docker

If you want to run the application using [Docker](https://www.docker.com/get-started/), make sure you have Docker installed on your computer and then from the root directory run:

```
docker-compose up
```
Then to stop the container run
```
docker-compose down
```
### Running locally

4. Inside a terminal from the root directory run ```mvn spring-boot:run```

#### Alternatively:

4. cd into the top level of the repo and run ```mvn package```
5. cd into the target directory that was created for you
6. run ```java -jar CalendaringMicroservice-0.0.1-SNAPSHOT.jar```

That's it! Your calendaring microservice is now up and running and ready to receive requests.  It should be up on port 8090. If you want to test the endpoints outside of the calendaring-fe service, you'll need postman to do it.

To see the API documentation, we have set up Swagger.  You can access the Swagger API Documentation locally from: http://localhost:8090/calendaring-service/swagger-ui.html#/

# How to contribute

Before getting started making contributions please message a team member in our Slack channel to get the necessary permissions.

Explain the different ways people can contribute. For example:

- Join the team {on Slack/at our weekly hack night/etc}.
- To help with user research, {do ABC}.
- To provide design support, {do XYZ}.
- To contribute to the code, follow the instructions below.

Remember to provide direct links to each channel.

### Working with issues

- Explain how to submit a bug.
- Explain how to submit a feature request.
- Explain how to contribute to an existing issue.

To create a new issue, please use the blank issue template (available when you click New Issue).  If you want to create an issue for other projects to use, please create the issue in your own repository and send a slack message to one of your hack night hosts with the link.


### Working with forks and branches

- Explain your guidelines here.


### Working with pull requests and reviews

- Explain your process.


### Testing

- Provide instructions.

# Contact info

Include at least one way (or more, if possible) to reach your team with questions or comments.


### Licensing

Include details about the project's open source status.

*this readme file sourced from [Jessica Sand](http://jessicasand.com/other-stuff/just-enough-docs/)*
