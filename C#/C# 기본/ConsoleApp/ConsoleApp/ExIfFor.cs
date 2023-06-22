namespace ConsoleApp;

public class ExIfFor
{
    public static void PrintIf()
    {

        var a = -11;
        int val;

        if (a >= 0)
        {
            val = a;
        }
        else
        {
            val = -a;
        }

        // 11
        Console.WriteLine(val);
    }

    public static void PrintSwitch()
    {
        var category = "사과";

        var price = category switch
        {
            "사과" => 1000,
            "딸기" => 1100,
            "포도" => 900,
            _ => 0
        };

        Console.WriteLine(price);
    }

    public static void PrintFor()
    {
        for (int i = 0; i < 10; i++)
        {
            Console.WriteLine(i);
        }
    }

    public static void PrintForEach()
    {
        string[] array = new string[] { "AB", "CD", "EF" };

        foreach (var s in array)
        {
            Console.WriteLine(s);
        }
    }
}