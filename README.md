# itakademija-voting-system

### Launching the app

#### No tests
	mvn clean spring-boot:run
#### With tests
	mvn clean verify spring-boot:run
#### Address
	http://localhost:8080/
### Database (h2 connection console):
	http://localhost:8080/h2-console/
	jdbc:h2:/home/arnoldasurbelis/Desktop/RinkSis/db/h2/database-dev;IFEXISTS=TRUE
### Swagger documentation:
	http://localhost:8080/swagger-ui.html#/
### Data Preloading
	/voting-system/src/test/java/preloader/DeletePrefillDbMin.java
	1) cleans DB but leaves admin:pass
	2) then fills DB with data
### Login
	Login - admin
	Password - pass
	Representive - ZV, OB - and others
	Password - 123
### Email
	LINK:		https://www.zoho.com/mail/login.html
	LOGIN:		vrk.sistema@zoho.com
	PASSWORD:	sistema.vrk
