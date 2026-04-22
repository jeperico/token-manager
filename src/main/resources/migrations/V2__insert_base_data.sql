-- Insert Attributes
INSERT INTO attribute (name, short_name, description) VALUES
('Agility', 'AGI', 'Agility attr!'),
('Intellect', 'INT', 'Intellect attr!'),
('Presence', 'PRE', 'Presence attr!'),
('Strength', 'STR', 'Strength attr!'),
('Vigor', 'VIG', 'Vigor attr!');

-- Insert Expertises (using subqueries to get attribute UUIDs)
INSERT INTO expertise (name, base_attribute_id, trained_only, charge_penalty, kit_needed) VALUES
('Acrobatics', (SELECT id FROM attribute WHERE short_name = 'AGI'), FALSE, TRUE, FALSE),
('Athletics', (SELECT id FROM attribute WHERE short_name = 'STR'), FALSE, FALSE, FALSE),
('Current Affairs', (SELECT id FROM attribute WHERE short_name = 'INT'), TRUE, FALSE, FALSE),
('Sciences', (SELECT id FROM attribute WHERE short_name = 'INT'), TRUE, FALSE, FALSE),
('Crime', (SELECT id FROM attribute WHERE short_name = 'AGI'), TRUE, TRUE, TRUE),
('Diplomacy', (SELECT id FROM attribute WHERE short_name = 'PRE'), FALSE, FALSE, FALSE),
('Deception', (SELECT id FROM attribute WHERE short_name = 'PRE'), FALSE, FALSE, TRUE),
('Fortitude', (SELECT id FROM attribute WHERE short_name = 'VIG'), FALSE, FALSE, FALSE),
('Stealth', (SELECT id FROM attribute WHERE short_name = 'AGI'), FALSE, TRUE, FALSE),
('Initiative', (SELECT id FROM attribute WHERE short_name = 'AGI'), FALSE, FALSE, FALSE),
('Intimidation', (SELECT id FROM attribute WHERE short_name = 'PRE'), FALSE, FALSE, FALSE),
('Intuition', (SELECT id FROM attribute WHERE short_name = 'PRE'), FALSE, FALSE, FALSE),
('Investigation', (SELECT id FROM attribute WHERE short_name = 'INT'), FALSE, FALSE, FALSE),
('Fight', (SELECT id FROM attribute WHERE short_name = 'STR'), FALSE, FALSE, FALSE),
('Medicine', (SELECT id FROM attribute WHERE short_name = 'INT'), FALSE, FALSE, TRUE),
('Occultism', (SELECT id FROM attribute WHERE short_name = 'INT'), TRUE, FALSE, FALSE),
('Perception', (SELECT id FROM attribute WHERE short_name = 'PRE'), FALSE, FALSE, FALSE),
('Piloting', (SELECT id FROM attribute WHERE short_name = 'AGI'), TRUE, FALSE, FALSE),
('Aim', (SELECT id FROM attribute WHERE short_name = 'AGI'), FALSE, FALSE, FALSE),
('Profession', (SELECT id FROM attribute WHERE short_name = 'INT'), FALSE, FALSE, FALSE),
('Reflexes', (SELECT id FROM attribute WHERE short_name = 'AGI'), FALSE, FALSE, FALSE),
('Religion', (SELECT id FROM attribute WHERE short_name = 'PRE'), TRUE, FALSE, FALSE),
('Survival', (SELECT id FROM attribute WHERE short_name = 'PRE'), FALSE, FALSE, FALSE),
('Tactics', (SELECT id FROM attribute WHERE short_name = 'INT'), TRUE, FALSE, FALSE),
('Technology', (SELECT id FROM attribute WHERE short_name = 'INT'), TRUE, FALSE, TRUE),
('Will', (SELECT id FROM attribute WHERE short_name = 'PRE'), FALSE, FALSE, FALSE);

-- Insert Base Status
INSERT INTO base_status (hp_base, hp_level, ep_base, ep_level, san_base, san_level) VALUES
(20, 4, 2, 2, 12, 3),  -- Combatant
(16, 3, 3, 3, 16, 4),  -- Specialist
(12, 2, 4, 4, 20, 5);  -- Occultist

-- Insert Roles (using subqueries to get base_status UUIDs)
INSERT INTO role (name, proficiencies, base_expertises, base_status_id) VALUES
('Combatant', 'Simple weapons, tactical weapons, lightweight protection', 3, 
    (SELECT id FROM base_status WHERE hp_base = 20 AND hp_level = 4)),
('Specialist', 'Simple weapons, lightweight protection', 5, 
    (SELECT id FROM base_status WHERE hp_base = 16 AND hp_level = 3)),
('Occultist', 'Simple weapons', 4, 
    (SELECT id FROM base_status WHERE hp_base = 12 AND hp_level = 2));

-- Insert Role-Expertise relationships (using subqueries)
INSERT INTO role_expertise (role_id, expertise_id) VALUES
((SELECT id FROM role WHERE name = 'Combatant'), (SELECT id FROM expertise WHERE name = 'Fight')),
((SELECT id FROM role WHERE name = 'Combatant'), (SELECT id FROM expertise WHERE name = 'Aim')),
((SELECT id FROM role WHERE name = 'Specialist'), (SELECT id FROM expertise WHERE name = 'Investigation')),
((SELECT id FROM role WHERE name = 'Specialist'), (SELECT id FROM expertise WHERE name = 'Technology')),
((SELECT id FROM role WHERE name = 'Occultist'), (SELECT id FROM expertise WHERE name = 'Will')),
((SELECT id FROM role WHERE name = 'Occultist'), (SELECT id FROM expertise WHERE name = 'Occultism'));

-- Insert Origins
INSERT INTO origin (name, description, power_name, power_description) VALUES
('T.I.', 'Programador, engenheiro de software ou simplesmente "o cara da T.I.", você tem treinamento e experiência para lidar com sistemas informatizados.', 'Motor de Busca', 'A critério do Mestre, sempre que tiver acesso a internet, você pode gastar 2 PE para substituir um teste de perícia qualquer por um teste de Tecnologia.'),
('Fighter', 'Você pratica uma arte marcial ou esporte de luta, ou cresceu em um bairro perigoso onde aprendeu briga de rua.', 'Mão Pesada', 'Você recebe +2 em rolagens de dano com ataques corpo a corpo.'),
('Military', 'Você serviu em uma força militar, como o exército ou a marinha.', 'Para Bellum', 'Você recebe +2 em rolagens de dano com armas de fogo.');

-- Insert Origin-Expertise relationships (using subqueries)
INSERT INTO origin_expertise (origin_id, expertise_id) VALUES
((SELECT id FROM origin WHERE name = 'T.I.'), (SELECT id FROM expertise WHERE name = 'Investigation')),
((SELECT id FROM origin WHERE name = 'T.I.'), (SELECT id FROM expertise WHERE name = 'Technology')),
((SELECT id FROM origin WHERE name = 'Fighter'), (SELECT id FROM expertise WHERE name = 'Fight')),
((SELECT id FROM origin WHERE name = 'Fighter'), (SELECT id FROM expertise WHERE name = 'Reflexes')),
((SELECT id FROM origin WHERE name = 'Military'), (SELECT id FROM expertise WHERE name = 'Aim')),
((SELECT id FROM origin WHERE name = 'Military'), (SELECT id FROM expertise WHERE name = 'Tactics'));
