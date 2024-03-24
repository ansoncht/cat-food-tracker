import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:logging/logging.dart';

// AuthPasswordFormField is a custom form field for entering passwords.
class AuthPasswordFormField extends StatefulWidget {
  const AuthPasswordFormField({
    super.key,
    required this.logger,
    required this.label,
  });

  final Logger logger;

  final String label;

  String? validatePassword(String? value) {
    if (value == null || value.isEmpty) {
      return 'Password cannot be empty.';
    }

    return null;
  }

  @override
  AuthPasswordFormFieldState createState() => AuthPasswordFormFieldState();
}

class AuthPasswordFormFieldState extends State<AuthPasswordFormField> {
  bool _isObscure = true;

  void toggleIsObscureState() {
    setState(() {
      _isObscure = !_isObscure;
    });
  }

  @override
  Widget build(BuildContext context) {
    try {
      return Container(
        margin: const EdgeInsets.only(bottom: 25),
        width: 0.85 * MediaQuery.of(context).size.width,
        height: 0.06 * MediaQuery.of(context).size.height,
        child: TextFormField(
          // The validator receives the text that the user has entered.
          validator: widget.validatePassword,
          decoration: InputDecoration(
            filled: true,
            fillColor: Colors.grey.shade50,
            border: OutlineInputBorder(
              borderRadius: BorderRadius.circular(8),
              borderSide: const BorderSide(
                width: 0,
                style: BorderStyle.none,
              ),
            ),
            focusedBorder: const OutlineInputBorder(),
            labelText: widget.label,
            alignLabelWithHint: true,
            prefixIcon: Icon(
              FontAwesomeIcons.lock,
              color: Theme.of(context).colorScheme.surface,
            ),
            suffixIcon: IconButton(
              hoverColor: Colors.transparent,
              highlightColor: Colors.transparent,
              icon: Icon(
                _isObscure ? FontAwesomeIcons.eye : FontAwesomeIcons.eyeSlash,
              ),
              onPressed: toggleIsObscureState,
              color: Theme.of(context).colorScheme.surface,
            ),
            suffixIconConstraints: const BoxConstraints(minWidth: 55),
            labelStyle: GoogleFonts.kanit(
                fontSize: 15,
                fontWeight: FontWeight.w300,
                color: Theme.of(context).colorScheme.surface),
          ),
          style: GoogleFonts.kanit(
            fontSize: 15,
            fontWeight: FontWeight.w300,
          ),
          obscureText: _isObscure,
        ),
      );
    } on Error catch (e) {
      widget.logger.finest(e.toString());
      rethrow;
    }
  }
}
