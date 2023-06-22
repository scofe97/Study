namespace ConsoleApp;

class Robot
{
    public void Move()
    {
        Console.WriteLine("로봇이 움직인다");
    }
}

class CleanRobot : Robot
{
    public void Clean()
    {
        Console.WriteLine("로봇이 청소를 한다.");
    }
}

class RecueRobot : Robot
{
    public void Move()
    {
        Console.WriteLine("구조 로봇이 움직이다.");
    }
}

public class ExClassInheritance
{
    public static void PrintClass()
    {
        var cleanRobot = new CleanRobot();
        cleanRobot.Move();
        cleanRobot.Clean();

        var recueRobot = new RecueRobot();
        recueRobot.Move();
    }
}