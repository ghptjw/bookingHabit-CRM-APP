import 'package:bookinghabit/widget/input_text_widget.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

enum Gender { MAN, WOMEN }

class SignUpPage extends StatefulWidget {
  const SignUpPage({Key? key, this.size}) : super(key: key);
  final Size? size;

  @override
  State<SignUpPage> createState() => _SignUpPageState();
}

class _SignUpPageState extends State<SignUpPage> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  bool _isChecked = false;
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
        Get.offAllNamed('/');
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
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                InputText(
                  labelValue: '이름',
                  hintValue: 'ex) 이성훈',
                  myController: _nameController,
                ),
                _siezdBoxMargin(size),
                InputText(
                  labelValue: '생년월일',
                  hintValue: '2020-02-01',
                  myController: _birthController,
                ),
                _siezdBoxMargin(size),
                Row(
                  children: <Widget>[
                    Flexible(
                      flex: 10,
                      fit: FlexFit.loose,
                      child: Container(
                        decoration: BoxDecoration(
                          border: Border.all(
                            width: _gender == Gender.MAN ? 2 : 1,
                            color: _gender == Gender.MAN
                                ? Color.fromRGBO(61, 61, 61, 1)
                                : Color.fromRGBO(173, 173, 173, 1),
                          ),
                          borderRadius: const BorderRadius.all(
                              Radius.circular(10.0) // POINT
                              ),
                        ),
                        child: RadioListTile(
                            activeColor: Colors.black,
                            title: const Text('남성'),
                            value: Gender.MAN,
                            groupValue: _gender,
                            onChanged: (value) {
                              setState(() {
                                _gender = Gender.MAN;
                              });
                            }),
                      ),
                    ),
                    const Spacer(),
                    Flexible(
                      flex: 10,
                      fit: FlexFit.loose,
                      child: Container(
                        decoration: BoxDecoration(
                          border: Border.all(
                            width: _gender == Gender.WOMEN ? 2 : 1,
                            color: _gender == Gender.WOMEN
                                ? Color.fromRGBO(61, 61, 61, 1)
                                : Color.fromRGBO(173, 173, 173, 1),
                          ),
                          borderRadius: const BorderRadius.all(
                              Radius.circular(10.0) // POINT
                              ),
                        ),
                        child: RadioListTile(
                            activeColor: Colors.black,
                            title: const Text('여성'),
                            value: Gender.WOMEN,
                            groupValue: _gender,
                            onChanged: (value) {
                              setState(() {
                                _gender = Gender.WOMEN;
                              });
                            }),
                      ),
                    )
                  ],
                ),
                _siezdBoxMargin(size),
                InputText(
                  labelValue: '휴대전화번호',
                  hintValue: '01012341234',
                  myController: _phoneController,
                ),
                _siezdBoxMargin(size),
                InputText(
                  labelValue: '이메일계정',
                  hintValue: 'soso@naver.com',
                  myController: _emailController,
                ),
                _siezdBoxMargin(size),
                InputText(
                  labelValue: '비밀번호',
                  hintValue: '',
                  myController: _passwordController,
                ),
                _siezdBoxMargin(size),
                InputText(
                  labelValue: '비밀번호확인',
                  hintValue: '',
                  myController: _passwordController,
                ),
                _siezdBoxMargin(size),
                Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Checkbox(
                        value: _isChecked,
                        onChanged: (value) {
                          setState(() {
                            _isChecked = value!;
                          });
                        }),
                    const Expanded(
                      child: Text(
                        '해당 회원으로부터 본 서비스 이용에 필요한 이용약관, 개인정보 처리방침및 위치기반 서비스 이용약관 동의를 받았습니다.',
                        style: TextStyle(),
                      ),
                    )
                  ],
                ),
                _siezdBoxMargin(size),
                Row(
                  children: [
                    Expanded(
                      flex: 10,
                      child: OutlinedButton(
                        child: const Text(
                          '취소',
                          style: TextStyle(
                            fontSize: 16,
                          ),
                        ),
                        style: OutlinedButton.styleFrom(
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(10.0),
                          ),
                          padding: const EdgeInsets.all(25),
                          side: const BorderSide(
                              width: 1,
                              color: Color.fromRGBO(160, 156, 156, 1)),
                          primary: const Color.fromRGBO(188, 188, 188, 1),
                        ),
                        onPressed: () {
                          _signUp();
                        },
                      ),
                    ),
                    const Spacer(),
                    Expanded(
                      flex: 10,
                      child: OutlinedButton(
                        child: const Text(
                          '완료',
                          style: TextStyle(
                            fontSize: 16,
                          ),
                        ),
                        style: OutlinedButton.styleFrom(
                            shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(10.0),
                            ),
                            padding: const EdgeInsets.all(25),
                            backgroundColor:
                                const Color.fromRGBO(232, 232, 232, 1),
                            primary: const Color.fromRGBO(188, 188, 188, 1),
                            side: const BorderSide(style: BorderStyle.none)),
                        onPressed: () {
                          _signUp();
                        },
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Widget _siezdBoxMargin(size) {
    return SizedBox(height: size.height * 0.02);
  }
}
