namespace ConsoleApp;

public class Yield
{
    public static IEnumerable<int> Ex()
    {
        yield return 10; // 1번째 루프에서 반환 
        yield return 20; // 2번째 루프에서 반환
        yield return 30; // 3번째 루프에서 반환
    }
    
    static 
}