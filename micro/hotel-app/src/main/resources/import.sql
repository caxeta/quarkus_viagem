INSERT INTO Hotel(id,travelorderid,nights) VALUES (nextval('Hotel_SEQ'),1,5);
INSERT INTO Hotel(id,travelorderid,nights) VALUES (nextval('Hotel_SEQ'),2,2);
INSERT INTO Hotel(id,travelorderid,nights) VALUES (nextval('Hotel_SEQ'),3,3);
INSERT INTO Hotel(id,travelorderid,nights) VALUES (nextval('Hotel_SEQ'),4,10);
INSERT INTO Hotel(id,travelorderid,nights) VALUES (nextval('Hotel_SEQ'),5,30);
ALTER SEQUENCE Hotel_SEQ RESTART WITH 10;