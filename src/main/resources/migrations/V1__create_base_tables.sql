-- Create Attribute table
CREATE TABLE attribute (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50) NOT NULL UNIQUE,
    short_name VARCHAR(10) NOT NULL UNIQUE,
    description TEXT NOT NULL
);

-- Create Expertise table
CREATE TABLE expertise (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL UNIQUE,
    base_attribute_id uuid NOT NULL,
    trained_only BOOLEAN DEFAULT FALSE,
    charge_penalty BOOLEAN DEFAULT FALSE,
    kit_needed BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (base_attribute_id) REFERENCES attribute(id)
);

-- Create BaseStatus table
CREATE TABLE base_status (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    hp_base INT NOT NULL,
    hp_level INT NOT NULL,
    ep_base INT NOT NULL,
    ep_level INT NOT NULL,
    san_base INT NOT NULL,
    san_level INT NOT NULL
);

-- Create Role table
CREATE TABLE role (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL UNIQUE,
    proficiencies TEXT NOT NULL,
    base_expertises INT NOT NULL,
    base_status_id uuid NOT NULL,
    FOREIGN KEY (base_status_id) REFERENCES base_status(id)
);

-- Create Role-Expertise junction table (many-to-many)
CREATE TABLE role_expertise (
    role_id uuid NOT NULL,
    expertise_id uuid NOT NULL,
    PRIMARY KEY (role_id, expertise_id),
    FOREIGN KEY (role_id) REFERENCES role(id),
    FOREIGN KEY (expertise_id) REFERENCES expertise(id)
);

-- Create Origin table
CREATE TABLE origin (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    power_name VARCHAR(100) NOT NULL,
    power_description TEXT NOT NULL
);

-- Create Origin-Expertise junction table (many-to-many)
CREATE TABLE origin_expertise (
    origin_id uuid NOT NULL,
    expertise_id uuid NOT NULL,
    PRIMARY KEY (origin_id, expertise_id),
    FOREIGN KEY (origin_id) REFERENCES origin(id),
    FOREIGN KEY (expertise_id) REFERENCES expertise(id)
);

-- Create Token table
CREATE TABLE token (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    nex INT NOT NULL CHECK (nex >= 0 AND nex <= 99),
    origin_id uuid NOT NULL,
    role_id uuid NOT NULL,
    FOREIGN KEY (origin_id) REFERENCES origin(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);

-- Create Token-Expertise junction table (many-to-many)
CREATE TABLE token_expertise (
    token_id uuid NOT NULL,
    expertise_id uuid NOT NULL,
    PRIMARY KEY (token_id, expertise_id),
    FOREIGN KEY (token_id) REFERENCES token(id),
    FOREIGN KEY (expertise_id) REFERENCES expertise(id)
);
