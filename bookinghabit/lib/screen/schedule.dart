// 메인화면
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class Schedule extends StatefulWidget {
  const Schedule({Key? key}) : super(key: key);

  @override
  State<Schedule> createState() => _ScheduleState();
}

class _ScheduleState extends State<Schedule> {
  int _selectedIndex = 1;
  
  @override
  Widget build(BuildContext context) {
    final Size size = MediaQuery.of(context).size;
    return Scaffold(
      body: Stack(
        children: [
          Container(
            color: const Color(0xff16AA83),
          ),
          Container(
            child: Column(
              children: [
                Expanded(
                  child: 
                    Padding(
                      padding: EdgeInsets.all(size.width * 0.05),
                      child: Container(
                        width: double.infinity,
                        child: SafeArea(
                          child: Column(
                            children: [
                              Expanded(
                                child: 
                                  Container(
                                    width: double.infinity,
                                    color: Colors.black12,
                                    child: Row(
                                      
                                      mainAxisAlignment: MainAxisAlignment.center,
                                      children: [
                                        Expanded(
                                          child: Container(
                                            width: double.infinity,
                                            height:double.infinity,
                                            color: Colors.white30,
                                            child: const Text('center name'),
                                          ),
                                        ),
                                        const Spacer(),
                                        Expanded(
                                          child: Container(
                                            width: double.infinity,
                                            height:double.infinity,
                                            color: Colors.green,
                                            child: const Text('date'),
                                          ),
                                        )
                                      ],
                                    ),
                                  ),
                              ),
                              SizedBox(
                                height: size.width * 0.05,
                              ),
                              Expanded(
                                flex: 5,
                                child: 
                                  Container(
                                    padding: EdgeInsets.all(size.width * 0.05),
                                    width: double.infinity,
                                    child: const Text('schedule'),
                                    decoration:  BoxDecoration(
                                      color: Colors.white,
                                      borderRadius: BorderRadius.circular(20)
                                    ),
                                  ),
                              )
                            ],
                          ),
                        ),
                      ),
                    ),
                ),
                Expanded(
                  child: 
                    Container(
                      decoration: const BoxDecoration(
                        color: Colors.white,
                        borderRadius: BorderRadius.only(
                        topRight: Radius.circular(20),
                        topLeft: Radius.circular(20),
                        ),
                      ),
                      width: double.infinity,
                    ),
                )
              ],
            ),
          ),
        ],
      ),
      bottomNavigationBar: Container(
        decoration: const BoxDecoration(
          boxShadow: <BoxShadow>[
            BoxShadow(
              color: Colors.black26,
              blurRadius: 10,
            ),
          ],
        ),
        child: BottomNavigationBar(
          elevation: 10,
          type: BottomNavigationBarType.fixed,
          selectedItemColor: const Color(0xff16AA83),
          unselectedItemColor: Colors.black.withOpacity(.60),
          currentIndex: _selectedIndex,
          onTap: (int index) {
            switch (index) {
              case 0:
                Get.toNamed('mypage');
            }
            setState(() {
              _selectedIndex = index;
            });
          },
          items: const [
            BottomNavigationBarItem(
              label: '마이페이지',
              icon: Icon(Icons.person),
            ),
            BottomNavigationBarItem(
              label: '스케줄',
              icon: Icon(Icons.calendar_today_outlined),
            ),
            BottomNavigationBarItem(
              label: '알림',
              icon: Icon(Icons.notifications),
            ),
            BottomNavigationBarItem(
              label: '공지',
              icon: Icon(Icons.article_outlined),
            ),
          ],
        ),
      ),
    );
  }

  List _widgetOptions = [
    Text(
      '마이페이지',
      style: TextStyle(fontSize: 30, fontFamily: 'DoHyeonRegular'),
    ),
    Text(
      '스케줄',
      style: TextStyle(fontSize: 30, fontFamily: 'DoHyeonRegular'),
    ),
    Text(
      '알림',
      style: TextStyle(fontSize: 30, fontFamily: 'DoHyeonRegular'),
    ),
    Text(
      '공지',
      style: TextStyle(fontSize: 30, fontFamily: 'DoHyeonRegular'),
    ),
  ];
}
