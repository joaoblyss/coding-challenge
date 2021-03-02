# ECB Exchange Rates Calculator

A simple EURO currency exchange calculator.

The user's input, in EUROS, will be converted to foreign currencies based on cross-currency rates provided by the
European Central Bank, which can be found in http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml.

Table of Contents
=================

* [Pre-Requisites](#Pre-Requisites)
* [Running the Project](#Running the Project)
    * [Configuring MongoDB](#Configuring MongoDB)

# Pre-Requisites

* Java 1.8 JDK
* A MongoDB instance fully accessible

# Running this Project

## Clone this Repository

In you system terminal, navigate to a source-code folder of your choice and clone the repository with the command

```bash
$ git clone https://github.com/joaoblyss/coding-challenge.git
$ cd coding-challenge
```

## Setup Up The Database Access

After checking out the project, you'll need to set the following properties in the `application.properties` file:

| Property Key | Description |
| --- | --- |
| `spring.data.mongodb.host` | Set here the MongoDB server address (host only) |
| `spring.data.mongodb.database` | Set here the name of the database you've created in the MongoDB server |

## Starting the Application

In the system terminal, execute the following:

```bash
$ ./mvnw spring-boot:run
```

> :warning: On Windows, you might need to use **mvnw.cmd** instead

This is how a successful startup should look like: 

<<IMAGE_SPOT>>

# Basic Usage
On the web browser, access `http://localhost:8080`. 

The GUI consists two basic input fields and one output, such as this:

<<IMAGE_SPOT>>

In the input field, type the value, in EURO, to be converted into a foreign currency, then select in the dropdown below the target currency. The result must be shown as below:

<<IMAGE_SPOT>>

>:construction: The text input still accepts alphabetic characters, but no calculation is performed unless the data input is a valid number. 

# Toubleshooting

## Fixing the Maven Wrapper Executable

Make sure the `mvnw` file has the propper execution permissions.

### Under Linux (and Cygwin)

```bash
$ chmod 744 mvnw
```

### Under Windows

```Batchfile
> icacls /grant:r Everyone:F mvnw.cmd
```

## Update CA Certificates

During the execution of the **mvnw** packaging tool under Ubuntu, there might be a failure concerning the *trustAnchors*
parameter, with the error message ```trustAnchors parameter must be non-empty```. To work around this, it might be necessary to purge certificates by doing as follows:

* remove the `/etc/ssl/certs/java/cacerts`
* run `update-ca-certificates -f`
* retry `mvnw spring-boot:run`

********