import 'package:flutter/material.dart';

class AuthPage extends StatelessWidget {
  //  const AuthPage({Key? key}) : super(key: key);
  // ignore: prefer_final_fields

  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    // ? context 앱이 구동되고 있는 정보
    final Size size = MediaQuery.of(context).size;

    return Scaffold(
        resizeToAvoidBottomInset: false,
        body: SingleChildScrollView(
            child: Container(
          padding: EdgeInsets.all(size.width * 0.05),
          color: const Color(0xfffcfcfc),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Image.asset(
                'assets/images/logo.png',
                width: size.width * 0.6,
                height: size.height * 0.35,
              ),
              _inputForm(size),
              SizedBox(
                height: size.height * 0.02,
              ),
              _authButton(size),
              SizedBox(
                height: size.height * 0.02,
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: const [
                  Expanded(
                    flex: 1,
                    child: Text(
                      '회원가입',
                      textAlign: TextAlign.center,
                      style: TextStyle(
                        fontSize: 16,
                        color: Color(0xffBCBCBC),
                      ),
                    ),
                  ),
                  Text(
                    '|',
                    textAlign: TextAlign.center,
                    style: TextStyle(
                      fontSize: 13,
                      color: Color(0xffBCBCBC),
                    ),
                  ),
                  Expanded(
                    flex: 1,
                    child: Text(
                      '비밀번호 찾기',
                      textAlign: TextAlign.center,
                      style: TextStyle(
                        fontSize: 16,
                        color: Color(0xffBCBCBC),
                      ),
                    ),
                  ),
                ],
              ),
              SizedBox(
                height: size.height * 0.15,
              ),
              const Text(
                '파트너사 가입을 원하시나요?',
                style: TextStyle(
                  fontSize: 16,
                  color: Color(0xffBCBCBC),
                  decoration: TextDecoration.underline,
                ),
              ),
              SizedBox(
                height: size.height * 0.1,
              ),
              const Text(
                '좋은습관',
                style: TextStyle(
                  fontSize: 16,
                  color: Color(0xffBCBCBC),
                ),
              ),
            ],
          ),
        )));
  }

  Widget _inputForm(Size size) {
    // ignore: unused_local_variable
    String _user_id, _password = "";
    return Form(
        key: _formKey,
        child: Column(
          children: <Widget>[
            TextFormField(
              controller: _emailController,
              textCapitalization: TextCapitalization.words,
              textInputAction: TextInputAction.next,
              decoration: const InputDecoration(
                border: OutlineInputBorder(
                    borderSide: BorderSide(color: Colors.white, width: 2.0),
                    borderRadius: BorderRadius.all(Radius.circular(10))),
                labelText: '이메일',
              ),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return '잘못된 형식입니다.';
                }
                return null;
              },
            ),
            SizedBox(
              height: size.height * 0.015,
            ),
            TextFormField(
              obscureText: true,
              controller: _passwordController,
              decoration: const InputDecoration(
                border: OutlineInputBorder(
                    borderRadius: BorderRadius.all(Radius.circular(10))),
                labelText: '비밀번호',
              ),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return '잘못된 형식입니다.';
                }
                return null;
              },
            ),
          ],
        ));
  }

  Widget _authButton(Size size) {
    return ElevatedButton(
        autofocus: true,
        onPressed: () {
          if (_formKey.currentState != null) {
            if (_formKey.currentState!.validate()) {
              return print(_emailController.text);
            }
          }
        },
        child: const Text('로그인'),
        style: ElevatedButton.styleFrom(
            shape: RoundedRectangleBorder(
                //모서리를 둥글게
                borderRadius: BorderRadius.circular(10)),
            primary: const Color(0xffe8e8e8),
            onPrimary: const Color(0xffBCBCBC),
            minimumSize: const Size(double.infinity, 55),
            textStyle: const TextStyle(fontSize: 20)));
  }

  // Widget _logoImage() {
  //   return Expanded(
  //       child: Padding(
  //     padding: const EdgeInsets.only(top: 40, left: 24, right: 24),
  //     child: FittedBox(
  //       fit: BoxFit.contain,
  //       child: Image.asset(
  //         'assets/images/logo.png',
  //       ),
  //     ),
  //   ));
  // }
}
