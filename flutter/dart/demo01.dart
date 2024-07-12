main(List<String> args){
  // sayHello("John");
  print("Hello World");
}

// []表示可选参数
void sayHello(String name, [int? age, double? height]) {
  print(name);
}


// 类的定义
class Person{
  String name;
  int age;
  double? height;

  // 语法糖 this.name = name
  Person(this.name, this.age, {this.height});

  Person.witdhNameAge(this.name, this.age);

  void sayHello(){
    print("Hello, my name is ${name}");
    // ${name} 中的{}可以省略
    print("Hello, my name is $name");
  }
}

class Student extends Person{
  String school;

  Student(String name, int age, double height, this.school): super(name, age, height);

  void sayHello(){
    print("Hello, my name is $name");
  }
}

class Teacher extends Person{
  String school;

  Teacher(String name, int age, double height, this.school): super(name, age, height);

  void sayHello(){
    print("Hello, my name is $name");
  }
}