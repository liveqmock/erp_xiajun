
#DROP PROCEDURE createUser;
DELIMITER //
CREATE PROCEDURE createUser(
  IN companyName VARCHAR(64),
  IN loginName   VARCHAR(64),
  OUT msg VARCHAR(64)
)
  BEGIN
    DECLARE chars_str VARCHAR(100) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    DECLARE companyno VARCHAR(64) DEFAULT '';
    DECLARE orgid BIGINT DEFAULT 0;
    DECLARE userno VARCHAR(64) DEFAULT '';
    DECLARE roleid BIGINT DEFAULT 0;
    DECLARE roleid_default BIGINT DEFAULT 0;
    DECLARE roleid_buyer BIGINT DEFAULT 0;
    DECLARE roleid_buyermgr BIGINT DEFAULT 0;
    DECLARE userId BIGINT DEFAULT 0;
    DECLARE i INT DEFAULT 0;
    /** 标记是否出错 */
    DECLARE t_error INT DEFAULT 0;
    /** 如果出现sql异常，则将t_error设置为1后继续执行后面的操作 */
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET t_error = 1; -- 出错处理
    /** 显式的开启事务，它开启后，事务会暂时停止自动提交*/
    -- start transaction;
    /** 关闭事务的自动提交 */
    SET autocommit = 0;
    WHILE i < 10 DO
      SET companyno = concat(companyno, substring(chars_str, FLOOR(1 + RAND() * 62), 1));
      SET userno = concat(userno, substring(chars_str, FLOOR(1 + RAND() * 62), 1));
      SET i = i + 1;
    END WHILE;
    #创建公司
    INSERT INTO company (company_no, company_name, status,
                         force_idcard, tel, im,
                         creator, modifier)
    VALUES
      (companyno, companyName, 0, 1, '13777828256', '微信',
       'SYSTEM', 'SYSTEM');

    #     创建部门 (待测试)
    SELECT max(org_id) + 1
    INTO orgid
    FROM auth_organization;
    INSERT INTO auth_organization (company_no, org_id, name, address,code,seq, creator, modifier)
    VALUES (companyno, orgid, '公司总部', '公司地址',orgid+100000000,1, 'SYSTEM', 'SYSTEM');


    #     创建角色
#     创建管理员
    SELECT max(role_id) + 1
    INTO roleid
    FROM auth_role;
    INSERT INTO auth_role (role_id, company_no, name, creator, modifier)
    VALUES (roleid, companyno, '公司管理员', 'SYSTEM', 'SYSTEM');
#     创建买手角色
    SELECT max(role_id) + 1
    INTO roleid_buyer
    FROM auth_role;
    INSERT INTO auth_role (role_id, company_no, name, creator, modifier)
    VALUES (roleid_buyer, companyno, '买手', 'SYSTEM', 'SYSTEM');
#     创建新成员角色
    SELECT max(role_id) + 1
    INTO roleid_default
    FROM auth_role;
    INSERT INTO auth_role (role_id, company_no, name, creator, modifier)
    VALUES (roleid_default, companyno, '新成员', 'SYSTEM', 'SYSTEM');
#     创建买手角色
    SELECT max(role_id) + 1
    INTO roleid_buyermgr
    FROM auth_role;
    INSERT INTO auth_role (role_id, company_no, name, creator, modifier)
    VALUES (roleid_buyermgr, companyno, '买手主管', 'SYSTEM', 'SYSTEM');

    #     创建用户
    INSERT INTO auth_user (company_no, user_no, login_name, name, password, creator, modifier)
    VALUES (companyno, userno, loginName, '公司管理员', 'e10adc3949ba59abbe56e057f20f883e', 'SYSTEM', 'SYSTEM');


    #用户角色的对应
    SELECT id
    INTO userId
    FROM auth_user
    WHERE login_name = loginName;
    INSERT INTO auth_user_role (user_id, role_id, creator, modifier, company_no)
    VALUES (userId, roleid, 'SYSTEM', 'SYSTEM', companyno);
    #创建相应的资源
    INSERT INTO auth_role_resource (company_no, role_id, resource_id, creator, modifier) VALUES
      (companyNo, roleid, 1, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 11, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 111, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 112, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 113, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 114, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 12, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 121, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 122, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 123, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 124, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 125, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 13, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 131, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 132, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 133, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 134, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 14, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 141, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 142, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 143, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 144, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 289, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 290, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 293, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 303, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 299, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 300, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 231, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 259, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 260, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 284, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 261, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 262, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 294, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 263, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 264, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 265, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 266, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 277, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 304, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 267, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 268, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 269, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 297, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 298, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 270, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 271, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 272, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 302, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 273, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 274, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 275, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 276, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 285, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 295, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 221, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 227, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 228, 'SYSTEM', 'SYSTEM'),
      (companyNo, roleid, 229, 'SYSTEM', 'SYSTEM');
    IF t_error = 1
    THEN
      ROLLBACK; -- 事务回滚
      set msg = 'erro';
    ELSE
      COMMIT; -- 事务提交
      set msg = 'success';
    END IF;
    SET autocommit = 1;
  END //
DELIMITER ;

#


SHOW PROCEDURE STATUS;
# CALL createUser('APEX公司', 'apex',@msg);SELECT @msg;






