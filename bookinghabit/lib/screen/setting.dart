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
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const Text('계정'),
              underLine(),
              const Text('비밀번호변경'),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: const [Text('소속센터'), Text('좋은습관 캘리포니아점')],
              ),
              const Text('앱'),
              underLine(),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: const [Text('버전정보'), Text('0.9버전')],
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: const [Text('알람수신설정'), Text('토글버튼')],
              ),
              const Text('고객센터'),
              underLine(),
              const Text('공지사항'),
              const Text('자주묻는질문'),
              const Text('서비스 이용약관'),
              const Text('개인정보처리방침'),
            ],
          ),
        ));
  }

  Widget underLine() {
    return Container(
        width: double.infinity,
        child: const Divider(color: Colors.black, thickness: 1.0));
  }
}
