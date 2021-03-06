package repositoryinterface;

import java.util.List;
import java.util.Optional;
import model.User;

public interface UserRepositoryInterface {

  public Optional<User> findById(String id);

  public List<User> findAll();

  public Optional<User> save(User user);


}
