INSERT INTO user (empid, accesskey, country) VALUES
  ('Miller', 'TRMLLR', 'SG');
INSERT INTO user (empid, accesskey, country) VALUES
  ('Tessa', 'TRTSSA', 'SG');
INSERT INTO user (empid, accesskey, country) VALUES
  ('Dosson', 'TRDSSN', 'HK');
INSERT INTO user (empid, accesskey, country) VALUES
  ('Ricky', 'TRRCKY', 'SG');
INSERT INTO user (empid, accesskey, country) VALUES
  ('Aaron', 'TRARON', 'SG');
INSERT INTO user (empid, accesskey, country) VALUES
  ('Bob', 'TRBOB', 'HK');

  
INSERT INTO teamheirarchy (empid, managerid) VALUES
  ('Tessa', 'Miller');
INSERT INTO teamheirarchy (empid, managerid) VALUES
  ('Dosson', 'Miller');
INSERT INTO teamheirarchy (empid, managerid) VALUES
  ('Ricky', 'Tessa');
INSERT INTO teamheirarchy (empid, managerid) VALUES
  ('Aaron', 'Tessa');
INSERT INTO teamheirarchy (empid, managerid) VALUES
  ('Bob', 'Dosson');
