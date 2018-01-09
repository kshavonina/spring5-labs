package demo.aop;

import demo.common.AuthException;
import demo.common.SecurityContext;
import demo.common.ServiceException;
import lab.dao.PersonDao;
import lab.model.Person;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class UserService {
    @Autowired
    PersonDao personDao;
    @Autowired
    Map<Integer, Person> cache = new HashMap<>();
    public Person getPerson(Integer id) throws ServiceException, AuthException {
        if (!SecurityContext.getPerson().hasRight("GetUser"))
            throw new AuthException("Permission Denied");
        log.info("Call method getPerson with id {}", id);
        Person person;
        try {
            if (cache.containsKey(id))
                person = cache.get(id);
            else
                cache.put(id, person = personDao.getPerson(id));
        } catch(SQLException e) {
            throw new ServiceException(e);
        }
        log.info("User info is: {}", person);
        return person;
    }
}
