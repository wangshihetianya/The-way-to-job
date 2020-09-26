# typedef的基本用法



### 用途一：定义一种类型的别名

定义一种类型的别名，而不只是简单的宏替换。可以用作同时声明指针型的多个对象。

比如：

```c
char* pa, pb;
```

这多数不符合我们的意图，它只声明了一个指向字符变量的指针， 和一个字符变量；

 

以下则可行：

```c
typedef char* PCHAR; // 一般用大写

PCHAR pa, pb;
```

 可行，同时声明了两个指向字符变量的指针；



虽然：

```c
char *pa, *pb;  //也可行
```

但相对来说没有用typedef的形式直观，尤其在需要大量指针的地方，typedef的方式更省事。



### 用途二：与struct搭配使用

用在旧的C的代码中，帮助struct。以前的代码中，声明struct新对象时，必须要带上struct，即形式为： struct 结构名 对象名，如：

```c
struct tagPOINT1
{
  int x;
  int y;
};
struct tagPOINT1 p1; 
```



而在C++中，则可以直接写：结构名 对象名，即：

```c
tagPOINT1 p1;
```



估计某人觉得经常多写一个struct太麻烦了，于是就发明了：

```c
typedef struct tagPOINT
{
  int x;
  int y;
}POINT;
POINT p1;
```

这样就比原来的方式少写了一个struct，比较省事，尤其在大量使用的时候

 

或许，在C++中，typedef的这种用途二不是很大，但是理解了它，对掌握以前的旧代码还是有帮助的，毕竟我们在项目中有可能会遇到较早些年代遗留下来的代码。



## typedef与结构结合使用

```c
typedef struct tagMyStruct 
　　{ 
　　int iNum; 
　　long lLength; 
　　} MyStruct; 
```

*这语句实际上完成两个操作：* 

1. 先定义一个新的结构类型 

```c
struct tagMyStruct 
　　{ 
　　int iNum; 
　　long lLength; 
　　}; 
```

* 分析：tagMyStruct称为“tag”，即“标签”，实际上是一个临时名字，**struct 关键字和tagMyStruct一起**，构成了这个结构类型，不论是否有typedef，这个结构都存在

* 我们可以用struct tagMyStruct varName来定义变量
* 但要注意，**使用tagMyStruct varName来定义变量是不对的**，因为struct 和tagMyStruct合在一起才能表示一个结构类型

 

2. typedef为这个新的结构起了一个名字，叫MyStruct

   ```c
   typedef struct tagMyStruct MyStruct; 
   ```

　　因此，MyStruct实际上相当于struct tagMyStruct，我们可以使用MyStruct varName来定义变量。



3. 规范写法： 

```c
struct tagNode 
　　{ 
　　char *pItem; 
　　struct tagNode *pNext; 
　　}; 

typedef struct tagNode tag; 
```



4. 简便写法：

```c
　　typedef struct tagMyStruct 
　　{ 
　　int iNum; 
　　long lLength; 
　　} tag; 
```



 