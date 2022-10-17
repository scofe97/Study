package reflection.factory;

public interface UserDao {

    void insert(User user);

    User select(int id);
}
