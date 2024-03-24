import 'package:client/component/auth/auth_email_form_field.dart';
import 'package:client/component/auth/auth_password_form_field.dart';
import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:logging/logging.dart';

class SignInForm extends StatelessWidget {
  const SignInForm({
    super.key,
    required this.logger,
    required this.function,
  });

  final Logger logger;
  final VoidCallback function;

  @override
  Widget build(BuildContext context) {
    try {
      return Form(
        child: Column(
          children: [
            Container(
              margin: const EdgeInsets.only(bottom: 50),
              child: Text(
                'Sign In',
                style: GoogleFonts.kanit(
                  fontSize: 25,
                  fontWeight: FontWeight.w500,
                ),
              ),
            ),
            AuthEmailFormField(
              logger: logger,
            ),
            AuthPasswordFormField(
              logger: logger,
              label: 'Password',
            ),
            Container(
              margin: const EdgeInsets.only(top: 25, bottom: 15),
              width: 0.85 * MediaQuery.of(context).size.width,
              height: 0.06 * MediaQuery.of(context).size.height,
              child: TextButton(
                onPressed: () {},
                style: ButtonStyle(
                  backgroundColor: MaterialStateProperty.all(
                    Theme.of(context).colorScheme.secondary,
                  ),
                  overlayColor: MaterialStateProperty.all(Colors.transparent),
                ),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Padding(
                      padding: const EdgeInsets.only(
                        right: 15,
                      ),
                      child: Icon(
                        FontAwesomeIcons.solidEnvelope,
                        color: Theme.of(context).colorScheme.onSurface,
                      ),
                    ),
                    Text(
                      'Sign In with Email',
                      style: TextStyle(
                        color: Theme.of(context).colorScheme.onSurface,
                        fontSize: 20,
                        fontWeight: FontWeight.w300,
                      ),
                    ),
                  ],
                ),
              ),
            ),
            Container(
              margin: const EdgeInsets.only(bottom: 15),
              child: Text(
                'Or',
                style: GoogleFonts.kanit(
                  fontSize: 25,
                  fontWeight: FontWeight.w300,
                ),
              ),
            ),
            Container(
              margin: const EdgeInsets.only(bottom: 25),
              width: 0.85 * MediaQuery.of(context).size.width,
              height: 0.06 * MediaQuery.of(context).size.height,
              child: TextButton(
                onPressed: () {},
                style: ButtonStyle(
                  backgroundColor: MaterialStateProperty.all(
                    Theme.of(context).colorScheme.error,
                  ),
                  overlayColor: MaterialStateProperty.all(Colors.transparent),
                ),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Padding(
                      padding: const EdgeInsets.only(
                        right: 15,
                      ),
                      child: Icon(
                        FontAwesomeIcons.google,
                        color: Theme.of(context).colorScheme.onSurface,
                      ),
                    ),
                    Text(
                      'Sign In with Google',
                      style: TextStyle(
                        color: Theme.of(context).colorScheme.onSurface,
                        fontSize: 20,
                        fontWeight: FontWeight.w300,
                      ),
                    ),
                  ],
                ),
              ),
            ),
            Expanded(
              child: Align(
                alignment: Alignment.bottomCenter,
                child: Padding(
                  padding: const EdgeInsets.only(
                    bottom: 25,
                  ),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Text(
                        'First time?',
                        style: GoogleFonts.kanit(
                          fontSize: 20,
                          fontWeight: FontWeight.w300,
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.only(
                          left: 5,
                        ),
                        child: TextButton(
                          onPressed: function,
                          style: ButtonStyle(
                            padding: MaterialStateProperty.all(
                              EdgeInsets.zero,
                            ),
                            overlayColor: MaterialStateProperty.all(
                              Colors.transparent,
                            ),
                          ),
                          child: Text(
                            'Create Account',
                            style: GoogleFonts.kanit(
                              fontSize: 20,
                              fontWeight: FontWeight.w300,
                              decoration: TextDecoration.underline,
                              decorationColor:
                                  Theme.of(context).colorScheme.primary,
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
          ],
        ),
      );
    } on Error catch (e) {
      logger.finest(e.toString());
      rethrow;
    }
  }
}
