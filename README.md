# EmployeeManagementSystem
Used Techs: {servlet, jsp, hibernate, postgres, springboot, springsecurity, spring data jpa}
* The Ripo is devied into
	- (1) "EmployeemanagementSystem" --> Servlet, JSP
	- (2) "springboot"_folder
		- (1) "EmployeemanagementSystem" --> SpringBoot, SpringSecurity, SSecurity JDBC Approach Auth
		- (2) "SSecurity_Auth_DifferentApproaches" --> implementing different approaches in spring security such as {in-memory, jdbc, oauth2, jwt}
			- (1) "JDBCAuth" --> spring security using _JDBC_ approach
			- (2) "SSecurity_JPA_CustomAuth" --> spring security using _JPA/Hibernate custom auth_ approach

  # SpringSecurity_Notes:
  	- "users", "authorities" are the default tables names s_security deal with, if u changed it
  	- 	u must provide a custom queries to handle it with JDBC_Manager for ex
  	- you passwords if you using default s_security algo must be prefixed with the id, ex: "{noop}password" --> for plain text || "{bcrypt}password"
  	- 	if you use PasswordEncoder encode your password first on this site "https://www.bcryptcalculator.com/" then put it without {id}
  	- in S_Security: your roles names must be prefixed with "ROLE_"

# Authentication Modules:

* Spring Security provides different ways to implement user auth:
	- In-Memory Authentication (spring-security-config): Allows storing users and roles in memory, useful for testing or simple applications.
 	- In-Memory: for development or small-scale applications
	- JDBC Auth (spring-security-jdbc): Enables user auth against a database using JDBC.
 	- JDBC Auth: for production use with user data stored persistently, consider JDBC or JPA authentication
  	* If you're using an existing authentication provider like LDAP or an authorization server (OAuth 2.0, OpenID Connect)
  	{
    	- LDAP Auth (spring-security-ldap): Provides support for auth users against an LDAP directory server.
    	- OAuth 2.0 Client (spring-security-oauth2-client): Enables applications to act as OAuth 2.0 clients, delegating authentication to an authorization server.
    	- OpenID Connect (spring-security-oauth2-client): Supports OpenID Connect, an extension of OAuth 2.0 for user identity information.
  	}

