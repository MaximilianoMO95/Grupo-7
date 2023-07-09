CREATE TABLE IF NOT EXISTS cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,

    run                 INT NOT NULL,
    dv                  CHAR(1) NOT NULL,
    nombre              VARCHAR(60) NOT NULL,
    ap_materno          VARCHAR(30) NOT NULL,
    ap_paterno          VARCHAR(30) NOT NULL,
    tel                 VARCHAR(16) NOT NULL,
    comuna              VARCHAR(30) NOT NULL,
    domicilio           VARCHAR(60) NOT NULL,

    /* Cuenta */
    numero_cuenta       INT NOT NULL,
    saldo_cuenta        INT NOT NULL,
    tipo_cuenta         VARCHAR(40) NOT NULL
);
