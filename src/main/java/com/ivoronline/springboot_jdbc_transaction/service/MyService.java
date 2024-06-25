package com.ivoronline.springboot_jdbc_transaction.service;

import com.ivoronline.springboot_jdbc_transaction.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class MyService {

  //PROPERTIES
  @Autowired private DataSource   dataSource;
  @Autowired private MyRepository repository;

  //=========================================================================================================
  // INSERT RECORDS
  //=========================================================================================================
  public void insertRecords(String name, Integer age) throws SQLException {

    //GET DB CONNECTION
    Connection connection = dataSource.getConnection();

    //TRANSACTION
    try {          //It doesn't work with try(connection) => throws java.sql.SQLRecoverableException

      //START TRANSACTION (Without Transaction first statement getsr inserted)
      connection.setAutoCommit(false);

      //EXECUTE SQL STATEMENTS (Inserts both Records or none)
      for (int i = 1; i <= 2; i++) {
          //if(i==2) { throw new Exception("Exception"); }
          repository.save(connection, "Person " + i, 10 * i);
      }

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
