INSERT INTO Flight(id,travelOrderId,fromAirport,toAirport) VALUES (nextval('Flight_SEQ'),1,'GRU','MCO');
INSERT INTO Flight(id,travelOrderId,fromAirport,toAirport) VALUES (nextval('Flight_SEQ'),2,'GRU','JFK');
INSERT INTO Flight(id,travelOrderId,fromAirport,toAirport) VALUES (nextval('Flight_SEQ'),3,'GRU','ATL');
INSERT INTO Flight(id,travelOrderId,fromAirport,toAirport) VALUES (nextval('Flight_SEQ'),4,'GRU','MEX');
ALTER SEQUENCE Flight_SEQ RESTART WITH 10;