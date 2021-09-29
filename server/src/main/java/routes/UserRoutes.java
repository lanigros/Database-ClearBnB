package routes;

import datatransforobject.ReviewBasicDTO;
import datatransforobject.UserCompleteProfileDTO;
import datatransforobject.UserNameIdDTO;
import datatransforobject.UserProfileDTO;
import express.Express;
import java.util.List;
import java.util.Optional;
import model.Review;
import model.User;
import service.ActiveSessionService;
import service.UserService;

public class UserRoutes {

  private final Express app;
  private final UserService userService;

  public UserRoutes(Express app) {
    this.app = app;
    this.userService = new UserService();
    this.init();
  }

  void init() {
    app.get("rest/user/private", (req, res) -> {
      try {
        String sessionID = req.cookie("sessionID");
        System.out.println(sessionID);
        String userId = String.valueOf(ActiveSessionService.getActiveSessionUserId(sessionID));
        UserCompleteProfileDTO user = userService.getUserCompleteProfile(userId);
        res.json(user);
      } catch (Exception e) {
        res.json(500);
      }
    });
    app.get("rest/users/name", (req, res) -> {
      try {
        List<UserNameIdDTO> users = userService.getAllNames();
        res.json(users);
      } catch (Exception e) {
        res.status(500).json("internal error");
      }
    });
    app.get("rest/user/profile/:id", (req, res) -> {
      try {
        String id = req.params("id");
        UserProfileDTO user = userService.getUserProfile(id);
        res.json(user);
      } catch (Exception e) {
        res.status(500).json("internal error");
      }
    });
    app.get("rest/users", (req, res) -> {
      try {
        List<User> users = userService.getAllWithEverything();
        res.json(users);
      } catch (Exception e) {
        e.printStackTrace();
        res.status(500).json("internal error");
      }
    });
    app.post("rest/reviews/host/user/:id", (req, res) -> {
      try {
        String sessionID = req.cookie("sessionID");
        String userId = String.valueOf(ActiveSessionService.getActiveSessionUserId(sessionID));
        String hostUserId = req.params("id");
        ReviewBasicDTO dto = req.body(ReviewBasicDTO.class);
        Review review = userService.createHostReview(userId, dto, hostUserId);
        res.json(review);
      } catch (Exception e) {
        e.printStackTrace();
        res.status(500);
      }
    });
    app.post("rest/reviews/renter/:id", (req, res) -> {
      try {
        String sessionID = req.cookie("sessionID");
        String userId = String.valueOf(ActiveSessionService.getActiveSessionUserId(sessionID));
        String renterID = req.params("id");
        ReviewBasicDTO dto = req.body(ReviewBasicDTO.class);
        Review review = userService.createRenterReview(userId, dto, renterID);
        res.json(review);
      } catch (Exception e) {
        e.printStackTrace();
        res.status(500);
      }
    });
    app.delete("rest/reviews/renter/:id", (req, res) -> {
      String reviewID = req.params("id");
      Optional<Integer> review = userService.deleteRenterReview(reviewID);
      res.json(review);
    });
  }

}
