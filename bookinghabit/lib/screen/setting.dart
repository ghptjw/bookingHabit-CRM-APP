import 'package:flutter/material.dart';

class Setting extends StatelessWidget {
  const Setting({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final Size size = MediaQuery.of(context).size;
    return Scaffold(
      appBar: AppBar(
        bottomOpacity: 0.0,
        elevation: 0.0,
        foregroundColor: Colors.black,
        backgroundColor: Colors.white,
        shadowColor: Colors.white,
        title: const Text('설정'),
      ),
      body: Padding(
        padding: EdgeInsets.all(size.width * 0.05),
        child: Column(
          children: [
            
          ],
        ),
      )
    );
  }
}