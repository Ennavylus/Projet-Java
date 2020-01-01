# Readme 

Voici mon projet Java pour le Callac Soft College Session 9 

## Description de l'application

 L'application est une sorte de plateforme de jeux , avec une interface utlisateur, création de compte et modification, une partie administrateur.
 Il y a aussi la possibiliter de jouer a un jeu ( sur les regle du jeux des Kangourou)

## Installation 

### Logiciel Requis:
 Un logiciel de Base de donné ( Actuellement utiliser MySQL Workench)
 Eclipse (Version: 2019-09 R (4.13.0))
 java jdk 12 (openJdk)

### Base de Donnée
 Ouvrez votre WorkBench, 
 Créer une nouvelle Base de données(Schemas)
 ensuite ouvrez chaque script SQL en commencent par jeuxDuMob.sql, puis executez le script

### Lien GitLab
 Rendez vous sur la page principale du projet , puis copiez l'Url

 <img src="imageReadme\clonegit.png" width="350">

 
### Lancez ECLIPSE
 Créer ou utiliser votre WorkSpace en cours

 ensuite cliquez sur file, puis Import...

 <img src ="imageReadme/import.png" height = "250">

 Selectionnez dans le repertoire Git , Project from Git

<img src ="imageReadme/importSelection.png" height = "250">

 Selectionnez ensuite Clone URL 

 <img src="imageReadme\importGitSelection.png" width="350">


collez l'Url précedement copiez dans la case URl,
renseignez egalement votre nom de compte et mot de passe  de GitLab dans la partie Authentification puis next

<img src="imageReadme\importUrl.png" width="350">

cliquez sur next, ensuite

Selectionner ensuite l'emplacement de telechargement puis next

<img src="imageReadme\selectImportFile.png" width="350">


ensuite next, et finish 

l'import ces normalement bien effectué.
Ensuite avant de lancer le projet nous devant renseigner l'adresse, l'user et le password de la base de donné

aller donc dans le fichier DataBase.java
    - renseigner a la ligne 17 entre le "/" et le "?" le nom de votre base de donné
    - ligne 18 renseigner dans user :  votre nom user de votre base de donnée et dans pwsd : votre mot de passe d'acces a la base de donnée 

<img src="imageReadme\Changement bdd.png" height="450">



une fois tout ceci fais vous aller enfin pouvoir tester le projet , 
donc 
cliqué sur la petite fleche noir qui est a droite du premier run(premiere fleche verte) et selectionné run As, puis java Application

<img src="imageReadme\runProject.png" height="200">

cliquez sur Ok 

<img src="imageReadme\okRun.png" height="300">


puis bon Test !

<p>
<img src="imageReadme\goodGame.png" height="300">
</p>

PS :  pour avoir acces a l'interface admin connecter vous avec 

pseudo : admin
<br>mot de passe : admin
