-- Insert sample data into Household table
INSERT INTO household (eircode, number_of_occupants, max_number_of_occupants, owner_occupied) VALUES
('D02XY45', 3, 5, 1),
('A94B6F3', 4, 6, 0),
('T12AB34', 2, 4, 1),
('C15DE67', 5, 7, 1),
('F12GH89', 1, 2, 0),
('B78IJ01', 3, 5, 1),
('M34KL56', 4, 6, 0),
('P90QR78', 2, 4, 1),
('V23ST01', 5, 7, 1),
('X45UV67', 1, 2, 0);

-- Insert sample data into Pets table
INSERT INTO pets (name, animal_type, breed, age, household_eircode) VALUES
('Buddy', 'Dog', 'Golden Retriever', 3, 'D02XY45'),
('Mittens', 'Cat', 'Siamese', 2, 'D02XY45'),
('Charlie', 'Dog', 'Beagle', 4, 'A94B6F3'),
('Whiskers', 'Cat', 'Persian', 5, NULL), -- No household
('Coco', 'Rabbit', 'Holland Lop', 1, 'C15DE67'),
('Goldie', 'Fish', 'Goldfish', 1, NULL), -- No household
('Polly', 'Bird', 'Parakeet', 2, NULL), -- No household
('Max', 'Dog', 'German Shepherd', 5, 'A94B6F3'),
('Luna', 'Cat', 'Maine Coon', 3, NULL), -- No household
('Nibbles', 'Hamster', 'Syrian Hamster', 1, NULL); -- No household


INSERT INTO my_user (username, password, role, enabled, locked, first_name, last_name, county)
VALUES
    ('admin@example.com', '$2a$10$6cdgfUoM0bqmYzJRiTdcme9DFFV8frLghI2sz2zAfxcSprtF.KBH6', 'ADMIN', true, false, 'Admin', 'User', 'Kerry'),
    ('user@example.com', '$2a$10$6cdgfUoM0bqmYzJRiTdcme9DFFV8frLghI2sz2zAfxcSprtF.KBH6', 'USER', true, false, 'Regular', 'User', 'Cork');
