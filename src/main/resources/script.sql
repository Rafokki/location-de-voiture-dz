create table demande_reservation
(
    id                    serial not null,
    date_de_retour        timestamp(6),
    date_prise_en_charge  timestamp(6),
    heure_de_retour       varchar(255),
    heure_prise_en_charge varchar(255),
    mail                  varchar(255),
    nom                   varchar(255),
    numero_tel            varchar(255),
    prenom                varchar(255),
    status                varchar(255),
    primary key (id)
)