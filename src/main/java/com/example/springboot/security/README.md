In this folder we found all the files and configurations to have spring security authentication with JWT.

Spring security provides a /login endpoint  to manage the authentications with JWT, you only need to complete each of the following files to make this work

* AuthCredentials: This class is used for the data to bet received in the login endpoint.
* JWTAuthenticationFilter: In this class is where the logic of the login endpoint will be added, we have two steps, one to get the user from the database (check is the password is ok) and other step to create the token to eb returned to the user.
* JWTAuthorizationFilter: In this class we add the logic to authorize the resources to a authenticated user (using the roles).
* TokeUtils: Here is where we encrypt and decrypt the data of the user that is in the token.
* UserDetailServiceImpl: Check If the user is in the database
* UserDetail: A class that is needed fir spring security, and is uses to access the data of the user in all the  module.
* WebSecurityConfig: Here we add all the filters to be used in the security chain (and also define the function to decrypt the password from the database)