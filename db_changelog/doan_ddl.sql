create table area
(
    id         bigint       not null
        primary key,
    code       varchar(10)  null,
    parentcode varchar(10)  null,
    name       varchar(100) null,
    status     int          null,
    DKKDID     int          null,
    Region     int          null
);

create table bill
(
    id               bigint auto_increment
        primary key,
    totalAmount      double       null,
    fee              double       null,
    fromDate         date         null,
    toDate           date         null,
    nameCli          varchar(100) null,
    addressClient    varchar(255) null,
    addressWarehouse varchar(255) null,
    typeShip         int          null,
    idPol            int          null,
    status           int          null,
    idCli            bigint       null,
    idWar            bigint       null
);

create table brand
(
    id      bigint auto_increment
        primary key,
    nameBra varchar(100) null,
    idCat   varchar(100) null
);

create table cart
(
    id       bigint auto_increment
        primary key,
    idPro    bigint       null,
    idBil    bigint       null,
    quantity int          null,
    price    double       null,
    amount   double       null,
    namePro  varchar(150) null
);

create table category
(
    id       bigint auto_increment
        primary key,
    nameCate varchar(100) null
);

create table client
(
    id       bigint auto_increment
        primary key,
    password varchar(50) null,
    fullName varchar(50) null,
    phone    varchar(20) null
);

create table comment
(
    id      bigint auto_increment
        primary key,
    nameCli varchar(255) null,
    date    date         null,
    comment varchar(512) null,
    rate    double       null,
    idPro   bigint       null
);

create table employee
(
    id       bigint auto_increment
        primary key,
    username varchar(100) null,
    password varchar(100) null,
    fullname varchar(100) null,
    role     varchar(100) null
);

create table inventory
(
    id       bigint auto_increment
        primary key,
    idPro    bigint       null,
    namePro  varchar(255) null,
    idWar    bigint       null,
    quantity int          null
);

create table policy
(
    id      int auto_increment
        primary key,
    fromDis int    null,
    toDis   int    null,
    amount  double null
);

create table product
(
    id          bigint auto_increment
        primary key,
    namePro     varchar(150) null,
    price       double       null,
    idCat       bigint       null,
    idBra       bigint       null,
    description text         null,
    screen      varchar(100) null,
    os          varchar(100) null,
    ram         varchar(100) null,
    battery     varchar(100) null,
    date        date         null,
    image       mediumblob   null,
    status      tinyint      null,
    rate        double       null
);

create table warehouse
(
    id      bigint auto_increment
        primary key,
    nameWar varchar(255) null,
    idWar   varchar(10)  null,
    idDis   varchar(10)  null,
    idPro   varchar(10)  null,
    address varchar(255) null,
    status  tinyint(1)   null,
    street  varchar(100) null
);

create table warehousereceipt
(
    id          bigint auto_increment
        primary key,
    idEmp       bigint       null,
    idWar       bigint       null,
    date        date         null,
    totalAmount double       null,
    type        int          null,
    code        varchar(100) null,
    idBil       bigint       null,
    fee         double       null
);

create table warehousereceiptdetail
(
    id       bigint auto_increment
        primary key,
    idWare   bigint       null,
    idPro    bigint       null,
    namePro  varchar(100) null,
    quantity int          null,
    price    double       null,
    amount   double       null
);


