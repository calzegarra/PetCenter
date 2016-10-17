drop database if exists bd_PetCenter;

create database bd_PetCenter;
USE bd_PetCenter;

-- --------------------;
-- / CREAR LAS TABLAS /;
-- --------------------;

CREATE TABLE tb_cliente (
	idCliente			int				NOT NULL AUTO_INCREMENT,
	codCliente 			VARCHAR 		(10) 	NOT NULL,
	idTipoCliente 			tinyInt			 	NOT NULL,
	idTipoDocumento	 		tinyInt			 	NOT NULL,
	nroDocumento			VARCHAR			(15)	NOT NULL,
	nomCliente			VARCHAR			(30)	NOT NULL,
	apePaternoCliente		VARCHAR			(30)	NOT NULL,
	apeMaternoCliente		VARCHAR			(30)	NOT NULL,
	idGeneroCliente			tinyInt				NOT NULL,
	fecNacCliente			DATE				NOT NULL,
	idSede				tinyInt				NOT NULL,
	idDistrito			int				NOT NULL,
	direcCliente			VARCHAR			(50)	NOT NULL,
	celCliente			VARCHAR			(30)	NOT NULL,
	telfDomCliente			VARCHAR			(30)	NOT NULL,
	telfTraCliente			VARCHAR			(30)	NOT NULL,
	indNotificaciones		BOOLEAN				NOT NULL,
	correoElectCliente		VARCHAR			(80)	NOT NULL,
	estadoCliente			tinyInt				NOT NULL,	
	CONSTRAINT pk_cliente PRIMARY KEY (idCliente)
);
CREATE UNIQUE INDEX idx_cliente_codigo ON tb_cliente(codcliente ASC);

CREATE TABLE tb_mascota(
	idMascota			int				NOT NULL AUTO_INCREMENT,
	codMascota			VARCHAR			(10)	NOT NULL,
	nomMascota			VARCHAR			(30)	NOT NULL,
	idCliente			int				NOT NULL,
	idRelClienteMascota		tinyInt				NOT NULL,		
	idRaza				tinyInt				NOT NULL,
	fotoMascota			blob				NOT NULL,
	estadoMascota			tinyInt				NOT NULL,
	descMascota			VARCHAR			(150)	NOT NULL,
	idGeneroMascota			tinyInt				NOT NULL,
	fechaNacMascota			DATE				NOT NULL,
	CONSTRAINT pk_mascota PRIMARY KEY (idMascota)
);
CREATE UNIQUE INDEX idx_mascota_codigo ON tb_mascota(codMascota ASC);

CREATE TABLE tb_especie(
	idEspecie			tinyInt				NOT NULL AUTO_INCREMENT,
	descripcionEspecie		VARCHAR			(50)	NOT NULL,
	CONSTRAINT pk_especie PRIMARY KEY (idEspecie)
);

CREATE TABLE tb_raza(
	idRaza				tinyInt				NOT NULL AUTO_INCREMENT,
	descripcionRaza			VARCHAR			(50)	NOT NULL,
	idEspecie			tinyInt				NOT NULL, 
	CONSTRAINT pk_raza PRIMARY KEY (idRaza)
);

CREATE TABLE tb_generomascota(
	idGeneroMascota			tinyInt				NOT NULL AUTO_INCREMENT,
	descripcionGenMascota		VARCHAR			(50)	NOT NULL,
	CONSTRAINT pk_generoMascota PRIMARY KEY (idGeneroMascota)
);

CREATE TABLE tb_fichaControl(
	idFichaControl			int				NOT NULL AUTO_INCREMENT,
	idMascota			int 				NOT NULL,
	observaciones			VARCHAR			(300)	NOT NULL,
	fechaControl			DATE				NOT NULL,
	proximoControl			DATE				NOT NULL,
	estadoFicha			tinyInt 			NOT NULL,
	CONSTRAINT pk_fichaControl PRIMARY KEY (idFichaControl)
);

CREATE TABLE tb_tipocliente(
	idTipoCliente 			tinyInt				NOT NULL AUTO_INCREMENT,
	descripcionTipoCliente 		VARCHAR			(50)	NOT NULL,
	CONSTRAINT pk_tipoCliente PRIMARY KEY (idTipoCliente)
);

CREATE TABLE tb_tipodocumento(
	idTipoDocumento			tinyInt				NOT NULL AUTO_INCREMENT,
	descripcionTipoDocumento 	VARCHAR			(50)	NOT NULL,
	CONSTRAINT pk_tipoDocumento PRIMARY KEY (idTipoDocumento)
);

