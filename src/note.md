# Java基础
## 概念部分
### Java特点
* 平台无关性
* 面向对象
* 内存管理
### Java跨平台
Java跨平台依赖于JVM (Java Virtual Machine)  
JVM作为一个软件，不同平台对应不同版本。  
编写的Java代码经编译后生成的.class文件，通过JVM运行。  
JVM作为一个中间体，是实现跨平台的关键，将class文件翻译为机器语言，以便于在各个平台运行。  
编译的结果不是生成机器码，而是字节码。字节码不能直接运行，须经JVM翻译为机器语言才能运行。  
但是，class文件每个平台都相同，每个平台的JVM所翻译的机器码不相同。
跨平台是Java程序，不是JVM。  
### JDK、JRE、JVM
JDK是Java开发工具包，是开发Java所需的工具集合。包含JVM，编译器(javac)，调试器(jdb)等开发工具，以及一系列的类库。JDK提供了开发、编译、调试及运行Java程序所需全部工具和环境。  
JRE是Java运行时环境，是Java运行的最小环境。不包含开发工具。
JVM是Java虚拟机，提供Java运行的环境。 
### Java的编译和解释
+ 编译：
  * Java源代码首先被编译为字节码，JIT把编译后的机器码保存备用。  
  * 编译型语言：程序执行前，整个代码被编译成机器码或者字节码，生成可执行文件。执行时直接运行编译后的代码，速度快，跨平台差。
  * 编译型语言：C、C++  
+ 解释：
  * JVM中一个方法调用计数器，当累计计数大于一定值，使用JIT进行编译机器码文件。否则利用解释器进行解释。  
  * 解释型语言：程序执行时，逐行解释执行源代码，不生成可执行文件。通常由解释器动态解释并执行代码。跨平台好，但执行速度慢。
  * 解释型语言：Python
### Python与Java
* Python是解释型语言，翻译时会在执行程序的同时进行翻译。
* Java是已编译的语言，Java编译器会将源代码编译为字节码，而字节码由JVM转换为机器码。
## 数据类型
### 八种基本数据类型
基本数据类型又可分为三类：
* 数值型：整数(byte, short, int, long)、浮点(float, double)
* 字符型：char
* 布尔型：boolean  
取值范围：  

| 关键字        | 位数 | 默认值   | 取值范围         |
|------------|----|-------|--------------|
| byte       | 8  | 0     | -2^7~2^7-1   |
| short      | 16 | 0     | -2^15~2^15-1 |
| int(默认)    | 32 | 0     | -2^31~2^31-1 |
| long       | 64 | 0     | -2^63~2^63-1 |
| float      | 32 | 0.0f  | -2^31~2^31-1 |
| double(默认) | 64 | 0.0d  | -2^63~2^63-1 |
| boolean    | 8  | false | true, false  |

### long和int的互换

int换为long是安全的，因为long的范围大于int。  
 
```Java  
int intValue = 10;
long longValue = intValue; //安全转换
```
long换为int可能会导致数据丢失或溢出。  
转换结果是截断后的低位部分。  
```Java
long longValue = 100L;
int intValue = (int) longValue; //强制类型转换，可能导致溢出
```
### 数据类型转换
* 自动类型转换(隐式转换)：  
目标类型范围大于原类型，会自动转换。例如int转long，float转double。
* 强制类型转换(显式转换)：  
目标类型范围小于原类型，可能导致溢出。例如long转int，double转float。
* 字符串转换：  
如字符串换int的Integer.parseInt()方法。  
如字符串换double的Double.parseDouble()方法。
### 装箱和拆箱
装箱和拆箱是将基本数据类型和对应包装类相互转换的过程。
```Java
Integer i = 10; //装箱
int n = i;  //拆箱
```
自动装箱发生在两种情况，一种是赋值时，一种是方法调用时。  
* 赋值
```Java
//before autoboxing
Integer i = Integer.valueOf(3);
int iPrimitive = i.intValue();
```
* 方法调用
```Java
public static void main(Integer i) {
  System.out.println("autoboxing example - method innovation i="+i);
  return i;
}
//autoboxing and unboxing method innovation
show(3);
int result=show(3);
```
* 自动装箱的弊端
在循环中进行自动装箱操作的情况，如下就会创建多余的对象，影响性能。
```Java
Integer sum = 0;
for(int i=1;i<5000;i++){
    sum+=i;
        }
```
上述sum+=i可以看成sum=sum+i，但是+操作符不适用于Integer，sum会进行自动拆箱，进行数值相加操作，最后发生自动装箱转为Integer。实际上，内部变化如下：  
```Java
int result = sum.intValue()+ i;
Integer sum = new Integer(result);
```
由于sum声明为Integer类型，在上述的循环会产生5000个无用Integer对象，在这样庞大循环中，会降低性能，增加垃圾回收的工作量。
### Java的Integer
Integer是int的包装类，把int类型包装为Object对象。对象封装有诸多好处，可以把属性和处理这些属性的方法结合在一起。比如Integer就有parseInt()专门处理int相关的数据。  
另一个原因是Java绝大多数方法和类都是处理对象的，如ArrayList集合类就只能以类作为存储对象，而这时将一个int写入list是不可能的，必须把它包装成类，也就是Integer才能被List所接受。  
* 泛型的应用
在Java中，泛型只能使用引用类型，不能使用基本类型。泛型在使用中要想使用int，必须使用Integer的包装类。

```Java
List<Integer> list = new ArrayList();
list.add(3);
list.add(1);
list.add(2);
Collections.sort(list);
system.out.println(list);
```
将元素排序，并将排序结果存在一个新的列表中，若采用int，则无法直接使用Collections.sort()方法。  
若采用Integer包装类，则可以使用该方法。

