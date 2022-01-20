import 'package:app/screen/login.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        title: '오늘의습관',
        theme: ThemeData(
          primaryColor: Color(0xff16aa83),
        ),
        home: AuthPage());
  }
}
 // git test4