CREATE TABLE tb_relacionclientemascota(
	idRelClienteMascota 		tinyInt				NOT NULL AUTO_INCREMENT,
	descripcionRelClienteMascota  	VARCHAR			(50)	NOT NULL,
	CONSTRAINT pk_relClienteMascota PRIMARY KEY (idRelClienteMascota)
);

CREATE TABLE tb_sede(
	idSede 		 		tinyInt				NOT NULL AUTO_INCREMENT,
	descripcionSede 	 	VARCHAR			(50)	NOT NULL,
	CONSTRAINT pk_sede PRIMARY KEY (idSede)
);

CREATE TABLE tb_pais(
	idPais 		 		int				NOT NULL AUTO_INCREMENT,
	descripcionPais 	 	VARCHAR			(50)	NOT NULL,
	CONSTRAINT pk_pais PRIMARY KEY (idPais)
);

CREATE TABLE tb_provincia(
	idProvincia 	 		int				NOT NULL AUTO_INCREMENT,
	descripcionProvincia 	 	VARCHAR			(50)	NOT NULL,
	idPais 		 		int				NOT NULL,
	CONSTRAINT pk_provincia PRIMARY KEY (idProvincia)
);

CREATE TABLE tb_distrito(
	idDistrito	 		int				NOT NULL AUTO_INCREMENT,
	descripcionDistrito 	 	VARCHAR			(50)	NOT NULL,
	idProvincia 	 		int				NOT NULL,
	CONSTRAINT pk_distrito PRIMARY KEY (idDistrito)
);

CREATE TABLE tb_generocliente(
	idGeneroCliente  		tinyInt				NOT NULL AUTO_INCREMENT,
	descripcionGeneroCliente 	VARCHAR			(50)	NOT NULL,
	CONSTRAINT pk_generoCliente PRIMARY KEY (idGeneroCliente)
);

CREATE TABLE tb_promocion(
	idPromocion	 		int				NOT NULL AUTO_INCREMENT,
	descripcionProm 	 	VARCHAR			(50)	NOT NULL,
	CONSTRAINT pk_promocion PRIMARY KEY (idPromocion)
);

CREATE TABLE tb_consolidadoBanPel(
	idConsolidadoBanPel		int				NOT NULL AUTO_INCREMENT,
	descripcionBanPel 	 	VARCHAR			(50)	NOT NULL,
	CONSTRAINT pk_consolidadobanpel PRIMARY KEY (idConsolidadoBanPel)
);

CREATE TABLE tb_contactosecundario(
	idContactoSecundario 		int				NOT NULL AUTO_INCREMENT,
	idParentContactoSecCli		tinyINT				NOT NULL,
	idcliente			int				NOT NULL,
	nomContactoSec	 	 	VARCHAR			(30)	NOT NULL,	
	apePaternoContactoSec		VARCHAR			(30)	NOT NULL,
	apeMaternoContactoSec		VARCHAR			(30)	NOT NULL,
	celContactoSec			VARCHAR			(30)	NOT NULL,
	telfDomContactoSec		VARCHAR			(30)	NOT NULL,
	descContactoSecundario		VARCHAR			(150)	NOT NULL,
	CONSTRAINT pk_contactoSecundario PRIMARY KEY (idContactoSecundario)
);


CREATE TABLE tb_parentContactoSecCli(
	idParentContactoSecCli		tinyINT				NOT NULL AUTO_INCREMENT,
	descParentContactoSec		VARCHAR			(50)	NOT NULL,
	CONSTRAINT pk_parentContactoSecCli PRIMARY KEY (idParentContactoSecCli)
);


-- ------------------------CREAR RESTRICCIONES (FK);
-- 01 Cliente;
ALTER TABLE tb_cliente
ADD CONSTRAINT fk_cliente_distrito
FOREIGN KEY	(idDistrito)
REFERENCES tb_distrito(idDistrito);

ALTER TABLE tb_cliente
ADD CONSTRAINT fk_cliente_sede
FOREIGN KEY	(idSede)
REFERENCES tb_sede(idSede);

ALTER TABLE tb_cliente
ADD CONSTRAINT fk_cliente_tipoCliente
FOREIGN KEY	(idtipoCliente)
REFERENCES tb_tipocliente(idtipoCliente);

