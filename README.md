# GESTIBANK

Projet de gestion des comptes bancaires en ligne

Le projet comporte deux applications : front end et back end.

## Technologies back end utilisées: 
Java, Spring Boot, Hibernate, MySQL

## Installation

Pour démarrer le projet, il faut installer toutes les dépendances.
```bash
mvn clean
mvn install
mvn spring-boot:run
```



## Caractéristiques principales

- Podrzan rad sa tekucim

Implemented so far:

- 

## Structure du projet

- com.gesti.bank.controller - est utilisé comme dispatcher pour les requêtes http
- com.gesti.bank.dto - déclarations des objects qui s'échangent entre le client et le serveur
- com.gesti.bank.model - entités qui mappent les tables de la base de données relationnelle MySQL
- com.gesti.gank.repository - Java persistence API pour le travail avec la base de données
- com.gesti.bank.service - déclaration de la logique métier
- com.gesti.bank.service.impl - implémentation de la logique métier



