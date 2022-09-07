package mao.t2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Project name(项目名称)：java并发编程_原子数组
 * Package(包名): mao.t2
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/7
 * Time(创建时间)： 15:21
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{
    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
        int total = 10000;
        List<Thread> threads = new ArrayList<>(total);
        for (int i = 0; i < total; i++)
        {
            Thread thread = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    for (int k = 0; k < 10; k++)
                    {
                        for (int j = 0; j < atomicIntegerArray.length(); j++)
                        {
                            atomicIntegerArray.incrementAndGet(j);
                        }
                    }
                }
            }, "t" + (i + 1));
            threads.add(thread);
        }

        threads.forEach(Thread::start);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                long end = System.currentTimeMillis();
                System.out.println("运行时间：" + (end - start) + "ms");
                System.out.println(atomicIntegerArray);
            }
        }));
    }
}
