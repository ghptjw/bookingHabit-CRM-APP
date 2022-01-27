import 'package:bookinghabit/widget/input_text_widget.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';

enum Gender { MAN, WOMEN }

class SignUpPage extends StatefulWidget {
  const SignUpPage({Key? key}) : super(key: key);

  @override
  State<SignUpPage> createState() => _SignUpPageState();
}

class _SignUpPageState extends State<SignUpPage> {
  bool _isChecked = false;
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  Gender _gender = Gender.MAN;

  @override
  Widget build(BuildContext context) {
    final Size size = MediaQuery.of(context).size;
    final _auth = FirebaseAuth.instance;
    final TextEditingController _emailController = TextEditingController();
    final TextEditingController _passwordController = TextEditingController();
    final TextEditingController _phoneController = TextEditingController();
    final TextEditingController _birthController = TextEditingController();
    final TextEditingController _nameController = TextEditingController();

    void _signUp() async {
     
      try {
        await FirebaseAuth.instance.createUserWithEmailAndPassword(
            email: _emailController.text, password: _passwordController.text);
             print('${_emailController.text} 비밀번호 ${_passwordController.text}');
      } on FirebaseAuthException catch (e) {
        if (e.code == 'weak-password') {
          print('The password provided is too weak.');
        } else if (e.code == 'email-already-in-use') {
          print('The account already exists for that email.');
        }
      } catch (e) {
        print(e);
      }
    }

    return Scaffold(
      appBar: AppBar(
        bottomOpacity: 0.0,
        elevation: 0.0,
        foregroundColor: Colors.black,
        backgroundColor: Colors.white,
        shadowColor: Colors.white,
        title: const Text('회원가입'),
      ),
      body: Form(
        key: _formKey,
        child: Padding(
          padding: EdgeInsets.all(size.width * 0.05),
          child: SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                InputText(
                  labelValue: '이름',
                  hintValue: 'ex) 이성훈',
                  myController: _nameController,
                ),
                SizedBox(
                  height: size.height * 0.02,
                ),
                InputText(
                  labelValue: '생년월일',
                  hintValue: '2020-02-01',
                  myController: _birthController,
                ),
                SizedBox(
                  height: size.height * 0.02,
                ),
                Row(
                  children: <Widget>[
                    Flexible(
                      fit: FlexFit.loose,
                      child: RadioListTile(
                          title: const Text('남성'),
                          value: Gender.MAN,
                          groupValue: _gender,
                          onChanged: (value) {
                            setState(() {
                              _gender = Gender.MAN;
                            });
                          }),
                    ),
                    Flexible(
                      fit: FlexFit.loose,
                      child: RadioListTile(
                          title: const Text('여성'),
                          value: Gender.WOMEN,
                          groupValue: _gender,
                          onChanged: (value) {
                            setState(() {
                              _gender = Gender.WOMEN;
                            });
                          }),
                    )
                  ],
                ),
                SizedBox(
                  height: size.height * 0.02,
                ),
                InputText(
                  labelValue: '휴대전화번호',
                  hintValue: '01012341234',
                  myController: _phoneController,
                ),
                SizedBox(
                  height: size.height * 0.02,
                ),
                InputText(
                  labelValue: '이메일계정',
                  hintValue: 'soso@naver.com',
                  myController: _emailController,
                ),
                SizedBox(
                  height: size.height * 0.02,
                ),
                InputText(
                  labelValue: '비밀번호',
                  hintValue: '',
                  myController: _passwordController,
                ),
                SizedBox(
                  height: size.height * 0.02,
                ),
                Row(
                  children: [
                    Checkbox(
                        value: _isChecked,
                        onChanged: (value) {
                          setState(() {
                            _isChecked = value!;
                          });
                        }),
                    const Text(
                        '해당 회원으로부터 본 서비스 이용에 필요한 이용약관, 개인정보 처리방침 및 위치기반 서비스 이용약관 동의를 받았습니다.')
                  ],
                ),
                ElevatedButton(
                    onPressed: () {
                      _signUp();
                    },
                    child: const Text('회원가입')),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
