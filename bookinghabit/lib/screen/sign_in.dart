import 'package:flutter/material.dart';
import 'package:get/get.dart';

class AuthPage extends StatefulWidget {
  const AuthPage({Key? key}) : super(key: key);

  @override
  State<AuthPage> createState() => _AuthPageState();
}

class _AuthPageState extends State<AuthPage> {
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
          padding: EdgeInsets.symmetric(horizontal: size.width * 0.05),
          color: const Color(0xfffcfcfc),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              SizedBox(
                height: size.height * 0.07,
              ),
              Image.asset(
                'assets/images/logo.png',
                width: size.width * 0.6,
                height: size.height * 0.2,
              ),
              SizedBox(
                height: size.height * 0.04,
              ),
              _inputForm(size),
              SizedBox(
                height: size.height * 0.02,
              ),
              _authButton(size),
              SizedBox(
                height: size.height * 0.02,
              ),
              _pageRoutingBtn(),
              SizedBox(
                height: size.height * 0.2,
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
                height: size.height * 0.03,
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

  Widget _pageRoutingBtn() {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Expanded(
          flex: 1,
          child: GestureDetector(
            onTap: () {
              Get.toNamed('/signup'
                  // arguments 전달 가능 (텍스트, 숫자) {'name':'개남', 'age': '22'}
                  // 찍어 낼떈 Get.arguments
                  // 커스텀하게 만든 객체도 전달가능
                  );
            },
            child: const Text(
              '회원가입',
              textAlign: TextAlign.center,
              style: TextStyle(
                fontSize: 16,
                color: Color(0xffBCBCBC),
              ),
            ),
          ),
        ),
        const Text(
          '|',
          textAlign: TextAlign.center,
          style: TextStyle(
            fontSize: 13,
            color: Color(0xffBCBCBC),
          ),
        ),
        Expanded(
          flex: 1,
          child: GestureDetector(
            child: const Text(
              '비밀번호 찾기',
              textAlign: TextAlign.center,
              style: TextStyle(
                fontSize: 16,
                color: Color(0xffBCBCBC),
              ),
            ),
            onTap: () {},
          ),
        ),
      ],
    );
  }

  Widget _inputForm(Size size) {
    // ignore: unused_local_variable

    return Form(
        key: _formKey,
        child: Column(
          mainAxisSize: MainAxisSize.max,
          children: <Widget>[
            TextFormField(
              controller: _emailController,
              autofocus: false,
              textCapitalization: TextCapitalization.words,
              textInputAction: TextInputAction.next,
              keyboardType: TextInputType.emailAddress,
              decoration: const InputDecoration(
                fillColor: Color(0xfffcfcfc),
                focusedBorder: OutlineInputBorder(
                  borderSide: BorderSide(color: Color(0xff16AA83)),
                  borderRadius: BorderRadius.all(Radius.circular(10)),
                ),
                border: OutlineInputBorder(
                    borderSide: BorderSide(color: Colors.white, width: 2.0),
                    borderRadius: BorderRadius.all(Radius.circular(10))),
                labelText: '이메일',
                hintText: '이메일',
              ),
              onSaved: (value) {
                _emailController.text = value!;
              },
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return '이메일을 입력해주세요.';
                } else if (!RegExp(
                        "/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}/")
                    .hasMatch(value)) {
                  return '잘못된 형식입니다.';
                } else {
                  return null;
                }
              },
            ),
            SizedBox(
              height: size.height * 0.015,
            ),
            TextFormField(
              autofocus: false,
              obscureText: true,
              controller: _passwordController,
              textInputAction: TextInputAction.done,
              decoration: const InputDecoration(
                focusedBorder: OutlineInputBorder(
                  borderSide: BorderSide(color: Color(0xff16AA83)),
                  borderRadius: BorderRadius.all(Radius.circular(10)),
                ),
                fillColor: Color(0xfffcfcfc),
                focusColor: Color(0xff16AA83),
                border: OutlineInputBorder(
                    borderRadius: BorderRadius.all(Radius.circular(10))),
                labelText: '비밀번호',
                hintText: '비밀번호',
              ),
              validator: (value) {
                if (value == null || value.isEmpty) {
                  return '비밀번호를 입력해주세요.';
                } else if (!RegExp(
                        "/^[A-Za-z0-9]{6,12}/")
                    .hasMatch(value)) {
                  return '잘못된 형식입니다.';
                } else {
                  return null;
                }
              },
              onSaved: (value) {
                _passwordController.text = value!;
              },
            ),
          ],
        ));
  }

  Widget _authButton(Size size) {
    return OutlinedButton(
        autofocus: true,
        onPressed: () {
          if (_formKey.currentState != null) {
            if (_formKey.currentState!.validate()) {
              return print(_emailController.text);
            }
          }
        },
        child: const Text('로그인'),
        style: OutlinedButton.styleFrom(
            shape: RoundedRectangleBorder(
                //모서리를 둥글게
                borderRadius: BorderRadius.circular(10)),
            side: const BorderSide(
              style: BorderStyle.none,
            ),
            primary: const Color(0xffBCBCBC),
            // onPrimary: const Color(0xffBCBCBC),
            backgroundColor: const Color(0xffe8e8e8),
            minimumSize: const Size(double.infinity, 55),
            textStyle: const TextStyle(fontSize: 20)));
  }
}
