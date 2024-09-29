# OlympicGamesStarter

Ce projet est une API REST Spring connecté à une base de données Mysql. Une autre application frontend fonctionne grâce à des requêtes vers le backend de cette API.
Vous allez donc devoir configurer deux applications pour faire fonctionner l'ensemble.
Le but de cette application est de pouvoir mettre en relation des utilisateurs de location immobilières. On doit pouvoir créer, consulter, modifier des annonces ; envoyez des messages, et créer un compte utilisateur.

# Configuration de l'API :

## Téléchargement de l'application 
1. Téléchargez le backend à https://github.com/wil1708/Developpez-le-back-end-en-utilisant-Java-et-Spring dans un fichier zip et décompressez le.
## Installer la version 17 du sdk de Java
1. Cette application utilise la version 17 du sdk de Java. Faites en sorte de l'avoir installé sur votre ordinateur et qu'elle soit disponible dans votre IDE
2. Téléchargez la version JDK Amazon Corretto 17 ici en recherchant Amazon Corretto 17 sur votre moteur de recherche
## Configuration application.properties (fichier de conf de l'application Spring que vous avez téléchargé précédemment)
1. Ouvrez le fichier application.properties.template
2. Créez un fichier application.properties dans le même dossier et copiez le contenu de application.properties.template à l'intérieur
## Créer une base de données mysql
1. Téléchargez et installez la base de données MySQL (base de données en ligne de commande), vous allez devoir choisir un username et un password pour accéder à votre base données
2. Lancez la base données depuis votre système d'exploitatiion, elle devrait s'afficher dans une fenêtre de ligne de commande CMD et entrez le password que vous avez configuré à l'installation de MySQL
3. puis créez une base de donnée nommée chatop (les tables seront automatiquement créees à la première compilation de l'API), avec la commande suivante CREATE DATABASE chatop;
4. Dans votre nouveau fichier application.properties, modifiez les valeurs nécessaires de ces deux clés avec les identifiants de votre base de données mysql :
      spring.datasource.username=yourdatabaseusername
      spring.datasource.password=yourdatabasepassword
## Génération d'une clé cryptée pour le jwt.secret
1. Générez une clé cryptée de 256 bits avec ces commandes dans powershell :
    $bytes = New-Object byte[] 32
    [Security.Cryptography.RandomNumberGenerator]::Create().GetBytes($bytes)
    [BitConverter]::ToString($bytes).Replace("-", "")
   Remplacez la valeur de jwt.secret=créerUnTokenEnLigneDeCommande(voirREADME) avec la clé obtenue, dans votre fichier application.properties
   exemple :
   jwt.secret=DF5EF5C4C20A85CD2E21501797992764DB3ACA30B4B5877C6EDAE6F1012C18F7
## Utilisation de la base de données chatop
1. Utilisez la ligne de commande suivante pour lancer votre base de données apiChatop précédemment créee avec USE chatop;
## Création des tables
1. une fois toutes les étapes précédentes effectuées et après avoir lancé votre serveur de base de données MySQL, compilez l'application Spring en cliquant sur le bouton run, si tout se passe bien, elle va générer toute les tables à l'intérieur de votre base de données apiChatop
2. Vérifiez que les tables ont été créees dans votre base de données avec les commandes DESCRIBE user; 
 DESCRIBE rental;
 DESCRIBE message;
 DESCRIBE timestamped_entity;

# Configuration de l'application FrontEnd Angular :
1. Téléchargez l'application à https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring
2. Ouvrez le fichier proxy.config.json dans main/src et modifiez le port du localhost à 8080 à la place de 3001 pour le faire correspondre à celui de l'API REST Spring précédemment installée
3. Ouvrez le fichier environment.ts dans main/src/environments et modifiez le port du localhost à 8080 comme précédemment
4. Compilez l'application avec une ligne de commande : ng serve
5. Vous devez avoir précedemment lancé l'application Spring pour faire des requêtes depuis le portail web de l'application Angular
