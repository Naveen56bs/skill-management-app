-- THIS FILE:
-- V1.0__base_schema.sql files takes care of:
--  base schema creation of tables.

-- NEXT FILE:
-- For db migration of any upcoming release, add script to a file
-- and name the file as V2.0__SomeReleventName.sql with respective to the change the script doing in this file
-- and place the file in this same package db.migration under resources.


CREATE TABLE IF NOT EXISTS public.employee_detail (
	employee_id  varchar(255) NOT NULL,
	employee_first_name varchar(255) NOT NULL,
	employee_last_name varchar(255) NOT NULL,
	created_on timestamp NOT NULL,
	updated_on timestamp NOT NULL,
	CONSTRAINT employee_id_pk PRIMARY KEY (employee_id)
);

CREATE TABLE IF NOT EXISTS public.skillset_detail (
	skill_id varchar(255) NOT NULL,
	skill_name varchar(255) NOT NULL,
	created_on timestamp NOT NULL,
	updated_on timestamp NOT NULL,
	CONSTRAINT skill_id_pk PRIMARY KEY (skill_id)
);


CREATE TABLE IF NOT EXISTS public.skillset_management_detail (
	employee_id  varchar(255) NOT NULL,
	skill_id varchar(255) NOT NULL,
	level varchar(255) NOT NULL,
	updated_on timestamp NOT NULL,
	CONSTRAINT emp_id_fk FOREIGN KEY (employee_id) REFERENCES employee_detail(employee_id) ON DELETE CASCADE,
	CONSTRAINT skill_id_fk FOREIGN KEY (skill_id) REFERENCES skillset_detail(skill_id) ON DELETE CASCADE
);



