CREATE TABLE IF NOT EXISTS cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,

    run                 INT NOT NULL,
    dv                  CHAR(1) NOT NULL,
    nombre              VARCHAR(60) NOT NULL,
    ap_materno          VARCHAR(30) NOT NULL,
    ap_paterno          VARCHAR(30) NOT NULL,
    tel                 VARCHAR(16) NOT NULL,
    comuna              VARCHAR(30) NOT NULL,
    domicilio           VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS cuenta (
    id INT AUTO_INCREMENT PRIMARY KEY,

    cliente_id INT NOT NULL,
    numero INT NOT NULL,
    saldo INT NOT NULL,
    descripcion VARCHAR(40) NOT NULL,

    CONSTRAINT fk_cuenta_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);
