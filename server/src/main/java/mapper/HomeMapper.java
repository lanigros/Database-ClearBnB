package mapper;


import datatransferobject.HomeAddressDTO;
import datatransferobject.HomeCoreDTO;
import datatransferobject.HomeCoreNoHostDTO;
import datatransferobject.HomeCoreWithBooking;
import datatransferobject.HomeHistoryDTO;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import model.Amenity;
import model.Home;
import model.HomeHistoryLog;
import model.HomeImage;
import model.Host;
import utility.AmenityEnumConverter;

public class HomeMapper {

  private static final AmenityEnumConverter amenityEnumConverter = new AmenityEnumConverter();

  public static HomeCoreDTO convertToCore(Home home) {
    System.out.println(home.getHost());
    HomeCoreDTO dto = new HomeCoreDTO();
    dto.setId(home.getId());
    dto.setAddress(home.getAddress());
    dto.setHost(HostMapper.convertToHostBasic(home.getHost()));
    dto.setPricePerNight(home.getPricePerNight());
    dto.setImages(home.getImages());
    dto.setStartDate(home.getStartDate());
    dto.setEndDate(home.getEndDate());
    dto.setCreatedDate(home.getCreatedDate());
    dto.setAmenities(home.getAmenities());
    return dto;
  }

  public static HomeHistoryDTO convertToCore(HomeHistoryLog historyLog) {
    HomeHistoryDTO dto = new HomeHistoryDTO();
    dto.setId(historyLog.getId());
    dto.setAddress(historyLog.getHome().getAddress());
    dto.setPricePerNight(historyLog.getPricePerNight());
    dto.setImages(historyLog.getImages());
    dto.setStartDate(historyLog.getStartDate());
    dto.setEndDate(historyLog.getEndDate());
    dto.setCreatedDate(historyLog.getCreatedDate());
    dto.setAmenities(historyLog.getAmenities());

    return dto;

  }

  public static Home convertToHome(HomeAddressDTO dto, Host host) {

    Home home = new Home();
    home.setPricePerNight(dto.getPricePerNight());
    home.setStartDate(dto.getStartDate());
    home.setEndDate(dto.getEndDate());
    home.setHost(host);
    List<Amenity> amenities = amenityEnumConverter.getAmenitiesAsAmenityList(dto.getAmenities(),
        home);
    home.setAmenities(amenities);
    List<HomeImage> homeImages = HomeImageMapper.convertToHomeImages(dto.getImages(), home);
    home.setImages(homeImages);
    home.setCreatedDate(new Timestamp(Instant.now().toEpochMilli()));
    return home;

  }

  public static List<HomeCoreNoHostDTO> convertToNoHost(List<Home> homes) {
    List<HomeCoreNoHostDTO> list = new ArrayList<>();
    homes.forEach(home -> {
      HomeCoreNoHostDTO dto = new HomeCoreNoHostDTO(
          home.getId(),
          home.getAddress(),
          home.getImages(),
          home.getPricePerNight(),
          home.getStartDate(),
          home.getEndDate(),
          home.getCreatedDate(),
          home.getAmenities()
      );
      list.add(dto);
    });
    return list;
  }

  public static List<HomeCoreWithBooking> convertToWithBooking(List<Home> homes) {
    List<HomeCoreWithBooking> list = new ArrayList<>();
    homes.forEach(home -> {
      HomeCoreWithBooking dto = new HomeCoreWithBooking(
          home.getId(),
          home.getAddress(),
          home.getImages(),
          home.getPricePerNight(),
          home.getStartDate(),
          home.getEndDate(),
          home.getCreatedDate(),
          home.getAmenities(),
          BookingDetailMapper.convertToBookingWithRenterDTO(home.getBookingDetails()));
      list.add(dto);
    });
    return list;
  }

  public static List<HomeHistoryLog> convertHistory(Home home) {
    List<HomeHistoryLog> historyLogs = home.getHistoryLogs();
    HomeHistoryLog homeHistory = new HomeHistoryLog(
        home,
        home.getPricePerNight(),
        home.getStartDate(),
        home.getEndDate(),
        new Timestamp(Instant.now().toEpochMilli())
    );
    homeHistory.setAmenities(AmenityMapper.convertToHistory(home.getAmenities(), homeHistory));
    homeHistory.setImages(HomeImageMapper.convertToHistory(home.getImages(), homeHistory));
    historyLogs.add(homeHistory);
    return historyLogs;

  }

}
