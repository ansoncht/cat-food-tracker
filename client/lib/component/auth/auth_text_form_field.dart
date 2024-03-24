import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:logging/logging.dart';

class AuthTextFormField extends StatelessWidget {
  const AuthTextFormField({
    super.key,
    required this.logger,
    required this.label,
    required this.icon,
  });

  final Logger logger;

  final String label;

  final IconData icon;

  String? validateText(String? value) {
    if (value == null || value.isEmpty) {
      return 'Password cannot be empty.';
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
          validator: validateText,
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
            labelText: label,
            alignLabelWithHint: true,
            prefixIcon: Icon(
              icon,
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