ALTER TABLE tb_cliente
ADD CONSTRAINT fk_cliente_tipoDocumento
FOREIGN KEY	(idTipoDocumento)
REFERENCES tb_tipodocumento(idTipoDocumento);

ALTER TABLE tb_cliente
ADD CONSTRAINT fk_cliente_GeneroCliente
FOREIGN KEY	(idGeneroCliente)
REFERENCES tb_generocliente(idGeneroCliente);

-- 02 MASCOTA;
ALTER TABLE tb_mascota
ADD CONSTRAINT fk_mascota_cliente
FOREIGN KEY	(idCliente)
REFERENCES tb_cliente(idCliente);

ALTER TABLE tb_mascota
ADD CONSTRAINT fk_mascota_raza
FOREIGN KEY	(idRaza)
REFERENCES tb_raza(idRaza);

ALTER TABLE tb_mascota
ADD CONSTRAINT fk_mascota_genero
FOREIGN KEY	(idGeneroMascota)
REFERENCES tb_generomascota(idGeneroMascota);

ALTER TABLE tb_mascota
ADD CONSTRAINT fk_mascota_relacionCliente
FOREIGN KEY	(idRelClienteMascota)
REFERENCES tb_relacionclientemascota(idRelClienteMascota);


-- 03 DISTRITO;
ALTER TABLE tb_distrito
ADD CONSTRAINT fk_distrito_provincia
FOREIGN KEY	(idProvincia)
REFERENCES tb_provincia(idProvincia);

-- 04 PROVINCIA;
ALTER TABLE tb_provincia
ADD CONSTRAINT fk_provincia_pais
FOREIGN KEY	(idPais)
REFERENCES tb_pais(idPais);

-- 05 CONTACTOSECUNDARIO;
ALTER TABLE tb_contactosecundario
ADD CONSTRAINT fk_contactoSecundario_Parentesco
FOREIGN KEY	(idParentContactoSecCli)
REFERENCES tb_parentContactoSecCli(idParentContactoSecCli);


-- 05 RAZA;
ALTER TABLE tb_raza
ADD CONSTRAINT fk_raza_especie
FOREIGN KEY	(idEspecie)
REFERENCES tb_especie(idEspecie);

-- ---------------------------------------------;  
-- --- /        DATA        / ------------------;
-- ---------------------------------------------;

-- ---------- tabla TIPOCLIENTE -------------;
insert into tb_tipocliente (descripcionTipoCliente) values ('NATURAL');
insert into tb_tipocliente (descripcionTipoCliente) values ('EMPRESA');
insert into tb_tipocliente (descripcionTipoCliente) values ('ONG');

-- ---------- tabla TIPOdocumento -------------
insert into tb_tipodocumento (descripcionTipoDocumento) values ('DNI');
insert into tb_tipodocumento (descripcionTipoDocumento) values ('RUC');
insert into tb_tipodocumento (descripcionTipoDocumento) values ('CARNET EXTRANJERIA');
insert into tb_tipodocumento (descripcionTipoDocumento) values ('PASAPORTE');
insert into tb_tipodocumento (descripcionTipoDocumento) values ('OTROS');

-- ---------- tabla GENEROCLIENTE -------------;
insert into tb_generocliente (descripcionGeneroCliente) values ('MASCULINO');
insert into tb_generocliente (descripcionGeneroCliente) values ('FEMENINO');
insert into tb_generocliente (descripcionGeneroCliente) values ('OTROS');

-- ---------- tabla SEDE -------------;
insert into tb_sede (descripcionSede) values ('SALAMANCA');
insert into tb_sede (descripcionSede) values ('PACHACAMAC');
insert into tb_sede (descripcionSede) values ('MIRAFLORES');
insert into tb_sede (descripcionSede) values ('LA MOLINA');

-- ---------- tabla PAIS -------------;
insert into tb_pais (descripcionPais) values ('PERU');
insert into tb_pais (descripcionPais) values ('ARGENTINA');
insert into tb_pais (descripcionPais) values ('BRASIL');
insert into tb_pais (descripcionPais) values ('CHILE');
insert into tb_pais (descripcionPais) values ('ECUADOR');
insert into tb_pais (descripcionPais) values ('BOLIVIA');
insert into tb_pais (descripcionPais) values ('OTROS');

