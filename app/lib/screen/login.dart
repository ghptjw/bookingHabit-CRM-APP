import 'package:flutter/material.dart';

class AuthPage extends StatelessWidget {
  //  const AuthPage({Key? key}) : super(key: key);

  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    // ? context 앱이 구동되고 있는 정보
    final Size size = MediaQuery.of(context).size;

    return Scaffold(
        body: Center(
            child: Container(
      padding: const EdgeInsets.all(20),
      color: const Color(0xfffcfcfc),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          _logoImage(),
          _inputForm(size),
          SizedBox(
            height: size.height * 0.02,
          ),
          _authButton(size),
          SizedBox(
            height: size.height * 0.02,
          ),
          Row(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: const [
              Text(
                '회원가입',
                style: TextStyle(
                  fontSize: 16,
                  color: Color(0xffBCBCBC),
                ),
              ),
              Text(
                '|',
                style: TextStyle(
                  fontSize: 16,
                  color: Color(0xffBCBCBC),
                ),
              ),
              Text(
                '비밀번호 찾기',
                style: TextStyle(
                  fontSize: 16,
                  color: Color(0xffBCBCBC),
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
    int _todayGreenColor = 0xff16AA83;
    String test = 'hello';
    return Form(
        key: _formKey,
        child: Column(
          children: <Widget>[
            TextFormField(
              controller: _emailController,
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
              height: size.height * 0.02,
            ),
            TextFormField(
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
        onPressed: () {},
        child: Text('로그인'),
        style: ElevatedButton.styleFrom(
            shape: RoundedRectangleBorder(
                //모서리를 둥글게
                borderRadius: BorderRadius.circular(10)),
            primary: Color(0xffe8e8e8),
            onPrimary: Color(0xffBCBCBC),
            minimumSize: Size(double.infinity, 55),
            textStyle: const TextStyle(fontSize: 20)));
  }

  Widget _logoImage() {
    return Expanded(
        child: Padding(
      padding: const EdgeInsets.only(top: 40, left: 24, right: 24),
      child: FittedBox(
        fit: BoxFit.contain,
        child: Image.asset(
          'assets/images/logo.png',
        ),
      ),
    ));
  }
}
