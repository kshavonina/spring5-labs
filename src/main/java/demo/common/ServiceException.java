package demo.common;

import java.sql.SQLException;

public class ServiceException extends Exception {
    public ServiceException(SQLException e) {
        super(e);
    }
}
