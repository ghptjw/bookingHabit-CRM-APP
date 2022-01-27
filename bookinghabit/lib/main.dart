import 'package:bookinghabit/screen/home.dart';
import 'package:bookinghabit/screen/mypage.dart';
import 'package:bookinghabit/screen/sign_in.dart';
import 'package:bookinghabit/screen/sign_up.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:firebase_core/firebase_core.dart';
import 'firebase_options.dart';


void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  // StatelessWidget build 기반으로 화면을 그린다.
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
      home: Home(),
      initialRoute: '/',
      getPages: [
        GetPage(
          name: '/',
          page: () => Home(),
          /* transition,  */
        ),
        GetPage(
          name: '/auth',
          page: () => AuthPage(),
          /* transition,  */
        ),
        GetPage(
          name: '/signup',
          page: () => SignUpPage(),
        ),
          GetPage(
          name: '/mypage',
          page: () => Mypage(),
        ),
      ],
    );
  }
}
