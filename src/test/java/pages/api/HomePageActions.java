package pages.api;

import java.time.LocalDate;

public interface HomePageActions {
    boolean isDisplayed();
    void tapSearchFlights();
    void setLeavingFrom();
    void setGoingTo();
    void setDates(LocalDate depart, LocalDate ret);
    void setNextFridayToNextWeekFriday();
}