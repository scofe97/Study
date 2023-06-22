using System.Collections.Generic;
using System.Linq;
using WpfApp1.Exception;

namespace WpfApp1.models;

public class ReservationBook
{
    private readonly List<Reservation> _reservations;

    public ReservationBook()
    {
        _reservations = new List<Reservation>();
    }

    public IEnumerable<Reservation> GetReservationsForUser(string username)
    {
        return _reservations.Where(r => r.Username == username);
    }

    public void AddReservation(Reservation reservation)
    {
        foreach (var existingReservation in _reservations)
        {
            if (existingReservation.Conflicts(reservation))
            {
                throw new ReservationConflictException(existingReservation, reservation);
            }
        }
        
        _reservations.Add(reservation);
    }
}