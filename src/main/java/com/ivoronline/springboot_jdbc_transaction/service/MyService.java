package com.ivoronline.springboot_jdbc_transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class MyService {

  //PROPERTIES
  @Autowired private DataSource dataSource;

  //=========================================================================================================
  // INSERT
  //=========================================================================================================
  public void insert(String name, Integer age) throws SQLException {

    //GET DB CONNECTION
    Connection connection = dataSource.getConnection();

    //TRANSACTION
    try {          //It doesn't work with try(connection) => throws java.sql.SQLRecoverableException

      //START TRANSACTION (Without Transaction first statement getsr inserted)
      connection.setAutoCommit(false);

      //INSERT RECORDS
      String    sql = " INSERT INTO PERSON (NAME, AGE) VALUES ('"+name+"', "+age+");" +
                      " INSERT INTO PERSON (NAME, AGE2) VALUES ('Peter'   , 60     )";
      Statement statement = connection.createStatement();
                statement.executeUpdate(sql);

      //COMMIT TRANSACTION
      connection.commit();

    } catch (Exception e) {
        connection.rollback();   //throws java.sql.SQLRecoverableException
    }
    finally {
      connection.close();
    }

  }

}
