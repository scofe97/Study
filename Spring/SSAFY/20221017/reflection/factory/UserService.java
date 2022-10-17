package reflection.factory;

public class UserService {
    private UserDao dao;
    public UserService() {}

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public UserDao getDao() {
        return dao;
    }

    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    public void add(User user)
}
