main(List<String> args) {
  var p = Person('张三');
  print(p.age);
}

class Person {
  final String name;
  final int age;

  // 有参数apge就使用age，否则使用默认值
  Person(this.name, {int? age}) : this.age = age ?? 10 {}
  // 只能赋值操作，给一个确定的值
  // Person(this.name, {int? age=10}) {}
}


// 重定向构造函数
class People{
  String name;
  int age;
  String color;

  // 
  People(String name): this._internal(name, 0,"red");

  // _internal
  People._internal(this.name,this.age,this.color);

  
  set setAge(int age){
    this.age = age;
  }

  int get getAge{
    return this.age;
  }


  // 工厂构造函数
  People.factory(String name): this._internal(name, 0,"yellow");

  factory People.factory_internal(String name,int age){
    return People._internal(name,age,"blue");
  }
}