package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "home_history_log")
public class HomeHistoryLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @JsonBackReference(value = "home-histories")
  @ManyToOne
  private Home home;
  @JsonManagedReference(value = "home-image-histories")
  @OneToMany(mappedBy = "homeHistoryLog", cascade = CascadeType.MERGE)
  private List<HomeImageHistory> images = new ArrayList<>();
  @Column(name = "price_per_night")
  private int pricePerNight;

  @Column(name = "start_date")
  private Timestamp startDate;

  @Column(name = "end_date")
  private Timestamp endDate;

  @Column(name = "created_date")
  private Timestamp createdDate;

  @JsonManagedReference(value = "home-history-amenities")
  @OneToMany(mappedBy = "homeHistoryLog", cascade = CascadeType.MERGE)
  private List<AmenityHistory> amenities;

  public HomeHistoryLog(int id, Home home, int pricePerNight, Timestamp startDate,
      Timestamp endDate, List<HomeImageHistory> images) {
    this.id = id;
    this.home = home;
    this.pricePerNight = pricePerNight;
    this.startDate = startDate;
    this.endDate = endDate;
    this.images = images;
  }

  public HomeHistoryLog(Home home, int pricePerNight,
      Timestamp startDate, Timestamp endDate, Timestamp createdDate) {
    this.home = home;
    this.pricePerNight = pricePerNight;
    this.startDate = startDate;
    this.endDate = endDate;
    this.createdDate = createdDate;
  }

  public HomeHistoryLog() {
  }

  @Override
  public String toString() {
    return "HomeHistoryLog{" + "id=" + id + ", home=" + home + ", images=" + images
        + ", pricePerNight=" + pricePerNight + ", startDate=" + startDate + ", endDate=" + endDate
        + ", createdDate=" + createdDate + ", amenities=" + amenities + '}';
  }

  public int getId() {
    return id;
  }

  public Home getHome() {
    return home;
  }

  public void setHome(Home home) {
    this.home = home;
  }

  public int getPricePerNight() {
    return pricePerNight;
  }

  public void setPricePerNight(int pricePerNight) {
    this.pricePerNight = pricePerNight;
  }

  public Timestamp getStartDate() {
    return startDate;
  }

  public void setStartDate(Timestamp startDate) {
    this.startDate = startDate;
  }

  public Timestamp getEndDate() {
    return endDate;
  }

  public void setEndDate(Timestamp endDate) {
    this.endDate = endDate;
  }

  public Timestamp getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Timestamp createdDate) {
    this.createdDate = createdDate;
  }

  public List<HomeImageHistory> getImages() {
    return images;
  }

  public void setImages(List<HomeImageHistory> images) {
    this.images = images;
  }

  public List<AmenityHistory> getAmenities() {
    return amenities;
  }

  public void setAmenities(List<AmenityHistory> amenities) {
    this.amenities = amenities;
  }

}
