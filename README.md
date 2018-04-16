# Atelier

> Atelier - LP CISIIE

## Etudiants
- BAIER Geoffrey
- DUBOIS Morgan
- MATMAT Myriam
- WILMOUTH Steven

## Prérequis
- npm
- maven
- docker, docker-compose

## Installation - Linux, Mac
Se rendre dans le dossier installation_linux, puis exécuter les commandes suivantes :

- installer l'api java
<pre>./installApi.sh</pre>

- démarrer l'api java
<pre>./startApi.sh</pre>

- stopper l'api java
<pre>./stopApi.sh</pre>

- démarrer la partie back office
<pre>./startBackOffice.sh</pre>

- démarrer la partie player
<pre>./startPlayer.sh</pre>

## Insertion de données dans l'api java
Pour faire fonctionner l'api il vous faudra des données dans la base de Postgres.
Pour cela, se rendre dans le dossier installation_linux, puis exécuter la commande suivante :

<pre>./insertData.sh</pre>

Celle-ci va demander un mot de passe pour se connecter à la base de données, entrer <code>td1-docker</code>.
Puis, exécuter la commande suivante :

<pre>\i docker-entrypoint-initdb.d/data.sql</pre>

Enfin taper <code>\q</code> pour quitter le shell de Postgres.

:warning: Il n'y a aucun compte et série créé dans l'api, pour tester l'application vous devrez vous inscrire et créer votre série.

## Configurations
> Configurer l'application joueur
- Modification du lien vers l'API JEE
> Editer le fichier **config.js** qui se trouve dans le dossier **webAppPlayer/src/config**
> Editer la variable **url** par votre url d'API
> Créer une série dans l'application back-office
[Documentation application joueur](https://github.com/MorganDbs/Atelier/blob/master/vuejs/webAppPlayer/README.md)

> Configurer l'application back-office
- Modification du lien vers l'API JEE
> Editer le fichier **index.js** qui se trouve dans le dossier **webAppBackOffice/src/configApi**
> Editer la variable **baseURL** par votre url d'API
[Documentation application back-office](https://github.com/MorganDbs/Atelier/blob/master/vuejs/webAppBackOffice/README.md)


[Documentation de l'API](https://github.com/MorganDbs/Atelier/blob/master/jee/README.md)

