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
