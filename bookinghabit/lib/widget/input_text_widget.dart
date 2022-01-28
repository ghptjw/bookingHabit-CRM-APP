import 'package:flutter/material.dart';

class InputText extends StatefulWidget {
  InputText({
    Key? key,
    required this.labelValue,
    required this.hintValue,
    required TextEditingController this.myController,
  }) : super(key: key);

  final String labelValue;
  final String hintValue;

  TextEditingController? myController = TextEditingController();

  @override
  State<InputText> createState() => _InputTextState();
}

class _InputTextState extends State<InputText> {
  @override
  Widget build(BuildContext context) {
    final Size size = MediaQuery.of(context).size;
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          widget.labelValue,
          style: const TextStyle(
            fontSize: 16.0,
            fontWeight: FontWeight.w700,
            color: Color.fromRGBO(124 ,124 ,124, 1)
          ),
        ),
        SizedBox(
          height: size.height * 0.02
        ),
        TextFormField(
          controller: widget.myController,
          textCapitalization: TextCapitalization.words,
          textInputAction: TextInputAction.next,
          cursorColor: const Color(0xff16AA83),
          decoration: InputDecoration(
            focusedBorder: const OutlineInputBorder(
              borderSide: BorderSide(color: Color(0xff16AA83)),
              borderRadius: BorderRadius.all(Radius.circular(10))
            ),
            border: const OutlineInputBorder(
                borderSide: BorderSide(color: Colors.white, width: 2.0),
                borderRadius: BorderRadius.all(Radius.circular(10))),
            hintText: widget.hintValue,
          ),
          validator: (value) {
            if (value == null || value.isEmpty) {
              return '잘못된 형식입니다.';
            }
            return null;
          },
        ),
      ],
    );
  }
}
