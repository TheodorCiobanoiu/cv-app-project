# JWT Authentication
[![License: MPL 2.0](https://img.shields.io/badge/License-MPL_2.0-brightgreen.svg)](https://opensource.org/licenses/MPL-2.0)
[![GitHub version](https://badge.fury.io/gh/xrusu%2Fjwt-auth.svg)](https://badge.fury.io/gh/xrusu%2Fjwt-auth)
[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)](https://github.com/xrusu/jwt-auth/graphs/commit-activity)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

JSON Web Token Authentication using Java and Spring Boot


## About
This is an Auth Rest API, written in Spring Boot, that receives a username and password and registers the user. Authentication is then made using JWTs.

This is a real-world example of JWT Auth and is production-ready.

## Usage
Clone the repository and build with Gradle `./gradlew build`. Make sure that your database is running.

Check if the project is running by starting Swagger in your preferred browser. You can find the Swagger documentation [here](http://localhost:8095/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/). Make sure that the dev profile is activated, otherwise you won't be able to access swagger.


## API Reference (Swagger)
After you run the project locally, swagger can be found [here](http://localhost:8095/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/), if dev profile is activated.

To activate the dev profile, edit the run configuration and add the following line:
`dev` in the Active profiles field.

## License
This project is licensed under the [Mozilla Public License 2.0](https://www.mozilla.org/en-US/MPL/2.0/).

## Others
For other projects / cool stuff, follow me on
[GitHub](https://github.com/xrusu)
