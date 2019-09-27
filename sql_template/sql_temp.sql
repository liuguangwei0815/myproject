show variables like 'innodb_large_prefix';  
show variables like 'innodb_file_format';
 
--修改最大索引长度限制
set global innodb_large_prefix=1; 
set global innodb_file_format=BARRACUDA;
-- 添加
set global innodb_file_format_max=BARRACUDA;

CREATE database if NOT EXISTS `oauth` default character set utf8 collate utf8_general_ci;
use `oauth`;

CREATE TABLE oauth_client_details (
    client_id VARCHAR(256) PRIMARY KEY,
    resource_ids VARCHAR(256),
    client_secret VARCHAR(256),
    scope VARCHAR(256),
    authorized_grant_types VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities VARCHAR(256),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove VARCHAR(256)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC default charset utf8mb4;
INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types, web_server_redirect_uri, autoapprove)
VALUES ('MemberSystem', '$2a$10$4rKJBT8ywtus2CYPl4x/u.vHoNYmNJOMJr5McQkWVo7lYbvN3muRS', 'user_info', 'authorization_code', 'http://localhost:8081/login', 'user_info');
INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types, web_server_redirect_uri, autoapprove)
VALUES ('CouponSystem', '$2a$10$4rKJBT8ywtus2CYPl4x/u.vHoNYmNJOMJr5McQkWVo7lYbvN3muRS', 'user_info', 'authorization_code', 'http://localhost:8082/login', 'user_info');