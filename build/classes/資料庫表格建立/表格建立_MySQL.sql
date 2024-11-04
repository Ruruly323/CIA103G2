CREATE DATABASE IF NOT EXISTS dobuy;
USE dobuy;

CREATE TABLE Auth (
    managerNo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    managerName VARCHAR(20) NOT NULL,
    managerAccount VARCHAR(20) NOT NULL,
    authNo INT NOT NULL,      -- 權限編號
    authTitle VARCHAR(255) NOT NULL,                     -- 權限名稱
    authContext VARCHAR(255) NOT NULL,                      -- 權限內容   
);

INSERT INTO Auth (managerNo, managerName, managerAccount, authNo, authTitle, authContext) VALUES
(1, 'John Doe', 'johndoe', 101, 'Admin', 'Full Access'),
(2, 'Jane Smith', 'janesmith', 102, 'Editor', 'Edit Content'),
(3, 'Jim Brown', 'jimbrown', 103, 'Viewer', 'View Content'),
(4, 'Nancy White', 'nancywhite', 104, 'Contributor', 'Contribute Content'),
(5, 'Larry Black', 'larryblack', 105, 'Guest', 'Limited Access');

