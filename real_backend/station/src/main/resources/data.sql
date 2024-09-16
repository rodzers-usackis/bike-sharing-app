INSERT INTO station_addresses  (uuid, city, street_name, street_number)
VALUES ('d2582265-1572-4715-848a-0e8cb4abf4ee', 'San Francisco', 'Street 1', 10);

INSERT INTO station_locations (uuid, latitude, longitude)
VALUES ('5280ab31-df38-4137-af08-9244a76b9d6f', 37.329732, -121.90178200000001);

INSERT INTO station_stations (uuid, name, address, location)
VALUES ('982535ea-e447-47a9-a520-7f6fc56d711d', 'San Jose Diridon Caltrain Station','d2582265-1572-4715-848a-0e8cb4abf4ee', '5280ab31-df38-4137-af08-9244a76b9d6f');

INSERT INTO station_docks (uuid, dock_state, dock_number, station, vehicle)
VALUES ('68bd8d62-5176-46b5-bcc5-ced98158d554', 'IN_USE', 1,'982535ea-e447-47a9-a520-7f6fc56d711d', '36eddf39-a93e-46ac-b79b-c329f107abfe');

INSERT INTO station_docks (uuid, dock_state, dock_number, station, vehicle)
VALUES ('4b2e6b21-635f-421e-9101-ab61a0787ea5', 'IN_USE', 2, '982535ea-e447-47a9-a520-7f6fc56d711d', '8327bbb1-430d-4e00-a2c2-ab21a03f31f0');

INSERT INTO station_docks (uuid, dock_state, dock_number, station, vehicle)
VALUES ('b53337e1-611f-4104-8b8a-b19009f6116e', 'IN_USE', 3, '982535ea-e447-47a9-a520-7f6fc56d711d', '891d7706-7178-429f-b171-475b8d06498e');



INSERT INTO station_addresses  (uuid, city, street_name, street_number)
VALUES ('3916ed6c-f191-4605-aab1-b261c409b667', 'San Francisco', 'Street 1', 15);

INSERT INTO station_locations (uuid, latitude, longitude)
VALUES ('eb3abc34-e3f9-4f96-bace-425fceb94a7e', 37.330165, -121.88583100000001);

INSERT INTO station_stations (uuid, name, address, location)
VALUES ('47910a7b-4457-498e-9d9d-e5d43b74f2c6', 'San Salvador at 1st', '3916ed6c-f191-4605-aab1-b261c409b667', 'eb3abc34-e3f9-4f96-bace-425fceb94a7e');

INSERT INTO station_docks (uuid, dock_state, dock_number, station, vehicle)
VALUES ('f8c6eda0-7541-41d3-8c2d-751f98fa6575', 'AVAILABLE', 1, '47910a7b-4457-498e-9d9d-e5d43b74f2c6', null);

INSERT INTO station_docks (uuid, dock_state, dock_number, station, vehicle)
VALUES ('fa0abb7d-7bee-4b56-a559-89e57ae19f66', 'AVAILABLE', 2, '47910a7b-4457-498e-9d9d-e5d43b74f2c6', null);

INSERT INTO station_docks (uuid, dock_state, dock_number, station, vehicle)
VALUES ('9509a2d0-3ee2-4bb6-b290-5225983a5d51', 'AVAILABLE', 3, '47910a7b-4457-498e-9d9d-e5d43b74f2c6', null);



INSERT INTO station_addresses  (uuid, city, street_name, street_number)
VALUES ('91c65aa2-34c4-4891-b79a-91799897dfa3', 'San Francisco', 'Street 1', 8);

INSERT INTO station_locations (uuid, latitude, longitude)
VALUES ('111696a1-5928-4a6c-9e5a-1d3db337c49d', 37.481758, -122.226904);

INSERT INTO station_stations (uuid, name, address, location)
VALUES ('b4e22f1d-13c6-44f7-9e05-0a880128f412', 'Franklin at Maple', '91c65aa2-34c4-4891-b79a-91799897dfa3', '111696a1-5928-4a6c-9e5a-1d3db337c49d');

INSERT INTO station_docks (uuid, dock_state, dock_number, station, vehicle)
VALUES ('d97254d5-6fc3-4250-8603-49b4eec5ec63', 'IN_USE', 1, 'b4e22f1d-13c6-44f7-9e05-0a880128f412', 'ae4dcc44-1f87-424d-a057-88b1d840cfb1');

INSERT INTO station_docks (uuid, dock_state, dock_number, station, vehicle)
VALUES ('6987275e-16ea-44d2-8f3f-1ad64a80a53a', 'IN_USE', 2, 'b4e22f1d-13c6-44f7-9e05-0a880128f412', 'd511b12c-3d90-42ad-9458-6bdbde62804c');

INSERT INTO station_docks (uuid, dock_state, dock_number, station, vehicle)
VALUES ('b2eece03-6cdd-4faa-8f50-cb4171bf5de5', 'AVAILABLE', 3,'b4e22f1d-13c6-44f7-9e05-0a880128f412', null);
