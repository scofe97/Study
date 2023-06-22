namespace ConsoleApp;

public enum Category
{
    // 0, 1, 2의 값을 받는다.
    Cake,
    IceCream,
    Bread
}

public enum City
{
    Seoul,
    Daejun,
    Busan = 5,
    Jeju = 10
}

enum Border
{
    None = 0,
    Top = 1,
    Right = 2,
    Bottom = 4,
    Left = 8
}

public class ExEnumPrint
{
    public static void Print()
    {
        City myCity;

        myCity = City.Seoul;
        
        // enum을 int로 변환하는 방법
        int cityValue = (int)myCity;

        if (myCity == City.Seoul)
        {
            Console.WriteLine("Welcome to Seoul");
        }
    }
}

public class ExEnumPrint2
{
    public static void Print()
    {
        // OR 연산자로 1+4 값을 가진다.
        Border b = Border.Top | Border.Bottom;
        
        // AND 연산자로 플래그 체크
        if ((b & Border.Top) != 0)
        {
            // HashFlas() 아용 플래그 체크
            if (b.HasFlag(Border.Bottom))
            {
                Console.WriteLine(b.ToString());
            }
        }
    }
}