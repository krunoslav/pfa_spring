	ALTER SEQUENCE pfa_doc_template_sequence RESTART WITH 1000;
	ALTER SEQUENCE pfa_document_sequence RESTART WITH 1000;
	ALTER SEQUENCE pfa_picture_sequence RESTART WITH 1000;
	ALTER SEQUENCE pfa_tenant_prop_sequence RESTART WITH 1000;
	ALTER SEQUENCE pfa_tenant_sequence RESTART WITH 1000;
	ALTER SEQUENCE pfa_user_sequence RESTART WITH 1000;
	TRUNCATE TABLE pfa_tenant CASCADE;
	TRUNCATE TABLE pfa_tenant_properties CASCADE;
	TRUNCATE TABLE pfa_document CASCADE;
	TRUNCATE TABLE pfa_document_template CASCADE;
	TRUNCATE TABLE pfa_picture CASCADE;
	TRUNCATE TABLE pfa_template_picture CASCADE;
	TRUNCATE TABLE pfa_user CASCADE;
	TRUNCATE TABLE pfa_user_properties CASCADE;
		
	INSERT INTO pfa_tenant_properties(id,creation_date,last_change_date)  VALUES (1,current_timestamp,current_timestamp);
	INSERT INTO pfa_tenant (id,creation_date,last_change_date,tenant_name,tenant_properties_id) VALUES  (1,current_timestamp,current_timestamp,'PONGE', 1);
	INSERT INTO pfa_user_properties (id,creation_date,last_change_date) VALUES (1,current_timestamp,current_timestamp);
	INSERT INTO pfa_user_properties (id,creation_date,last_change_date) VALUES (2,current_timestamp,current_timestamp);
			
		
	INSERT INTO pfa_user (id , creation_date ,last_change_date , address , date_of_birth ,last_access_time, username, users_password,surname , users_name,tenant_id,user_properties) 
		VALUES  (1,current_timestamp,current_timestamp,'Sjenjak 5, 31000 Osijek','1979-01-01',current_timestamp,'test','test','Samardžić','Krunoslav',1 , 1);
	INSERT INTO pfa_user (id , creation_date ,last_change_date , address , date_of_birth ,last_access_time, username, users_password,surname , users_name,tenant_id,user_properties) 
		VALUES  (2,current_timestamp,current_timestamp,'Sjenjak 131, 31000 Osijek','1976-09-28',current_timestamp,'test1','test1','Samardžić','Damir',1 , 2);