[Goѧϰ�̳�](http://c.biancheng.net/view/1.html)

��̬����

����

�����ٶ�

ԭ������֧��

��׼��ά��

###������
```
bufio  ������IO
bytes  �ֽڲ���
container  ?
crypto	�����㷨
database ���ݿ�����
debug	��
encoding	json  xml  base64
flag	��
fmt		��ʽ��
go		go���ԵĴʷ����ʷ���������
html	htmlת���Լ�ģ��
image 	����ͼ�θ�ʽ�ķ��ʺ�����
io		
math	��ѧ��
net		���磬socket �� http �� �ʼ� �� rpc �� smtp
os		
path	
pugin
reflect	����ӿ�
regexp	����ӿ�
runtime	����ʱ�ӿ�
sort	����ӿ�
strings	�ַ�������
time	ʱ��
text	�ı�ģ�壬token���ʷ���
```
###�����﷨

��������

```
bool
string
int��int8��int16��int32��int64
uint��uint8��uint16��uint32��uint64��uintptr
byte // uint8 �ı���
rune // int32 �ı��� ����һ�� Unicode ��
float32��float64
complex64��complex128
```

import

```
������ļ�������

���ܵ���û��ʹ�õ�����
���ܶ���û��ʹ�õı���
```

const����������ֻ��ʱ�ַ�������������ֵ

```
iota ���ڳ����У�iotaÿ����һ���Զ���һ����ǰ��Ĳ����������ָ����Ĭ��ʹ����һ����
const{
	name = "helun"
	age = 10 
	isAlive = true`
}
```

���飬��Ƭ����̬���飩

```
���岢��ʼ��һ������
var array = []int{0,1,2}
�����ʽ��������ʼ��һ������
array2 := []int{0,1} 

һ���������Ƭ�����Ⱥ���������3
var slice = []int{0,1,2}
ʹ��make����һ����Ƭ������3������5��δ��ʼ����int����Ĭ�����0
slice2 := make([]int,3,5)

len��slice���� ��Ƭ����  �� cap��slice�� ��Ƭ������append() �� copy() 

```

range

```
//�������飬��Ƭ��ͨ�������ϵ�Ԫ�أ�����Ԫ��������Ԫ��ֵ
nums := []int{0,1,2}
for index,value := range nums{
	fmt.Println(index,"-->",value)
}

maps := map[int]string{1:"1",2:"2"}
for key,value := range maps{
	fmt.Println(key," to " , value)
}

//rangeҲ��������ö��Unicode�ַ�������һ���������ַ����������ڶ������ַ���Unicode��ֵ������
for index,code range := "go language"{
	fmt.Prinln(index , " is " , code)
}

```
�ṹ��

```
��c�еĽṹ�����ƣ�
type structName struct{
	name string 
	age int8
} 

 helun1 := structName{"helun",17}
 helun2 := structName{name:"helun",age:18}
```

���� 

```
func functionName([parameters...]) [returnTypes] {

}
```

����

```
һ��ָ���˽����ߵĺ������Ƿ����������java  c++���������Ĭ��Ϊ��ķ�������thisָ�룬
go������Ҫ��������ʾ����������������
func (type Type) functionName([parameters...]) [returnTypes]{

}
```

�ӿ�

```
type IPhone interface{
	call(num string);
}

type Phone struct{
	name string
}


func (phone Phone) call(nums string){
	fmt.Pritln("I am ", phone.name,", I can call " , nums);
}

func main(){
	var phone IPhone
	phone = new(Phone){"Nokia"}
	phone.call("110")
	phone = new(Phone){"iPhone"}
	phone.call("911")
}
```

error�ӿ�

```
type SelfDivide struct{
	a int 
	b int
}

func (divide *SelfDivide) Error() string{
	strFormat := "a is %d , b is 0"
	return fmt.Sprintf(strFormat, divide.a)
}

