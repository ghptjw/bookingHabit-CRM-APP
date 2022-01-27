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
    return TextFormField(
      controller: widget.myController,
      textCapitalization: TextCapitalization.words,
      textInputAction: TextInputAction.next,
      decoration: InputDecoration(
        border: const OutlineInputBorder(
            borderSide: BorderSide(color: Colors.white, width: 2.0),
            borderRadius: BorderRadius.all(Radius.circular(10))),
        labelText: widget.labelValue,
        hintText: widget.hintValue,
      ),

      validator: (value) {
        if (value == null || value.isEmpty) {
          return '잘못된 형식입니다.';
        }
        return null;
      },
    );
  }
}
