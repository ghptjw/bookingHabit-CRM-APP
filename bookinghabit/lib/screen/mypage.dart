import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';

class Mypage extends StatelessWidget {
  const Mypage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    
    return   SizedBox(
      child:  Text('${FirebaseAuth.instance.currentUser!.email}'),
    );
  }
}