using System.Collections.Generic;
namespace WpfApp1.models;
using Exception;

public class Hotel
{
    private readonly ReservationBook _reservationBook;
    
    public string Name { get; }

    public Hotel(string name)
    {
        this.Name = name;
        _reservationBook = new ReservationBook();
        
    }

    /// <summary>
    /// 유저가 예약을 진행한다.
    /// </summary>
    /// <param name="username"></param>
    /// <returns></returns>
    public IEnumerable<Reservation> GetReservationsForUser(string username)
    {
        return _reservationBook.GetReservationsForUser(username);
    }

    /// <summary>
    /// 예약을 만든다.
    /// </summary>
    /// <param name="reservation"></param>
    /// <exception cref="ReservationConflictException"></exception>
    public void MakeReservation(Reservation reservation)
    {
        _reservationBook.AddReservation(reservation);
    }
    

}