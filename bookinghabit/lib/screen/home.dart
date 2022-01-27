import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:bookinghabit/screen/mypage.dart';
import 'package:bookinghabit/screen/sign_in.dart';

class Home extends StatelessWidget {
  const Home({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return StreamBuilder<User?>(
        stream: FirebaseAuth.instance.authStateChanges(),
        builder: (BuildContext context, AsyncSnapshot<User?> user) {
          if (user.hasData) {
            return const Mypage();
          } else {
            return AuthPage();
          }
        });
  }
}