func Divide(a int , b int) (result int,errorMsg string){
	if(b == 0){
		dDate := Divide{a:a,b:b}
		errorMsg = dDate.Error();
		return ;
	}else{
		return a/b,"";
	}
}

func main(){
	if result , msg := SelfDivide(100,0) ; msg==""{
		fmt.Println(result)
	}else{
		fmt.Println(msg)
	}
}
```
ָ�루���ã�

```
goĬ�϶���ֵ���ݣ���copyһ��һ����ֵ�������д���������Ҫ�������õ���Ҫʹ��ָ��

```
channel

```
����δ�������������̵߳ȴ�channel�Ļ�������channel����ȴ������ߣ����̣߳����ѻ�����
package main
import(
	"fmt"
)

func main(){
	channel := make(chan int, 2)
	channel <- 2;
	channel <- 1;
	channel <- 3;
	
	fmt.Println(<-channel)
	fmt.Println(<-channel)
}


package main
import (
	"fmt"
)

func main(){

	c := make(chan int , 10)
	go fibo(cap(c),c)
        // range ��������ÿ����ͨ�����յ������ݣ���Ϊ c �ڷ����� 10 ��
        // ����֮��͹ر���ͨ���������������� range �����ڽ��յ� 10 ������
        // ֮��ͽ����ˡ��������� c ͨ�����رգ���ô range �����Ͳ�
        // ��������Ӷ��ڽ��յ� 11 �����ݵ�ʱ��������ˡ�
	for i := range c{
		fmt.Println(i)
	}
}

func fibo(n int , c chan int){
	x,y := 0,1 
	for i := 0 ; i < n ; i++{
		c <-x 
		x,y = y ,x+y
	}
	close(c)
}
```

����

```
ʹ��go�ؼ��֣�ִ��һ���������ú�������һ���µ��߳���ִ�С�go��������һ���������߳�

package main
import(
	"fmt"
)

func main(){
	channel := make(chan int)
	array := []int{0,1,2,3,4,5};
	go sum(array[:len(array)/2],channel)
	go sum(array[len(array)/2],channel)
	x,y := <- channel,<- channel ;
	fmt.Println("x + y =",x+y)
	
}

func sum(a []int , b chan int){
	sum := 0 
	for _,value := range a{
		sum += value
	}
	b <- sum
}

```

###�߼��﷨

ʹ��new ����ָ��

```
str := new(string)
*str = "hahah"
str = "jasj" // ���лᱨ����Ϊstr��һ��ָ�����
fmt.Println(str)
```

�������ݷ���

```
���������ñ���Ӧ�÷����ڶѺ�ջ�ϵ�ԭ���ǣ�
�����Ƿ�ȡ��ַ��
�����Ƿ������ݡ�
```

type

```
type TypeAlias = Type �� ΪType���Ͷ���һ������������Ϊ�Ǳ�������ȡ����

type TypeAlias Type �� ��Type��Ϊһ���µ��Զ�������
```

struct

```
���нӿڣ��ܵ���˵����һ�׻��ƺ�java�еĽӿڣ��������Ǻ���

����Ƕ��


Ƕ���еĿ��Դ���ͬ�����ԣ�����ͬ��������Ҫʹ��ȫ·�������������ֱ��ʹ������������Ϊ��������

����ΪǶ�׵Ľṹ��ָ��ͬ���ĺ��������������ȫ·�����ʣ������ȷ�������ķ�����Ҳ�������Ľṹ��ʵ�ֵķ����Ḳ��Ƕ�׵Ľṹ��ķ���

package main

import(

)


type X struct{
	a int
}

type Y struct{
	X
}

type T struct{
	*X
}

func (x X) Get() int{
	return x.a
]

func (x X) Set( i int) {
	x.a = i
}

func main(){
	x := X{a:1}
	y := Y{X:x}
	t := T{X:&x}
	println(y.Get())
	y.Set(2)
	println(y.Get())
	
	
}



```














































