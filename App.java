import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        number a = new number(6);
        a.print();
        
        System.out.println("_____");
        a.write("file.txt");
        a.set_num(8);
        System.out.println("Смена значения после записи:");
        a.print();
        a.read("file.txt");
        System.out.println("Значение после чтения:");
        a.print();

        System.out.println("_____");
        a.write("file2.bin");
        a.set_num(8);
        System.out.println("Смена значения после записи:");
        a.print();
        a.read("file2.bin");
        System.out.println("Значение после чтения:");
        a.print();
    }
}

class number {
    private ArrayList<Integer> n = new ArrayList<Integer>();
    private boolean isprime(int a)
    {
        for (int i = 2; i < Math.sqrt(a); i++)
        {
            if (a % i == 0)
                return false;
        }
        return true;
    }
    private void filln(int a)
    {
        n.clear();
        for (int i = 2; i < a ; i++)
        {
            if (!isprime(i))
                continue;
            for (; a % i == 0; a /= i)
                n.add(i);
        }
        if (a > 1)
            n.add(a);
    }

    number(int a) throws Exception
    {
        set_num(a);
    }
    number(ArrayList<Integer> a) throws Exception
    {
        set_arr(a);
    }
    void print_arr()
    {
        for (int i = 0; i < n.size(); i++)
            System.out.print(n.get(i) + " ");
        System.out.println();
    }
    void print_num()
    {
        System.out.println(get_num());
    }
    void print()
    {
        print_num();
        print_arr();
    }

    int sum(int a) throws Exception
    {
        int n = get_num() + a;
        if (n > 0)
        {
            filln(n);
            return get_num();
        }
        else
            throw new Exception("number < 0");
    }
    int diff(int a) throws Exception
    {
        int n = get_num() - a;
        if (n > 0)
        {
            filln(n);
            return get_num();
        }
        else
            throw new Exception("number < 0");
    }
    int mul(int a) throws Exception
    {
        int n = get_num() * a;
        if (n > 0)
        {
            filln(n);
            return get_num();
        }
        else
            throw new Exception("number < 0");
    }
    int div(int a) throws Exception
    {
        int n = get_num() / a;
        if (n > 0)
        {
            filln(n);
            return get_num();
        }
        else
            throw new Exception("number < 0");
    }

    void set_ind_n(int i, int a) throws Exception
    {
        if ((i >= n.size()) || (i < 0))
            throw new Exception("Индекс выходит за границы массива");
        else if (a < 0)
            throw new Exception("number < 0");
        else if (!isprime(a))
            throw new Exception("Число не простое");
        else
            n.set(i, a);
    }
    void set_n(int a) throws Exception
    {
        if (a < 0)
            throw new Exception("number < 0");
        else if (!isprime(a))
            throw new Exception("Число не простое");
        else
            n.add(a);
    }
    void del_ind_n(int i) throws Exception
    {
        if ((i >= n.size()) || (i < 0))
            throw new Exception("Индекс выходит за границы массива");
        n.remove(i);
    }

    void set_num(int a) throws Exception
    {
        if (a > 0)
            filln(a);
        else
            throw new Exception("number < 0");
    }
    int get_num()
    {
        int a = 1;
        for (int i = 0; i < n.size(); i++)
            a *= n.get(i);
        return a;
    }

    void set_arr(ArrayList<Integer> a)
    {
        n.clear();
        n.addAll(a);
    }
    ArrayList<Integer> get_arr()
    {
        return n;
    }

    void write(String filename)
    {
        try
        {
            PrintWriter f = new PrintWriter(new FileWriter(filename));
            for (int i = 0; i < n.size(); i++)
                f.write(n.get(i) + " ");
            f.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    void read(String filename)
    {
        try
        {
            Scanner f = new Scanner(new FileReader(filename));
            while (f.hasNextLine())
            {
                n.clear();
                String str = f.nextLine();
                String[] nums = str.split(" ");
                for (String s : nums)
                    n.add(Integer.parseInt(s));
            }
            f.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    void write_bin(String filename)
    {
        try
        {
            ObjectOutputStream  f = new ObjectOutputStream (new FileOutputStream(filename));
            for (int i = 0; i < n.size(); i++)
                f.write(n.get(i));
            f.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    void read_bin(String filename)
    {
        try
        {
            ObjectInputStream f = new ObjectInputStream (new FileInputStream(filename));
            n.clear();
            while (true)
            {
                try
                {
                    int multiplier = f.readInt();
                    n.add(multiplier);
                }
                catch (EOFException e)
                {
                    break;
                }
            }
            f.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}