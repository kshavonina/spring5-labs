package lab.dao;

import lab.model.Person;

import java.sql.SQLException;

public interface PersonDao {
    Person getPerson(Integer id) throws SQLException;
}
