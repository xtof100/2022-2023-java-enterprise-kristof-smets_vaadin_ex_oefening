package be.ucll.jpa.entities.optimistic;

public interface SeatReservationService {

    SeatReservation getSeat(Long seatId);

    void reserveSeat(SeatReservation seatReservation);
}
