namespace ConsoleApp;

class Car
{
    private int _position;
    private int _destination;
 
    public Car(int destination)
    {
        _destination = destination;
    }
 
    public void Drive()
    {
        while (_position < _destination)
        {
            _position++;
            if (MoveEvent != null) MoveEvent(this, new MoveEventHandlerArgs(_position));
            Thread.Sleep(1000);
        }
    }
 
    public event EventHandler<MoveEventHandlerArgs> MoveEvent;
 
    public class MoveEventHandlerArgs
    {
        public int Position { get; set; }
 
        public MoveEventHandlerArgs(int position)
        {
            Position = position;
        }
    }
}

public class ExEvent
{
    public static void PrintEvent()
    {
        Car car = new Car(3);
        car.MoveEvent += Move;
        car.Drive();
    }
    
    private static void Move(object sender, Car.MoveEventHandlerArgs e)
    {
        Console.WriteLine(e.Position);
    }
}