* 转换中的应用
在Java中，基本类型和转换类型不能直接进行转换，必须使用包装类实现。
例如：
```Java
int i = 10;
Integer integer = new Integer();
String string = integer.toString();
system.out.println(string);
```
将一个int类型的值转为String类型，必须将其先转为Integer类型，然后再用toString()方法转为String。
* 集合的应用
Java集合只能存对象，而不能存基本数据类型。因此，要想将int存到集合中，必须用Integer包装类。
例如：

```Java
import java.util.ArrayList;

List<Integer> list = new ArrayList<>();
list.add(3);
list.add(1);
list.add(2);
int sum = list.stream().mapToInt(Integer::intValue).sum();
system.out.println(sum);
```
有一个列表，欲对其求和。如果用int，需要用循环对其遍历求和。如果用Integer，可以直接用stream()方法来求和。  
### Integer相比int的优势
+ Integer与int的区别
  * 引用类型和基本数据类型
  * 自动装箱和拆箱
  * 空指针异常  
+ 为何仍然保存int类型  
包装类是引用类型，对象的引用和对象本身是分开存放的；而对于基本数据类型，变量对应的内存块直接存储数据本身。  
因此int类型在读写效率方面要优于Integer。  
btw，在x64的JVM上，在开启引用压缩的情况下，一个Integer对象占用16字节，而一个int只占用4字节。
### Integer的缓存
Java的Integer缓存有静态缓存池，用于存储特定范围的整数值对应的Integer对象。  
默认范围，-128~127。当通过Integer.valueOf(int)方法创建一个在该范围内的整数对象时，并不会每次都声称新的对象实例，而是复用缓存中的现有对象。无需新建对象。  
## 面向对象
### 三态
面向对象是一种编程范式，将现实世界中的事物抽象为对象，对象有属性和行为。  
三大特性：封装，继承，多态。
* 封装：将对象的属性和方法结合在一起。对外隐藏对象的内部细节，通过接口与外界交互。目的是增强安全性和简化编程，使对象独立。  
* 继承：使得子类自动共享父类的数据结构和方法。是代码复用的重要手段。
* 多态：允许不同类的对象对同一消息作出相应。即同一个接口，使用不同的实例执行不同的操作。分为编译时多态(重载)、运行时多态(重写)。使程序具有良好灵活性和扩展性。
### 多态体现在哪些方面
+ 方法重载  
方法重载是指同一类可以有多个同名方法，它们具有不同的参数列表。虽然方法名相同，但是传入的参数不同，编译器在编译时决定调用哪个方法。  
例如对于一个add方法，可以定义为add(int a, int b)，也可为add(double a, double b)
+ 方法重写  
方法重写是子类能够提供对父类重名方法的具体实现。运行时，JVM根据对象的实际类型确定调用哪个版本的方法。实现多态。  
例如在一个动物类Animal中有sound()方法，子类Dog可以重写该方法以实现bark，Cat类可以实现meow。
+ 接口与实现  
多个类可以实现同一个接口，并且用接口类型的引用来调用这些类的方法。  
例如多个类(Dog,Cat)都实现了一个Animal接口，当用Animal类型的引用来调用makesound方法时，会触发相应的实现。
+ 向上转型和向下转型  
使用父类的引用指向子类对象，是向上转型。  
向下转型是将父类引用转回其子类类型。
### 面向对象六大原则
* 单一职责原则：一个类只有一个引起他变化的原因，即一个类只负责一个职责。
* 开放封闭原则：软件实体应该对扩展开放，对修改封闭。
* 里氏替换原则：子类对象应能够替换掉所有父类对象。
* 接口隔离原则：客户端不应该依赖那些它不需要的接口，接口应该又小又专。
* 依赖倒置原则：高层模块不应依赖底层模块，二者应该依赖于抽象；抽象不依赖于细节，细节依赖于抽象。
* 最少知识原则：一个对象应该对其他对象有最少的了解。
### 重载与重写
* 重载：同一个类，可有多个同名方法，有不同参数列表，编译器根据调用的参数类型决定调用哪个方法。  
* 重写：子类可以重新定义父类中的方法，但方法名，参数，返回值须保持一致，通过@override注解表明是重写。
### 抽象类与普通类
* 实例化：普通类可以直接实例化对象，抽象类不能被实例化，只能被继承。
* 方法实现：普通类中的方法可以有具体的实现，而抽象类的方法实现可有可无。
* 继承：一个类可以继承一个普通类、多个接口。也只能继承一个抽象类和多个接口。
* 实现限制：普通类可以被其他类继承和使用，抽象类一般用作基类，被其它类扩展和继承。
### 抽象类和接口
+ 两者特点：  
  * 抽象类用于描述类的共同特性和行为，可以有成员变量、构造方法、具体方法。适用于有明显继承关系的场景。
  * 接口用于定义行为规范，可以多实现。只能有常量和抽象方法。适用于定义类的功能。  
+ 两者区别：
  * 实现方式：实现接口的关键字为implements，继承抽象类的关键字为extends。一个类可以实现多个接口，但一个类只能继承一个抽象类。使用接口可以间接实现多重继承。
  * 方法方式：接口只有定义，不能用方法的实现，Java8可以用default方法体，而抽象类可以有定义和实现，方法可以在抽象类实现。
  * 修饰符：接口成员变量默认为public static final，必须有初值，不能被修改。其所有的成员方法都是public, abstract的。  
  抽象类中的成员变量默认为default，可以在子类中重新定义，也可被赋值。  
  抽象方法被abstract修饰，不能被private, static, synchronized, native修饰，必须以分号结尾，没有花括号。
  * 变量：抽象类可以包含实例变量和静态变量，而接口只能有常量。
### 
