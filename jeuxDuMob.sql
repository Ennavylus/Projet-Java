#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Utilisateur
#------------------------------------------------------------

CREATE TABLE Utilisateur(
        id     Int  Auto_increment  NOT NULL ,
        Pseudo Varchar (50) NOT NULL ,
        mail   Varchar (60) NOT NULL ,
        mdp    Varchar (50) NOT NULL
	,CONSTRAINT Utilisateur_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Partie
#------------------------------------------------------------

CREATE TABLE Partie(
        id       Int  Auto_increment  NOT NULL ,
        JHPartie Datetime NOT NULL ,
        nbMobWin Int NOT NULL
	,CONSTRAINT Partie_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: historique
#------------------------------------------------------------

CREATE TABLE historique(
        id       Int  Auto_increment  NOT NULL ,
        nbPartie Int ,
        Victoire Int ,
        defaite  Int
	,CONSTRAINT historique_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: joue
#------------------------------------------------------------

CREATE TABLE joue(
        id        Int NOT NULL ,
        id_Partie Int NOT NULL ,
        win       Bool NOT NULL
	,CONSTRAINT joue_PK PRIMARY KEY (id,id_Partie)

	,CONSTRAINT joue_Utilisateur_FK FOREIGN KEY (id) REFERENCES Utilisateur(id)
	,CONSTRAINT joue_Partie0_FK FOREIGN KEY (id_Partie) REFERENCES Partie(id)
)ENGINE=InnoDB;