-- ---------- tabla PROVINCIA -------------;
insert into tb_provincia (descripcionProvincia,idPais) values ('LIMA',1);
insert into tb_provincia (descripcionProvincia,idPais) values ('TACNA',1);
insert into tb_provincia (descripcionProvincia,idPais) values ('AREQUIPA',1);
insert into tb_provincia (descripcionProvincia,idPais) values ('CUSCO',1);
insert into tb_provincia (descripcionProvincia,idPais) values ('TUMBES',1);
insert into tb_provincia (descripcionProvincia,idPais) values ('CHICLAYO',1);
insert into tb_provincia (descripcionProvincia,idPais) values ('BUENOS AIRES',2);
insert into tb_provincia (descripcionProvincia,idPais) values ('SAO PAOLO',3);
insert into tb_provincia (descripcionProvincia,idPais) values ('SANTIAGO',4);
insert into tb_provincia (descripcionProvincia,idPais) values ('QUITO',5);
insert into tb_provincia (descripcionProvincia,idPais) values ('LA PAZ',6);
insert into tb_provincia (descripcionProvincia,idPais) values ('TANGAMANDAPIO',7);

-- ---------- tabla DISTRITO  -------------;
insert into tb_distrito (descripcionDistrito,idProvincia) values ('Chorrillos',1);
insert into tb_distrito (descripcionDistrito,idProvincia) values ('Miraflores',1);
insert into tb_distrito (descripcionDistrito,idProvincia) values ('Barranco',1);
insert into tb_distrito (descripcionDistrito,idProvincia) values ('Villa El Salvador',1);
insert into tb_distrito (descripcionDistrito,idProvincia) values ('Surco',1);
insert into tb_distrito (descripcionDistrito,idProvincia) values ('Surquillo',1);
insert into tb_distrito (descripcionDistrito,idProvincia) values ('Magdalena',1);
insert into tb_distrito (descripcionDistrito,idProvincia) values ('San Juan de Miraflores',1);
insert into tb_distrito (descripcionDistrito,idProvincia) values ('Villa Maria',1);

-- ---------- tabla PARENTESCO CONTACTO SECUNDARIO -------------;
insert into tb_parentcontactoseccli (descParentContactoSec) values ('SOBRINO');
insert into tb_parentcontactoseccli (descParentContactoSec) values ('HIJO');
insert into tb_parentcontactoseccli (descParentContactoSec) values ('HERMANO');
insert into tb_parentcontactoseccli (descParentContactoSec) values ('TIO');
insert into tb_parentcontactoseccli (descParentContactoSec) values ('OTROS');

-- ---------- tabla ESPECIE -------------;
insert into tb_especie (descripcionEspecie) values ('CANINO');
insert into tb_especie (descripcionEspecie) values ('FELINO');
insert into tb_especie (descripcionEspecie) values ('ROEDOR');
insert into tb_especie (descripcionEspecie) values ('AVE');
insert into tb_especie (descripcionEspecie) values ('OTROS');

-- ---------- tabla RAZA -------------;
insert into tb_raza (idEspecie,descripcionRaza) values (1,'PITBUL');
insert into tb_raza (idEspecie,descripcionRaza) values (1,'SIBERIANO');
insert into tb_raza (idEspecie,descripcionRaza) values (1,'BOXER');
insert into tb_raza (idEspecie,descripcionRaza) values (1,'Chow Chow');
insert into tb_raza (idEspecie,descripcionRaza) values (1,'Cairn Terrier');
insert into tb_raza (idEspecie,descripcionRaza) values (1,'San Bernardo');
insert into tb_raza (idEspecie,descripcionRaza) values (1,'Shih Tzu');
insert into tb_raza (idEspecie,descripcionRaza) values (1,'Pekines');
insert into tb_raza (idEspecie,descripcionRaza) values (1,'Pastor Aleman');
insert into tb_raza (idEspecie,descripcionRaza) values (1,'Schnauzer');
insert into tb_raza (idEspecie,descripcionRaza) values (2,'Persa');
insert into tb_raza (idEspecie,descripcionRaza) values (2,'Coon Maine');
insert into tb_raza (idEspecie,descripcionRaza) values (2,'Siameses');
insert into tb_raza (idEspecie,descripcionRaza) values (2,'Ragdoll');
insert into tb_raza (idEspecie,descripcionRaza) values (2,'Oriental');

-- ---------- tabla GENERO MASCOTA -------------;
insert into tb_generomascota (descripcionGenMascota) values ('MACHO');
insert into tb_generomascota (descripcionGenMascota) values ('HEMBRA');

