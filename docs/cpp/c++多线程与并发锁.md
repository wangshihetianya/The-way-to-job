## 学习使用Linux下的多线程编程

程序清单1（thread_race.cpp）

```c++
#include <iostream>
#include <pthread.h> /* pthread_self */
#include <string>
#include <sys/time.h>
#include <thread>
#include <unistd.h> /* getpid */

using std::cout;
using std::endl;
using std::string;
using std::thread;
namespace this_thread = std::this_thread;

void doit()
{
    // 进程，线程，协程
    cout << "当前进程ID：" << getpid() << ",";
    cout << gettid() << ",";
    cout << pthread_self() << "\n";
    cout << "当前线程ID：" << this_thread::get_id() << endl;
}

int main()
{
    doit();
    // 与Java不同，不需要显式指定start
    thread t1{doit};
    // 注意不添加join的话，会出现错误
    // 不要使用detatch
    t1.join();
    return 0;
}
```

Makefile

```makefile
all:
	g++ -Wall --std=c++11 thread_race.cpp -o thread_race -lpthread
```

输出结果：

```bash
luod@localhost cpp-thread % ./thread_race                                                                          [0]
当前进程ID：85964,85964,139774305982272
当前线程ID：139774305982272
当前进程ID：85964,85965,139774288037440
当前线程ID：139774288037440
luod@localhost cpp-thread %  
```

可以看到，`this_thread::get_id()`与`pthread_self()`的返回值是相同的，这个两个是对Linux内核线程进行了封装，均是符合POSIX标准的线程库。



插曲，glibc与gcc版本问题，添加动态链接库的路径。

```bash
# 查找原本的库
luod@localhost cpp-thread % strings /usr/lib64/libstdc++.so.6|grep GLIBCXX 
# 查找编译后新版gcc的库
luod@localhost cpp-thread % strings /usr/local/gcc/lib64/libstdc++.so.6|grep GLIBCXX  
# 找到后进行复制
luod@localhost cpp-thread % sudo cp /usr/local/gcc/lib64/libstdc++.so.6.0.28 /usr/lib64/
luod@localhost cpp-thread % cd /usr/lib64/  
luod@localhost cpp-thread % sudo mv  libstdc++.so.6 libstdc++.so.6.bak
# 创建动态链接
luod@localhost cpp-thread % sudo ln -s libstdc++.so.6.0.28 libstdc++.so.6
```

### 创建多线程进行测试

```c++
#include <chrono> /* seconds */
#include <iostream>
#include <pthread.h> /* pthread_self */
#include <string>
#include <sys/time.h>
#include <thread>
#include <unistd.h> /* getpid */
#include <vector>

using std::cout;
using std::endl;
using std::string;
using std::thread;
using std::vector;
using namespace std::chrono_literals;
namespace this_thread = std::this_thread;

void doit(int a, int b)
{
    // 进程，线程，协程
    // 2s, 2ms, 2us等均可以，在chrono_literals中有定义
    this_thread::sleep_for(2s);
    cout << "当前进程ID：" << getpid() << ",";
    cout << gettid() << ",";
    cout << pthread_self() << "\n";
    cout << "当前线程ID：" << this_thread::get_id() << endl;
    cout << "a = " << a << " b = " << b << endl;
}

int main()
{
    const unsigned thread_cnt = thread::hardware_concurrency();
    cout << "hardware_concurrency = " << thread_cnt << endl;
    // 创建一个比硬件核数更多的线程
    const unsigned max_thread_cnt = thread_cnt * 2 + 1;

    thread t1{doit, 3, 4};
    thread t2{doit, 3, 4};
    thread t3{doit, 3, 4};
    thread t4{doit, 3, 4};
    t1.join();
    t2.join();
    t3.join();
    t4.join();
    return 0;
}
```

此时是线程不安全的，可以添加上`std::mutex`进行保护。

一个简单的测试计数的例子：

```bash
#include <chrono> /* seconds */
#include <iostream>
#include <mutex>
#include <pthread.h> /* pthread_self */
#include <string>
#include <sys/time.h>
#include <thread>
#include <unistd.h> /* getpid */
#include <vector>

using std::cout;
using std::endl;
using std::string;
using std::thread;
using std::vector;
using namespace std::chrono_literals;
namespace this_thread = std::this_thread;

std::mutex count_lock;

static long counter = 0;

void doit()
{
	// 添加一个sleep，异步效果更加明显
	this_thread::sleep_for(20ms);
    for (int i = 0; i < 1000; ++i)
    {
        ++counter;
    }
}

int main()
{
    const unsigned thread_cnt = thread::hardware_concurrency();
    // 创建一个比硬件核数更多的线程
    const unsigned max_thread_cnt = thread_cnt * 2 + 1;

    vector<thread> workers;
    for (int i = 0; i < max_thread_cnt; ++i)
    {
        thread t1{doit};
        workers.push_back(std::move(t1));
    }
    for (auto &t : workers)
    {
        t.join();
    }
    cout << counter << endl;
    return 0;
}
```

输出

```bash
luod@localhost cpp-thread % ./thread_race                                                                          [0]
4232
luod@localhost cpp-thread % ./thread_race                                                                          [0]
7380
luod@localhost cpp-thread % ./thread_race                                                                          [0]
7676
luod@localhost cpp-thread % ./thread_race                                                                          [0]
8596
```

### 高精度停表的实现

```c++
// stopwatch.hpp
#ifndef _STOPWATCH_H
#define _STOPWATCH_H

#include <time.h> // clock_gettime

class StopWatch
{
private:
    struct timespec start_ts;
    bool is_started;

public:
    void start() noexcept;
    long get_ns() const noexcept;
    double get_ms() const noexcept;
};
#endif
```

```c++
// stopwatch.cpp
#include "stopwatch.hpp"

void StopWatch::start() noexcept
{
    if (!this->is_started)
    {
        clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &this->start_ts);
        is_started = true;
    }
}

long StopWatch::get_ns() const noexcept
{
    struct timespec end_ts;
    clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &end_ts);
    long s = end_ts.tv_sec - this->start_ts.tv_sec;
    long ns = 1000000000L * s + end_ts.tv_nsec - this->start_ts.tv_nsec;
    return ns;
}

double StopWatch::get_ms() const noexcept
{
    return this->get_ns() / 1000000.0;
}
```

测试：

```c++
int main()
{
    const unsigned thread_cnt = thread::hardware_concurrency();
    // 创建一个比硬件核数更多的线程
    const unsigned max_thread_cnt = thread_cnt * 2 + 1;
    StopWatch stw;
    stw.start();
    vector<thread> workers;
    for (int i = 0; i < max_thread_cnt; ++i)
    {
        thread t1{doit, 3, 4};
        workers.push_back(std::move(t1));
    }
    for (auto &t : workers)
    {
        t.join();
    }
    cout << counter << endl;
    cout << stw.get_ms() << "ms" << endl;
    return 0;
}
```

