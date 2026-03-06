package pages.api;

public interface SelectFlightPageActions {
    boolean waitUntilDisplayed();
    int getVisibleFlightCount();
    String firstDepartureTime();
    boolean isDepartureTimeVisible(String time);
    boolean infiniteScrollLoadsMoreOrStops(int maxSwipes);
    void selectFirstFlight();
    boolean matchesDeepLink(String deepLinkUrl);
}