-- ---------- tabla RELACION CLIENTE MASCOTA -------------;
insert into tb_relacionclientemascota (descripcionRelClienteMascota) values ('DUEÑO');
insert into tb_relacionclientemascota (descripcionRelClienteMascota) values ('CONTACTO ANONIMO');

-- ---------- tabla CLIENTE -------------;
insert into tb_cliente (codCliente,idTipoCliente,idTipoDocumento,nroDocumento,nomCliente,
				apePaternoCliente,apeMaternoCliente,idGeneroCliente,
					fecNacCliente,idSede,idDistrito,direcCliente,
					celCliente,telfDomCliente,telfTraCliente,
					indNotificaciones,correoElectCliente,estadoCliente)
		values ('CLI0000001',1,1,'10233775','Cecilia','Ramirez','Ludeña',2,'1980/1/15',1,1,
			 'Av. Revolucion 1247','99784514','2457866','4512487',1,
				'celiciaramirez@gmail.com',1);

insert into tb_cliente (codCliente,idTipoCliente,idTipoDocumento,nroDocumento,nomCliente,
				apePaternoCliente,apeMaternoCliente,idGeneroCliente,
					fecNacCliente,idSede,idDistrito,direcCliente,
					celCliente,telfDomCliente,telfTraCliente,
					indNotificaciones,correoElectCliente,estadoCliente)
		values ('CLI0000002',1,1,'10226478','Manuel','Garcia','Ruiz',1,'1978/4/16',1,2,
			 'Jr. Miramar 452','94441114','2411866','3512487',1,
				'manuelgr@gmail.com',1);

insert into tb_cliente (codCliente,idTipoCliente,idTipoDocumento,nroDocumento,nomCliente,
				apePaternoCliente,apeMaternoCliente,idGeneroCliente,
					fecNacCliente,idSede,idDistrito,direcCliente,
					celCliente,telfDomCliente,telfTraCliente,
					indNotificaciones,correoElectCliente,estadoCliente)
		values ('CLI0000003',1,1,'10354612','Raul','Morales','Olivos',1,'1977/1/22',1,3,
			 'Jr. Las Begonias 1452','98741114','2311266','4512487',1,
				'manuelgr@gmail.com',1);

-- ---------- tabla CONTACTO SECUNDARIO -------------;
insert into tb_contactosecundario (idParentContactoSecCli,idcliente,nomContactoSec,apePaternoContactoSec,
					apeMaternoContactoSec,celContactoSec,
					telfDomContactoSec,descContactoSecundario) 
		values (1,1,'JAMES','PARIONA','ROJAS','997130854','287-0442',
			'Llamarlo por las tardes en la mañana no puede contestar');

insert into tb_contactosecundario (idParentContactoSecCli,idcliente,nomContactoSec,apePaternoContactoSec,
					apeMaternoContactoSec,celContactoSec,
					telfDomContactoSec,descContactoSecundario) 
		values (1,2,'MIGUEL','BORJAS','APAZA','944130854','233-0442',
			'Llamar de preferencia al celular');

insert into tb_contactosecundario (idParentContactoSecCli,idcliente,nomContactoSec,apePaternoContactoSec,
					apeMaternoContactoSec,celContactoSec,
					telfDomContactoSec,descContactoSecundario) 
		values (2,3,'JHON','PARIONA','RIVERA','995630854','286-6442',
			'Persona morena de 1.70m aprox.');


-- ---------- tabla MASCOTA -------------;
insert into tb_mascota (codMascota,nomMascota,idCliente,idRelClienteMascota,idRaza,fotoMascota,
				estadoMascota,descMascota,idGeneroMascota,fechaNacMascota)
		values ('MAS0000001','Firulay',1,1,1,'',1,'presenta alergias al spray antipulgas',
				1,'2014/05/12');

insert into tb_mascota (codMascota,nomMascota,idCliente,idRelClienteMascota,idRaza,fotoMascota,
				estadoMascota,descMascota,idGeneroMascota,fechaNacMascota)
		values ('MAS0000002','Duquesa',2,1,2,'',1,'problemas al respirar',
				2,'2010/05/14');

insert into tb_mascota (codMascota,nomMascota,idCliente,idRelClienteMascota,idRaza,fotoMascota,
				estadoMascota,descMascota,idGeneroMascota,fechaNacMascota)
		values ('MAS0000003','Bronco',3,1,4,'',1,'presenta problemas de la vista de un ojo',
				1,'2011/01/22');


