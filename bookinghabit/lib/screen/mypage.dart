import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';

class Mypage extends StatelessWidget {
  const Mypage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final Size size = MediaQuery.of(context).size;
    return SizedBox(
        child: Scaffold(
      appBar: AppBar(
        bottomOpacity: 0.0,
        elevation: 0.0,
        foregroundColor: Colors.black,
        backgroundColor: const Color.fromRGBO(75, 75, 75, 1),
        shadowColor: Colors.white,
        title: const Text('회원정보'),
        titleTextStyle: const TextStyle(
          color: Colors.white,
          fontSize: 20,
        ),
        actions: [
          IconButton(
              onPressed: () {},
              icon: const Icon(Icons.settings_outlined, color: Colors.white))
        ],
      ),
      body: Container(
        width: size.width,
        padding: EdgeInsets.symmetric(
            horizontal: size.width * 0.05, vertical: size.width * 0.05),
        color: const Color.fromRGBO(75, 75, 75, 1),
        child: SizedBox(
          width: double.infinity,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.start,
            children: [
              Flexible(
                flex: 3,
                fit: FlexFit.tight,
                child: Container(
                  width: double.infinity,
                  child: Padding(
                    padding: const EdgeInsets.all(10.0),
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        CircleAvatar(
                          backgroundColor: Colors.white,
                          radius: 60.0,
                          backgroundImage: AssetImage('assets/images/logo.png'),
                          child: Image.asset(
                            'assets/images/logo.png',
                            fit: BoxFit.fill,
                          ),
                        ),
                        const Spacer(),
                        const Text('이조은',
                            style: TextStyle(
                                textBaseline: TextBaseline.alphabetic,
                                color: Colors.white)),
                        const Spacer(),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Expanded(
                              child: Container(
                                width: double.infinity,
                                child: const Text('selectbox'),
                                color: Colors.amberAccent,
                              ),
                            ),
                            Expanded(
                              child: Container(
                                width: double.infinity,
                                child: const Text('selectbox'),
                                color: Colors.white,
                              ),
                            ),
                          ],
                        )
                      ],
                    ),
                  ),
                  color: Colors.blue,
                ),
              ),
              Flexible(
                flex: 5,
                fit: FlexFit.tight,
                child: Container(
                  width: double.infinity,
                  color: Colors.orange,
                  child: Padding(
                    padding: EdgeInsets.all(size.width * 0.05),
                    child: Column(
                      children: [
                        Flexible(
                          flex: 1,
                          child: Column(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: const <Widget>[
                              Text('회원권 유효기간',
                                  style: TextStyle(color: Colors.white)),
                              Text('2022.01.02 ~ 2022.03.02',
                                  style: TextStyle(color: Colors.white))
                            ],
                          ),
                        ),
                        Flexible(
                          flex: 3,
                          child: ListView(
                            children: [
                              Container(
                                width: double.infinity,
                                height: size.height * 0.09,
                                margin: EdgeInsets.symmetric(
                                    vertical: size.height * 0.005),
                                decoration: BoxDecoration(
                                  border: Border.all(
                                    width: 1,
                                    color: Colors.white,
                                  ),
                                ),
                              ),
                              Container(
                                width: double.infinity,
                                height: size.height * 0.09,
                                margin: EdgeInsets.symmetric(
                                    vertical: size.height * 0.005),
                                decoration: BoxDecoration(
                                  border: Border.all(
                                    width: 1,
                                    color: Colors.white,
                                  ),
                                ),
                              ),
                              Container(
                                width: double.infinity,
                                height: size.height * 0.09,
                                margin: EdgeInsets.symmetric(
                                    vertical: size.height * 0.005),
                                decoration: BoxDecoration(
                                  border: Border.all(
                                    width: 1,
                                    color: Colors.white,
                                  ),
                                ),
                              ),
                              Container(
                                width: double.infinity,
                                height: size.height * 0.09,
                                margin: EdgeInsets.symmetric(
                                    vertical: size.height * 0.005),
                                decoration: BoxDecoration(
                                  border: Border.all(
                                    width: 1,
                                    color: Colors.white,
                                  ),
                                ),
                              ),
                              Container(
                                width: double.infinity,
                                height: size.height * 0.09,
                                margin: EdgeInsets.symmetric(
                                    vertical: size.height * 0.005),
                                decoration: BoxDecoration(
                                  border: Border.all(
                                    width: 1,
                                    color: Colors.white,
                                  ),
                                ),
                              ),
                              Container(
                                width: double.infinity,
                                height: size.height * 0.09,
                                margin: EdgeInsets.symmetric(
                                    vertical: size.height * 0.005),
                                decoration: BoxDecoration(
                                  border: Border.all(
                                    width: 1,
                                    color: Colors.white,
                                  ),
                                ),
                              ),
                              Container(
                                width: double.infinity,
                                height: size.height * 0.09,
                                margin: EdgeInsets.symmetric(
                                    vertical: size.height * 0.005),
                                decoration: BoxDecoration(
                                  border: Border.all(
                                    width: 1,
                                    color: Colors.white,
                                  ),
                                ),
                              ),
                            ],
                          ),
                        )
                      ],
                    ),
                  ),
                ),
              ),
              Flexible(
                flex: 1,
                fit: FlexFit.tight,
                child: Container(
                  width: double.infinity,
                  color: Colors.blueGrey,
                  child: ElevatedButton(
                    style:ElevatedButton.styleFrom(
                        primary: Colors.white,
                        onPrimary:Colors.green,
                    ),
                    onPressed: () {},
                    child:Text('수업내역'),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    ));
  }
}
