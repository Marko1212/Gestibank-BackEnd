# GESTIBANK

Projet de gestion des comptes bancaires en ligne

Le projet comporte deux parties : front end et back end.

Dans ce repository se trouve le code de la partie back end.

## Technologies back end utilisées: 
Java EE, Spring Boot, Hibernate, MySQL

## Installation

Pour démarrer le projet, il faut installer toutes les dépendances.
```bash
mvn clean
mvn install
mvn spring-boot:run
```

## Fonctionnalités principales

- GestiBank est un site de gestion bancaire en ligne, qui simule la création et la gestion de comptes
bancaires des clients via le Web. Dans ce cadre, l’application GestiBank répond principalement à 2 objectifs :
- Création, suivi et la gestion des comptes bancaires pour les clients de la banque.
- Administration et paramétrage des comptes pour les agents de la banque.

## Ont été implémentés pour le moment:

- L'Espace Public: 
- Permettre à tout le monde de demander l’ouverture de comptes bancaires
- Permettre à tout le monde de connaître le cours de la devise grâce à un Web service

- L'Espace Admin:
- Gestion des agents (CRUD) et la recherche des agents (search)

## Structure du projet

- com.gesti.bank.controller - est utilisé comme dispatcher pour les requêtes http
- com.gesti.bank.dto - déclarations des objets qui s'échangent entre le client et le serveur
- com.gesti.bank.model - entités qui sont associées aux tables de la base de données relationnelle MySQL
- com.gesti.gank.repository - Java persistence API pour le travail avec la base de données
- com.gesti.bank.service - déclaration de la logique métier
- com.gesti.bank.service.impl - implémentation de la logique métier



