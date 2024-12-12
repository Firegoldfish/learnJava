# Java集合
## 概念
### 数组、集合
+ 数组是固定长度的数据结构，一旦创建长度就无法改变，而集合是动态长度的数据结构，可以根据动态增减元素。
+ 数组可以包含基本数据类型和对象，集合只能包含对象。
+ 数组可以直接访问元素，集合需要通过迭代器或者其他方法访问元素。
+ 常用的集合类：
  + ArrayList：动态数组，实现了List接口，支持动态增长。
  + LinkedList：双向链表，实现了List接口，支持快速的插入和删除操作。
  + HashMap：基于哈希表的Map实现，存储键值对，通过键快速查找值。
  + HashSet：基于HashMap实现的Set集合，用于存储唯一元素。
  + TreeMap：基于红黑树实现的有序Map集合，可以按照键的顺序进行排序。
  + LinkedHashMap：基于哈希表和双向链表实现的Map集合，保持插入顺序或访问顺序。
  + PriorityQueue：优先队列，可以按照比较器或元素的自然顺序进行排序。
### 集合类型
+ List：有序的Collection
  + ArrayList：容量可变，非线程安全，底层数组。支持快速随机访问，增删速度慢。
  + LinkedList：双向列表，增删快，随机访问慢。
+ Set：无序，唯一
  + HashSet：通过HashMap实现，HashMap的Key即HashSet存储的元素，所有Key都使用相同的Value，线程不安全。
  + LinkedHashSet：继承自HashSet，通过LinkedHashMap实现，双向链表维护插入顺序。
  + TreeSet：通过TreeMap实现，添加元素到集合时按照比较规则将其插入到合适的位置，保证插入后的集合仍然有序。
+ Map：键值对集合。Key无序唯一，Value不要求有序，可以重复。检索时需要Key可导出Value
  + HashMap：由数组+链表组成，数组是主体，链表为了解决哈希冲突（拉链法）。JDK1.8后转为红黑树，减少搜索时间。
  + LinkedHashMap：继承自HashMap，底层基于拉链式散列结构或红黑树。增加双向链表，使得可以保持访问顺序。
  + HashTable：数组+链表组成，数组是HashTable主体，链表为了解决哈希冲突。
  + TreeMap：红黑树（自平衡的排序二叉树）
  + ConcurrentHashMap：Node数组+链表+红黑树实现，线程安全（volatile+CAS或者synchronized）
### 线程安全的集合
+ Vector: 内部方法经过synchronized修饰，使用对象数组保存数据，可以根据自动的再增加容量，当数组已满，会创建新的数组，拷贝原有数组元素。
+ Hashtable: 加锁方式是给每个方法上synchronized关键字，这样锁住的是整个Table对象，不支持null键和值，很少使用。多用concurrentHashMap。
+ ConcurrentHashMap: 与HashTable也就是加锁粒度不同。在JDK1.8中，取消了segment字段，直接在table上加锁，实现对每一行加锁，进一步减少了并发冲突的概率。对于put操作，如果Key对应数组元素为null，通过CAS(Compare and Swap)将其设置为当前值。若Key对应不为null，则使用synchronized关键字申请锁，然后进行操作。如果put使得当前链表长度超过一定阈值，则将该链表转为红黑树，从而提高寻址效率。
+ ConcurrentSkipListMap: 实现了一个基于跳表算法的可排序并发集合。跳表是一种可以对数与其时间内完成增删查的数据结构，通过维护多个指向其他元素的跳跃链接实现高效查找。
### ConcurrentHashMap的实现
+ JDK 1.7  
是数组加链表实现的，数组又分为大数组Segment和小数组HashEntry。Segment是一种可重入锁，在CHM中扮演锁的角色；HashEntry则用于存储键值对数据。一个CHM中包含一个Segment数组，一个Segment包含一个HashEntry数组，每个HashEntry是一个链表的元素。  
+ JDK 1.8  
在1.7中，CHM虽是线程安全的，但因为底层是数组+链表的构成，所有在数据多的情况下访问很慢，需要遍历整个表。在1.8中，采用数组+链表/红黑树的方式优化了存储。  
1.8中的CHM通过volatile+CAS或者synchronized来实现线程安全。添加元素会首先判断容器是否空：
  + 若为空，使用volatile+CAS
  + 如不空，根据存储的元素计算该位置是否为空：
    + 若根据存储的元素计算结果为空，CAS；
    + 若根据存储的计算结果不为空，synchronized；  
  + 然后遍历桶中的所有数据，并替换或新增节点到桶中，最后判断是否需转为红黑树，保证线程安全。
  + 总结一句；CHM通过对头节点加锁保证线程安全。锁的粒度相比Segment更小，发生冲突概率更低，并发操作性能提高。
+ JDK1.8使用的红黑树优化了之前的固定链表。当数据量比较大的时候，查询性能也得到了大的提升。
### HashMap重写equals和hashcode方法注意事项
HashMap使用Key对象的hashCode()和equals方法去决定key-value对的索引。当尝试着从HashMap中获取值的时候，这些方法也会被用到。如果没有正确被实现，在这种状况下，两个key也许会产生相同的hashCode和equals输出，HashMap则会认为他们是相同的，然后被覆盖，而非存储到不同的地方。  
同样的，所有不允许存储重复数据的集合类都是以hashCode和equals去查找重复。遵循以下规则：
+ 若o1.equals(o2)，则o1.hashCode()==o2.hashCode()为true
+ 若o1.hashCode()==o2.hashCode()，并不意味着o1.equals(o2)为true
### 重写HashMap的equals方法不当会出现的问题
HashMap在比较元素时，会先通过HashCode进行比较，相同的情况下才会用equals。  
所以用到equals时，hashCode必定相同。而hashCode相同时，equals不一定相同，比如散列冲突。  
重写了equals，不重写hashCode，就会导致equals返回true，hashCode返回false，导致在hashMap等类中存在多个一模一样的对象，导致出现覆盖存储的数据问题，与hashMap只能有唯一的key不符合。  
### HashMap线程不安全
HashMap在多线程下会出现以下问题：JDK8中由于采用数组+链表+红黑树存储，但在多线程下，put方法存在数据覆盖的问题。  
要保证线程安全，使用该方法：
+ Collections.synchronizedMap同步加锁的方式，还可以用HashTable，但同步的方式显然性能不达标，而ConcurrentHashTable更适合高并发。
### Set集合特点
+ Set中元素唯一，不会出现重复。
+ Set内部数据结构(哈希树，红黑树)实现key的无重复。当向Set插入数据时，先根据hashCode确定位置，然后通过equals比较是否已有数据，若存在则不会再次插入，保证唯一性。
hjm
哈吉米
哈吉米
哈吉米