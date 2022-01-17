import 'package:flutter/material.dart';


class AuthPage extends StatelessWidget {
  
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();



  @override
  Widget build(BuildContext context) {
    // ? context 앱이 구동되고 있는 정보
    final Size size = MediaQuery.of(context).size;

    return Scaffold(
        body: Stack(
      alignment: Alignment.center,
      children: <Widget>[
        Container(color: const Color(0xfffcfcfc)),
        Column(
          crossAxisAlignment: CrossAxisAlignment.center, 
          mainAxisAlignment: MainAxisAlignment.end,
          children: <Widget>[
            Image.asset('/app/assets/logo.png'),
            Stack(children: <Widget>[
              Form(
                key: _formKey,
                child: Column(
                  children: <Widget>[ 
                    TextFormField(
                      controller: _emailController,
                      decoration: const InputDecoration(
                        border: OutlineInputBorder(),
                        labelText: '이메일',
                      ),
                      validator: (value) {
                        if (value == null || value.isEmpty) {
                        return '잘못된 형식입니다.';
                      }
                        return null;
                      },
                    ),
                    TextFormField(
                      controller: _passwordController,
                        decoration: const InputDecoration(
                          border: OutlineInputBorder(),
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
                ))
            ],) ,
            const Text(
              '파트너사 가입을 원하시나요?',
              style: TextStyle(
                fontSize: 16,
                color: Color(0xffBCBCBC),
              ),
            ),
            Container(
              height: size.height * 0.1,
            ),
            const Text(
              '좋은습관',
              style: TextStyle(
                fontSize: 16,
                color: Color(0xffBCBCBC),
              ),
            ),
            Container(
              height: size.height * 0.1,
            )
          ],
        )
      ],
    ));
  }
}
