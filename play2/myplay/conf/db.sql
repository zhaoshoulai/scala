select * from test.products;

# --- !Ups
CREATE TABLE Products (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);
# --- !Downs
# DROP TABLE Products;