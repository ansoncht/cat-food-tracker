import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:logging/logging.dart';

class AuthEmailFormField extends StatelessWidget {
  const AuthEmailFormField({
    super.key,
    required this.logger,
  });

  final Logger logger;

  String? validateEmail(String? value) {
    if (value == null || value.isEmpty) {
      return 'Email cannot be empty.';
    }

    bool valid = RegExp(
            r"^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,253}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,253}[a-zA-Z0-9])?)*$")
        .hasMatch(value);
    if (!valid) {
      return 'Email is invalid.';
    }

    return null;
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
          validator: validateEmail,
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
            labelText: 'Email',
            alignLabelWithHint: true,
            prefixIcon: Icon(
              FontAwesomeIcons.at,
              color: Theme.of(context).colorScheme.surface,
            ),
            labelStyle: GoogleFonts.kanit(
                fontSize: 15,
                fontWeight: FontWeight.w300,
                color: Theme.of(context).colorScheme.surface),
          ),
          style: GoogleFonts.kanit(
              fontSize: 15, fontWeight: FontWeight.w300, color: Colors.black),
        ),
      );
    } on Error catch (e) {
      logger.finest(e.toString());
      rethrow;
    }
  }
}
