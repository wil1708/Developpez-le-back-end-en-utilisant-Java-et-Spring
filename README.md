# OlympicGamesStarter

Ce projet est une API REST Spring connecté à une base de données Mysql. Une autre application frontend fonctionne grâce à des requêtes vers le backend de cette API.
Vous allez donc devoir configurer deux applications pour faire fonctionner l'ensemble.
Le but de cette application est de pouvoir mettre en relation des utilisateurs de location immobilières. On doit pouvoir créer, consulter, modifier des annonces ; envoyez des messages, et créer un compte utilisateur.

# Veuillez suivre la procédure ci-jointe pour la configuration des applications :

API :
1. Téléchargez le backend à https://github.com/wil1708/Developpez-le-back-end-en-utilisant-Java-et-Spring
2. Cette application utilise la version 17 du sdk de Java. Faites en sorte de l'avoir installé sur votre ordinateur et qu'elle soit disponible dans votre IDE
3. Ouvrez le fichier application.properties.template
4. Créez un fichier application.properties et copié le contenu de application.properties.template
5. Modifiez les valeurs nécessaires de ces deux clés avec les identifiants de votre base de données mysql :
   spring.datasource.username=yourdatabaseusername
   spring.datasource.password=yourdatabasepassword
   puis créez une base de donnée nommée apiChatop (les tables seront automatiquement créees à la première compilation de l'API)
   puis générer une clé cryptée de 256 bits avec ces commandes dans powershell :
    $bytes = New-Object byte[] 32
    [Security.Cryptography.RandomNumberGenerator]::Create().GetBytes($bytes)
    [BitConverter]::ToString($bytes).Replace("-", "")
   Remplacez la valeur de jwt.secret=créerUnTokenEnLigneDeCommande(voirREADME) avec la clé obtenue dans votre fichier application.properties
   exemple :
   jwt.secret=DF5EF5C4C20A85CD2E21501797992764DB3ACA30B4B5877C6EDAE6F1012C18F7
6. Lancez votre serveur de base de données mysql et compilez l'API
7. Vérifiez que les tables ont été créees dans votre base de données

FrontEnd Angular :
1. Téléchargez l'application à https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring
2. Ouvrez le fichier proxy.config.json dans main/src et modifiez le port du localhost à 8080 à la place de 3001 pour le faire correspondre à celui de l'API REST Spring précédemment installée
3. Ouvrez le fichier environment.ts dans main/src/environments et modifiez le port du localhost à 8080 comme précédemment
4. Compilez l'application avec une ligne de commande : ng serve
