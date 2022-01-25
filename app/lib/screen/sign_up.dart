import 'package:flutter/material.dart';

enum Gender { man, women }

class SignUpPage extends StatefulWidget {
  const SignUpPage({Key? key}) : super(key: key);

  @override
  State<SignUpPage> createState() => _SignUpPageState();
}

class _SignUpPageState extends State<SignUpPage> {
  final bool _isChecked = false;

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
        title: const Text('회원가입'),
      ),
      body: Container(
        padding: EdgeInsets.all(size.width * 0.05),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            TextFormField(
              textCapitalization: TextCapitalization.words,
              textInputAction: TextInputAction.next,
              decoration: const InputDecoration(
                border: OutlineInputBorder(
                    borderSide: BorderSide(color: Colors.white, width: 2.0),
                    borderRadius: BorderRadius.all(Radius.circular(10))),
                labelText: '이름',
                hintText: 'ex) 이성훈',
              ),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return '잘못된 형식입니다.';
                }
                return null;
              },
            ),
            SizedBox(
              height: size.height * 0.02,
            ),
            TextFormField(
              textCapitalization: TextCapitalization.words,
              textInputAction: TextInputAction.next,
              decoration: const InputDecoration(
                border: OutlineInputBorder(
                    borderSide: BorderSide(color: Colors.white, width: 2.0),
                    borderRadius: BorderRadius.all(Radius.circular(10))),
                labelText: '생년월일',
                hintText: '2020-02-01',
              ),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return '잘못된 형식입니다.';
                }
                return null;
              },
            ),
            SizedBox(
              height: size.height * 0.02,
            ),
            TextFormField(
              textCapitalization: TextCapitalization.words,
              textInputAction: TextInputAction.next,
              decoration: const InputDecoration(
                border: OutlineInputBorder(
                    borderSide: BorderSide(color: Colors.white, width: 2.0),
                    borderRadius: BorderRadius.all(Radius.circular(10))),
                labelText: '휴대전화번호',
                hintText: '01012341234',
              ),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return '잘못된 형식입니다.';
                }
                return null;
              },
            ),
            SizedBox(
              height: size.height * 0.02,
            ),
            TextFormField(
              textCapitalization: TextCapitalization.words,
              textInputAction: TextInputAction.next,
              decoration: const InputDecoration(
                border: OutlineInputBorder(
                    borderSide: BorderSide(color: Colors.white, width: 2.0),
                    borderRadius: BorderRadius.all(Radius.circular(10))),
                labelText: '이메일계정',
                hintText: 'soso@naver.com',
              ),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return '잘못된 형식입니다.';
                }
                return null;
              },
            ),
          ],
        ),
      ),
    );
  }
}
