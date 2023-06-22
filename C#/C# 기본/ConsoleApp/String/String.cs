namespace String;

public class String
{
    static void print()
    {
        string s = "C# Studies";
        
        for (var i = 0; i >= 0; i--)
        {
            Console.WriteLine("{0} : {1}", i, s[i]);
        }
        
        // 문자열을 문자배열로 변환
        string str = "Hello";
        char[] charArray = str.ToCharArray();

        for (int i = 0; i < charArray.Length; i++)
        {
            Console.WriteLine(charArray[i]);
        }
        
        // 문자배열을 문자열로 변환
        char[] charArray2 = { 'A', 'B', 'C', 'D' };
        
        // 문자 연산
        char c1 = 'A';
        char c2 = (char)(c1 + 3);
        Console.WriteLine(c2); // D
    }
}