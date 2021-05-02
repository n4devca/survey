CREATE TABLE survey_user (
    id IDENTITY NOT NULL,
    username varchar(100) NOT NULL,
    passwd varchar(250) NOT NULL,
    name varchar(250) NOT NULL,
    version int NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    UNIQUE (username)
);

CREATE TABLE authority (
  id IDENTITY NOT NULL,
  code varchar(50)  NOT NULL,
  version int NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE (code)
);

CREATE TABLE user_authority (
  user_id int NOT NULL,
  authority_id int NOT NULL,
  version int NOT NULL DEFAULT 0,
  PRIMARY KEY (user_id, authority_id),
  CONSTRAINT ua_auth_id_auth_fk FOREIGN KEY (authority_id) REFERENCES authority (id) ON DELETE CASCADE,
  CONSTRAINT ua_user_id_user_fk FOREIGN KEY (user_id) REFERENCES survey_user (id) ON DELETE CASCADE
);

CREATE TABLE question_type (
  id IDENTITY NOT NULL,
  code varchar(50)  NOT NULL,
  version int NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE (code)
);

CREATE TABLE survey (
  id IDENTITY NOT NULL,
  name varchar(100)  NOT NULL,
  version int NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
);

CREATE TABLE question (
  id IDENTITY NOT NULL,
  question varchar(500) NOT NULL,
  question_type_id int NOT NULL,
  survey_id int NOT NULL,
  version int NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  CONSTRAINT q_question_type_fk FOREIGN KEY (question_type_id) REFERENCES question_type (id),
  CONSTRAINT q_survey_id_fk FOREIGN KEY (survey_id) REFERENCES survey (id) ON DELETE CASCADE
);


