package com.ivoronline.springboot_jdbc_transaction.repository;

import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class MyRepository {

  //=========================================================================================================
  // INSERT
  //=========================================================================================================
  public void save(Connection connection, String name, int age) throws SQLException {
    String    sql = " INSERT INTO PERSON (NAME, AGE) VALUES ('"+name+"', "+age+")";
    Statement statement = connection.createStatement();
              statement.executeUpdate(sql);
  }

}
