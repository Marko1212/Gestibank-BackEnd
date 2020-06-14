# GESTIBANK

Projet de gestion des comptes bancaires en ligne

Le projet comporte deux parties : front end et back end.

Dans ce repository se trouve le code de la partie back end.

## Technologies back end utilis�es: 
Java EE, Spring Boot, Hibernate, MySQL

## Installation

Pour d�marrer le projet, il faut installer toutes les d�pendances.
```bash
mvn clean
mvn install
mvn spring-boot:run
```

## Fonctionnalit�s principales

- GestiBank est un site de gestion bancaire en ligne, qui simule la cr�ation et la gestion de comptes
bancaires des clients via le Web. Dans ce cadre, l�application GestiBank r�pond principalement � 2 objectifs :
- Cr�ation, suivi et la gestion des comptes bancaires pour les clients de la banque.
- Administration et param�trage des comptes pour les agents de la banque.

## Ont �t� impl�ment�s pour le moment:

- L'Espace Public: 
- Permettre � tout le monde de demander l�ouverture de comptes bancaires
- Permettre � tout le monde de conna�tre le cours de la devise gr�ce � un Web service

- L'Espace Admin:
- Gestion des agents (CRUD) et la recherche des agents (search)

## Structure du projet

- com.gesti.bank.controller - est utilis� comme dispatcher pour les requ�tes http
- com.gesti.bank.dto - d�clarations des objets qui s'�changent entre le client et le serveur
- com.gesti.bank.model - entit�s qui sont associ�es aux tables de la base de donn�es relationnelle MySQL
- com.gesti.gank.repository - Java persistence API pour le travail avec la base de donn�es
- com.gesti.bank.service - d�claration de la logique m�tier
- com.gesti.bank.service.impl - impl�mentation de la logique m�tier



