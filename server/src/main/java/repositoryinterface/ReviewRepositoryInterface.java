package repositoryinterface;

import java.util.List;
import java.util.Optional;
import model.Review;

public interface ReviewRepositoryInterface {

  public Optional<Review> findById(int id);

  public List<Review> findAll(String id);

  public Optional<Review> save(Review review);

  public Optional<Integer> update(int review);

}
