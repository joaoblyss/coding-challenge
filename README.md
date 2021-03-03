# ECB Exchange Rates Calculator

A simple EURO currency exchange calculator.

The user's input, in EUROS, will be converted to foreign currencies based on cross-currency rates provided by the
European Central Bank, which can be found in http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml.

Table of Contents
=================

* [Pre-Requisites](#pre-requisites)
* [Running this Project](#running-this-project)
    * [Clone this Repository](#clone-this-repository)
    * [Setup Up The Database Access](#setup-up-the-database-access)
    * [Starting the Application](#starting-the-application)
* [Troubleshooting](#troubleshooting)
  * [Fixing the Maven Wrapper Executable](#fixing-the-maven-wrapper-executable)
    * [Under Linux (and Cygwin)](#under-linux-and-cygwin)
    * [Under Windows](#under-windows)
  * [Update CA Certificates](#update-ca-certificates)

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

![spring-run](https://user-images.githubusercontent.com/5368476/109731523-320fac00-7b9a-11eb-8f82-c7e490843dc8.JPG)


# Basic Usage
On the web browser, access `http://localhost:8080`. 

The GUI consists two basic input fields and one output, such as this:

![calculator](https://user-images.githubusercontent.com/5368476/109731758-a6e2e600-7b9a-11eb-80b7-587bd5174b7f.JPG)


In the input field, type the value, in EURO, to be converted into a foreign currency, then select in the dropdown below the target currency. The result must be shown as below:

![calc-result](https://user-images.githubusercontent.com/5368476/109731784-ae09f400-7b9a-11eb-890c-51987a37242e.JPG)


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
