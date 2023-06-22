using System.Collections;
namespace ConsoleApp;

public class MyList
{
    private readonly int[] _data = { 1, 2, 3, 4, 5 };
    
    public IEnumerator GetEnumerator()
    {
        int i = 0;
        while (i < _data.Length)
        {
            yield return _data[i];
            i++;                
        }
    }

}

public class ExYield
{
    private static IEnumerable<int> YieldTest()
    {
        yield return 10; // 1번째 루프에서 반환 
        yield return 20; // 2번째 루프에서 반환
        yield return 30; // 3번째 루프에서 반환
    }

    public static void YieldPrint()
    {
        var list = new MyList();

        // 1. foreach 사용
        foreach (var num in list)
        {
            Console.WriteLine(num);
        }
        
        // 2. 수동 Iteration
        IEnumerator it = list.GetEnumerator();
        it.MoveNext();
        Console.WriteLine(it.Current);
        it.MoveNext();
        Console.WriteLine(it.Current);
    }
}