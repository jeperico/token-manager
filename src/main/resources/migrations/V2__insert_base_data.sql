-- Insert Attributes
INSERT INTO attribute (name, short_name, description) VALUES
('Agility', 'AGI', 'Agility attr!'),
('Intellect', 'INT', 'Intellect attr!'),
('Presence', 'PRE', 'Presence attr!'),
('Strength', 'STR', 'Strength attr!'),
('Vigor', 'VIG', 'Vigor attr!');

-- Insert Expertises
INSERT INTO expertise (name, base_attribute_id, trained_only, charge_penalty, kit_needed) VALUES
('Acrobatics', 1, FALSE, FALSE, FALSE),
('Athletics', 4, FALSE, FALSE, FALSE),
('Current Affairs', 2, FALSE, FALSE, FALSE),
('Sciences', 2, FALSE, FALSE, FALSE),
('Crime', 1, FALSE, FALSE, FALSE),
('Diplomacy', 3, FALSE, FALSE, FALSE),
('Deception', 3, FALSE, FALSE, FALSE),
('Fortitude', 5, FALSE, FALSE, FALSE),
('Stealth', 1, FALSE, FALSE, FALSE),
('Initiative', 1, FALSE, FALSE, FALSE),
('Intimidation', 3, FALSE, FALSE, FALSE),
('Intuition', 3, FALSE, FALSE, FALSE),
('Investigation', 2, FALSE, FALSE, FALSE),
('Fight', 4, FALSE, FALSE, FALSE),
('Medicine', 2, FALSE, FALSE, FALSE),
('Occultism', 2, FALSE, FALSE, FALSE),
('Perception', 3, FALSE, FALSE, FALSE),
('Piloting', 1, FALSE, FALSE, FALSE),
('Aim', 1, FALSE, FALSE, FALSE),
('Profession', 2, FALSE, FALSE, FALSE),
('Reflexes', 1, FALSE, FALSE, FALSE),
('Religion', 3, FALSE, FALSE, FALSE),
('Survival', 3, FALSE, FALSE, FALSE),
('Tactics', 2, FALSE, FALSE, FALSE),
('Technology', 2, FALSE, FALSE, FALSE),
('Will', 3, FALSE, FALSE, FALSE);

-- Insert Base Status
INSERT INTO base_status (hp_base, hp_level, ep_base, ep_level, san_base, san_level) VALUES
(20, 4, 2, 2, 12, 3),  -- Combatant
(16, 3, 3, 3, 16, 4),  -- Specialist
(12, 2, 4, 4, 20, 5);  -- Occultist

-- Insert Roles
INSERT INTO role (name, proficiencies, base_expertises, base_status_id) VALUES
('Combatant', 'Simple weapons, tactical weapons, lightweight protection', 3, 1),
('Specialist', 'Simple weapons, lightweight protection', 5, 2),
('Occultist', 'Simple weapons', 4, 3);

-- Insert Role-Expertise relationships
INSERT INTO role_expertise (role_id, expertise_id) VALUES
(1, 14),  -- Combatant: Fight
(1, 19),  -- Combatant: Aim
(2, 13),  -- Specialist: Investigation
(2, 25),  -- Specialist: Technology
(3, 26),  -- Occultist: Will
(3, 16);  -- Occultist: Occultism

-- Insert Origins
INSERT INTO origin (name, description, power_name, power_description) VALUES
('T.I.', 'Programador, engenheiro de software ou simplesmente "o cara da T.I.", você tem treinamento e experiência para lidar com sistemas informatizados.', 'Motor de Busca', 'A critério do Mestre, sempre que tiver acesso a internet, você pode gastar 2 PE para substituir um teste de perícia qualquer por um teste de Tecnologia.'),
('Fighter', 'Você pratica uma arte marcial ou esporte de luta, ou cresceu em um bairro perigoso onde aprendeu briga de rua.', 'Mão Pesada', 'Você recebe +2 em rolagens de dano com ataques corpo a corpo.'),
('Military', 'Você serviu em uma força militar, como o exército ou a marinha.', 'Para Bellum', 'Você recebe +2 em rolagens de dano com armas de fogo.');

-- Insert Origin-Expertise relationships
INSERT INTO origin_expertise (origin_id, expertise_id) VALUES
(1, 13),  -- T.I.: Investigation
(1, 25),  -- T.I.: Technology
(2, 14),  -- Fighter: Fight
(2, 21),  -- Fighter: Reflexes
(3, 19),  -- Military: Aim
(3, 24);  -- Military: Tactics
