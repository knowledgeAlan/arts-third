# Java ArrayList使用



## 1 .介绍

我们将讨论 java集合ArrayLIst,讨论ArrayList属性,和一些常用使用方法,讨论它优缺点。

[ArrayList](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html) 在java核心包中,所以你不需要增加额外jar包,使用该类仅仅增加以下语句:

>  import java.util.ArrayList;

List 存放一组有序值,可以包含重复值,`ArrayList` 实现`List` 接口建立数组,它可以动态扩容和缩容

当增加和删除元素时候,元素很容易访问从下标零开始,这个实现有以下几点属性:

- 随机访问花费时间O(1)
- 增加元素花费时间常量O(1)
- 插入和删除花费时间O(n)
- 无序*ArrayList* 查找花费时间O(n)和排序花费时间O(log n)



## 2 .创建

`ArrayList` 有个种构造方法,我们将讨论在这节中。

第一,`ArrayList`  是泛型类,因此你可以参数化任何类型你想要的并且编译器能保证正确,例如,

你不可能增加Integer值插入集合String类型，你不需要强制转换类型当你获取元素从集合中。

第二,很好实践使用泛型*List* 接口作为参数变量，因为可以从特殊实现接口分离。



### 2.1 默认无参构造函数

>  List<String> list = new ArrayList<>();
>
> assertTrue(list.isEmpty());

我们简单创建空`ArrayList`  实例



### 2.2 构造函数接受容量大小

> List<String> list  = new ArrayList(20);

构造初始化长度的数组,可以帮助避免没必要重新计算当增加新元素



### 2.3 集合构造函数

```java
Collection<Integer> number = IntStream.range(0, 10).boxed().collect(toSet());
List<Integer> list = new ArrayList<>(numbers);
assertEquals(10, list.size());
assertTrue(numbers.containsAll(list));
```





## 3.增加元素

插入元素可以在最后或者在指定位置:

> List<Long> list = new ArrayList<>();
>
> list.add(1L);
> list.add(2L);
> list.add(1, 3L);
>
> assertThat(Arrays.asList(1L, 3L, 2L), equalTo(list));

也可以插入集合或者几个元素一起:

>  List<Long> list = new ArrayList<>(Arrays.asList(1L, 2L, 3L));
> LongStream.range(4, 10).boxed()
>
>  .collect(collectingAndThen(toCollection(ArrayList::new), ys -> list.addAll(0, ys)));
>
> assertThat(Arrays.asList(4L, 5L, 6L, 7L, 8L, 9L, 1L, 2L, 3L), equalTo(list));

## 4.List迭代器 *Iterating*



有两种迭代器:*Iterator* 和*ListIterator*  ,然而前者反序是一个方向，后者可以双向反序，以下展示仅仅*ListIterator*

```java
List<Integer> list = new ArrayList<>(IntStream.range(0,10).boxed().collect(toCollection(ArrayList::new)));

ListIterator<Integer> it = list.listIterator(list.size());

List<Integer> result = new ArrayList<>(list.size());

while (it.hasPrevious()) {
    result.add(it.previous());
}

Collections.reverse(list);
assertThat(result, equalTo(list));
```



你也可以查找,新增或删除元素使用*iterators*



## 5.List查找

我们展示如何查找使用集合

```java
List<String> list = LongStream.range(0, 16).boxed().map(Long::toHexString).collect(toCollection(ArrayList::new));
List<String> stringsToSearch = new ArrayList<>(list);
stringsToSearch.addAll(list);
```



### 5.1 查找在无序数组

为了查找一个元素使用*indexOf()* 或者*lastIndexOf()*  方法,他们接受一个对象放回*int*值

```java
assertEquals(10, stringsToSearch.indexOf("a"));
assertEquals(26, stringsToSearch.lastIndexOf("a"));
```

如果你想要查找所有满足条件,你可以使用java 8 [Stream API](https://www.baeldung.com/java-8-streams) (了解更过)过滤器,下面是示例:

```java
Set<String> matchingStrings = new HashSet<>(Arrays.asList("a", "c", "9"));
 
List<String> result = stringsToSearch.stream().filter(matchingStrings::contains)
  .collect(toCollection(ArrayList::new));
 assertEquals(6, result.size());
```

也可以使用for循环或迭代器

```java
Iterator<String> it = stringsToSearch.iterator();
Set<String> matchingStrings = new HashSet<>(Arrays.asList("a", "c", "9"));
 
List<String> result = new ArrayList<>();
while (it.hasNext()) {
    String s = it.next();
    if (matchingStrings.contains(s)) {
        result.add(s);
    }
}
```

### 5.2 查找在有序数组

如果是有序数组,可以使用二分法查找算法会更高效:

```java
List<String> copy = new ArrayList<>(stringsToSearch);
Collections.sort(copy);
int index = Collections.binarySearch(copy, "f");
assertThat(index, not(equalTo(-1)));
```



注意:如果元素没有找到返回-1

## 6.删除

为了删除元素，你应该找元素索引和执行*remove()* 方法，还有重载方法，接受一个对象参数，并查找然后删除第一个出现相等元素

```java
List<Integer> list = new ArrayList<>(
  IntStream.range(0, 10).boxed().collect(toCollection(ArrayList::new))
);
Collections.reverse(list);
 
list.remove(0);
assertThat(list.get(0), equalTo(8));
 
list.remove(Integer.valueOf(0));
assertFalse(list.contains(0));
```



但是要小心处理封装类型例如Integer,删除一个特定元素，应该拆箱成int，通过索引删除元素

你有可以使用后面提到 *Stream API* 删除多个元素，但是不会再这里讨论,在这里我们使用迭代器:

```java
Set<String> matchingStrings
 = HashSet<>(Arrays.asList("a", "b", "c", "d", "e", "f"));
 
Iterator<String> it = stringsToSearch.iterator();
while (it.hasNext()) {
    if (matchingStrings.contains(it.next())) {
        it.remove();
    }
}
```



## 7.总结

在文章开始，我们看过   *java*中 *ArrayList* ，展示如何创建*ArrayList* 实例，增加，查找或删除元素使用不同方法，



[译文链接](https://www.baeldung.com/java-arraylist)



 