import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

main() {
  // runApp(const MaterialApp(
  //   home: Center(
  //     child: Text(
  //       "Hello flutter Javer",
  //       textDirection: TextDirection.ltr,
  //       style: TextStyle(fontSize: 30, color: Colors.orange),
  //     ),
  //   ),
  // ));


  runApp(const MaterialApp(
    home: Scaffold(
      // appBar: AppBar(
      //   title: Text("No.1 Flutter App"),
      // ),
      body: Center(
        child: Text(
          "Hello flutter Javer",
          textDirection: TextDirection.ltr,
          style: TextStyle(fontSize: 30, color: Colors.orange),
        ),
      ),
    ),
  ));
}
// 有状态Widget：运行过程中存在一些数据的改变，可以使用StatusWidget
// 无状态Widget：运行过程中无数据的改变(内容确定)，可以使用StatelessWidget
class HYHomePage extends StatelessWidget{


  @override
  Widget build(BuildContext context) {

    return Text("Test widget");
  }

}
