INSERT INTO CATEGORY (CATEGORY_ID, NAME, DESCRIPTION, IMAGE_URL) VALUES
(1L,'Vlees','Vlees producten','https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png'),
(2L,'Fruit','Fruit producten','https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png'),
(3L,'Groenten','Groenten producten','https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png');

INSERT INTO DISH (DISH_ID, CATEGORY_ID, NAME, PRICE, DESCRIPTION, IMAGE_URL) VALUES
(1L,1L,'Biefstuk',7.00,'Een stuk biefstuk','https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png'),
(2L,1L,'Kip',5.00,'Een stuk kip','https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png'),
(3L,2L,'Bloemkool',3.00,'Een bord met bloemkool','https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png'),
(4L,2L,'Wortels',4.00,'Een bord met wortels','https://fontys.nl/upload/50716580-70d3-4c39-86dd-4237e0166f38_image6483968316988970112.png');

INSERT INTO DISH_INGREDIENT (ID, DISH_ID, INGREDIENT_ID, AMOUNT) VALUES
(1L, 1L, 1000L, 10),
(2L, 1L, 1001L, 5),
(3L, 2L, 1002L, 20),
(4L, 3L, 1003L, 17);