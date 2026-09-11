CREATE DATABASE IF NOT EXISTS videoflow_db CHARACTER SET utf8mb4;
USE videoflow_db;

DROP TABLE IF EXISTS project_members;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS media_files;
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(120) NOT NULL,
  email VARCHAR(160) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  role VARCHAR(30) NOT NULL
);
CREATE TABLE projects (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(150) NOT NULL,
  client VARCHAR(150) NOT NULL,
  status VARCHAR(50) NOT NULL,
  deadline DATE NOT NULL,
  owner_id BIGINT NOT NULL,
  FOREIGN KEY (owner_id) REFERENCES users(id)
);
CREATE TABLE tasks (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(180) NOT NULL,
  project_id BIGINT NOT NULL,
  FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);
CREATE TABLE media_files (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  file_name VARCHAR(255) NOT NULL,
  project_id BIGINT NOT NULL,
  FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);
CREATE TABLE comments (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  text VARCHAR(255) NOT NULL,
  task_id BIGINT NOT NULL,
  FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE
);
CREATE TABLE project_members (
  project_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  PRIMARY KEY (project_id, user_id),
  FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

INSERT INTO users(name,email,password,role) VALUES
('Strahinja Blagojevic','admin@videoflow.rs','$2b$12$/KqcRCY5fXpf7T57oIEMWOi7CSr493X3.uiIsE2UN0bXwj2VwitWK','ROLE_ADMIN'),
('Video editor','editor@videoflow.rs','$2b$12$T3kLBPSdvbx9r9nSw8Lb4e1pgWchSPxQh1kP4idXRCfTkW6Ss3Txe','ROLE_USER');
INSERT INTO projects(name,client,status,deadline,owner_id) VALUES ('AI reklama','Demo klijent','U TOKU','2026-10-01',1);
INSERT INTO project_members VALUES (1,1),(1,2);
INSERT INTO tasks(title,project_id) VALUES ('Prva montaza',1);
INSERT INTO media_files(file_name,project_id) VALUES ('reklama-v1.mp4',1);
INSERT INTO comments(text,task_id) VALUES ('Skratiti uvod.',1);
