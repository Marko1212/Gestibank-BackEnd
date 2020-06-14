# GESTIBANK

Projet de gestion des comptes bancaires en ligne

Le projet comporte deux applications : front end et back end.

## Technologies back end utilis�es: 
Java, Spring Boot, Hibernate, MySQL

## Installation

Pour d�marrer le projet, il faut installer toutes les d�pendances.
```bash
mvn clean
mvn install
mvn spring-boot:run
```



## Caract�ristiques principales

- Podrzan rad sa tekucim

Implemented so far:

- 

## Structure du projet

- com.gesti.bank.controller - est utilis� comme dispatcher pour les requ�tes http
- com.gesti.bank.dto - d�clarations des objects qui s'�changent entre le client et le serveur
- com.gesti.bank.model - entit�s qui mappent les tables de la base de donn�es relationnelle MySQL
- com.gesti.gank.repository - Java persistence API pour le travail avec la base de donn�es
- com.gesti.bank.service - d�claration de la logique m�tier
- com.gesti.bank.service.impl - impl�mentation de la logique m�tier



