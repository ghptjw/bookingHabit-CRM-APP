import 'package:app/screen/sign_in.dart';
import 'package:app/screen/sign_up.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  final int todayGreen = 0xff16AA83;

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      title: '오늘의습관',
      theme: ThemeData(
        primaryColor: Color(todayGreen),
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      debugShowCheckedModeBanner: false,
      home: AuthPage(),
      initialRoute: '/',
      getPages: [
        GetPage(name: '/', page: () => AuthPage()),
        GetPage(name: '/signup', page: () => SignUpPage()),
      ],
    );
  }
}
 // git test4