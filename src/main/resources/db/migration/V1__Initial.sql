CREATE TABLE if not exists demande_reservation
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
    reference             uuid,
    status                varchar(255),
    voiture_id            integer,
    primary key (id)
    );

create table if not exists voiture
(
    id                   serial not null,
    annee_de_circulation varchar(255),
    couleur              varchar(255),
    immatriculation      varchar(255),
    marque               varchar(255),
    model                varchar(255),
    reference            uuid,
    prix_de_location numeric(38,2),
    primary key (id)
);

create table if not exists reservation
(
    id                     serial not null,
    montant_total          numeric(38, 2),
    nombre_de_jour         integer,
    reference              uuid,
    status                 varchar(255),
    demande_reservation_id integer,
    primary key (id)
);

ALTER TABLE if exists demande_reservation
    add constraint fk_voiture_id
    foreign key (voiture_id)
    references voiture (id);


ALTER TABLE if exists reservation
    add constraint fk_demande_reservation
    foreign key (demande_reservation_id)
    references demande_reservation (id);
