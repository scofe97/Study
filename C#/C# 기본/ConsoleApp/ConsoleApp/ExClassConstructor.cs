namespace ConsoleApp;


class Cat
{
    private string name;

    public Cat()
    {
        Console.WriteLine("생성자가 호출되었다.");
    }

    public Cat(string name)
    {
        this.name = name;
        Console.WriteLine("고양이의 이름 {0}", this.name );
    }

    ~Cat()
    {
        Console.WriteLine("소멸자 호출되었다.");
    }
}

public class ExClassConstructor
{
    public static void Print()
    {
        var cat1 = new Cat();
        var cat2 = new Cat("슈퍼");
    }
}